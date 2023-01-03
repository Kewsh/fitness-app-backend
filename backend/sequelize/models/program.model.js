const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('program', {
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        description: {
            type: DataTypes.STRING(4096),
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        coachName: {
            type: DataTypes.STRING,
            validate: {
                is: /^[a-zA-Z\s]*$/,
            }
        },
        price: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 0,
            },
        },
        duration: {
            type: DataTypes.VIRTUAL,
        },
        numberOfAthletes: {
            type: DataTypes.VIRTUAL,
        },
        rating: {
            type: DataTypes.VIRTUAL,
        },
        numberOfRatings: {
            type: DataTypes.VIRTUAL,
        }
    }, {
        hooks: {
            afterFind: async query => {
                // skip this hook if no match is found
                if (!query) return;

                // set virtual fields
                query.duration = (
                    await getDuration(sequelize, query.id)
                ).dataValues.duration;

                query.numberOfAthletes = await getNumberOfAthletes(
                    sequelize,
                    query.id
                );

                const rating = await getRating(sequelize, query.id);
                query.rating = Number(rating.dataValues.avgRate);
                query.numberOfRatings = Number(rating.dataValues.nRates);
            }
        }
    });
}


const getDuration = async (sequelize, programId) => {
    // get maximum day on workouts that belong to this program
    return (await sequelize.models.workout.findAll({
        where: { programId },
        attributes: [
            [sequelize.fn('MAX', sequelize.col('day')), 'duration'],
        ],
    }))[0];
}

const getRating = async (sequelize, programId) => {
    // find all comments whose programId is the same as param programId
    // this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: { programId },
        attributes: [
            [sequelize.fn('AVG', sequelize.col('rate')), 'avgRate'],
            [sequelize.fn('COUNT', sequelize.col('rate')), 'nRates'],
        ],
    }))[0];
}

const getNumberOfAthletes = async (sequelize, programId) => {
    // get number of users whose programId is the same as param programId
    return await sequelize.models.user.count({ where: { programId } });
}
