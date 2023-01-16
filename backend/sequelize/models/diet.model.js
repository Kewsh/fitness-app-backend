const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('diet', {
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
        price: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 0,
            },
        },
        duration: {
            type: DataTypes.VIRTUAL
        },
        nAthletes: {
            type: DataTypes.VIRTUAL,
        },
        rating: {
            type: DataTypes.VIRTUAL,
        },
    }, {
        hooks: {
            afterFind: async query => {
                // skip this hook if no match is found
                if (!query) return;

                // set virtual fields
                query.duration = (
                    await getDuration(sequelize, query.id)
                ).dataValues.duration;

                query.nAthletes = await getNumberOfAthletes(
                    sequelize,
                    query.id
                );
                const rating = await getRating(sequelize, query.id);
                query.rating = {
                    rating: parseInt(rating.dataValues.avgRate),
                    nRates: query.numberOfRatings = parseInt(
                        rating.dataValues.nRates
                    ),
                };
            }
        }
    });
}


const getDuration = async (sequelize, dietId) => {
    // get maximum day on workouts that belong to this program
    return (await sequelize.models.food.findAll({
        where: { dietId },
        attributes: [
            [sequelize.fn('MAX', sequelize.col('day')), 'duration'],
        ],
    }))[0];
}

const getRating = async (sequelize, dietId) => {
    // find all comments whose dietId is the same as param dietId
    // this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: { dietId },
        attributes: [
            [sequelize.fn('AVG', sequelize.col('rate')), 'avgRate'],
            [sequelize.fn('COUNT', sequelize.col('rate')), 'nRates'],
        ],
    }))[0];
}

const getNumberOfAthletes = async (sequelize, dietId) => {
    // get number of users whose dietId is the same as param dietId
    return await sequelize.models.user.count({ where: { dietId } });
}
