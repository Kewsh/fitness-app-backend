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
            }
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

const getDuration = async (sequelize, dietId) => {
    // get maximum day on workouts that belong to this program
    return (await sequelize.models.food.findAll({
        where: { dietId },
        attributes: [
            [sequelize.fn('MAX', sequelize.col('day')), 'duration'],
        ],
    }))[0].dataValues.duration;
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
    }))[0].dataValues;
}

const getNumberOfAthletes = async (sequelize, dietId) => {
    // get number of users whose dietId is the same as param dietId
    return await sequelize.models.user.count({ where: { dietId } });
}
