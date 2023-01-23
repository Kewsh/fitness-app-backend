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

                // set virtual fields.
                // e.g. query can be from findAll or findOne
                if (Array.isArray(query)){
                    for (const result of query) {
                        const { duration, nAthletes, rating } =
                            await getVirtualFields(sequelize, result.id);

                        result.duration = duration;
                        result.nAthletes = nAthletes;
                        result.rating = rating;
                    }
                } else {
                    const { duration, nAthletes, rating } =
                        await getVirtualFields(sequelize, query.id);

                    query.duration = duration;
                    query.nAthletes = nAthletes;
                    query.rating = rating;
                }
            },
            afterCreate: async program => {
                // delete all coverPicPath fields
                delete program.dataValues.coverPicPath;
                program.workouts && program.workouts.map(
                    workout => {
                        delete workout.dataValues.coverPicPath;
                        delete workout.dataValues.videoPath;
                    }
                );
            },
            afterUpdate: async program => {
                // delete all coverPicPath fields
                delete program.dataValues.coverPicPath;
                program.workouts && program.workouts.map(
                    workout => {
                        delete workout.dataValues.coverPicPath;
                        delete workout.dataValues.videoPath;
                    }
                );
            },
        }
    });
}


const getVirtualFields = async (sequelize, id) => {
    const duration = await getDuration(sequelize, id);
    const nAthletes = await getNumberOfAthletes(sequelize, id);
    let rating = await getRating(sequelize, id);
    rating = {
        rating: parseInt(rating.avgRate),
        nRates: parseInt(rating.nRates),
    };

    return { duration, nAthletes, rating };
}

const getDuration = async (sequelize, programId) => {
    // get maximum day on workouts that belong to this program
    return (await sequelize.models.workout.findAll({
        where: { programId },
        attributes: [
            [sequelize.fn('MAX', sequelize.col('day')), 'duration'],
        ],
    }))[0].dataValues.duration;
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
    }))[0].dataValues;
}

const getNumberOfAthletes = async (sequelize, programId) => {
    // get number of users whose programId is the same as param programId
    return await sequelize.models.user.count({ where: { programId } });
}
