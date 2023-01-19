const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('nutritionist', {
        fullName: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                is: /^[a-zA-Z\s]*$/,
            },
        },
        picPath: {
            type: DataTypes.STRING,
        },
        description: {
            type: DataTypes.STRING(4096),
        },
        since: {
            type: DataTypes.DATEONLY,
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

                // get diet ids that belong to this nutritionist
                const dietIds = await getDiets(sequelize, query.id);
                
                // set virtual fields.
                // e.g. query can be from findAll or findOne
                if (Array.isArray(query)){
                    for (const result of query) {
                        const { nAthletes, rating } =
                            await getVirtualFields(sequelize, dietIds);

                        result.nAthletes = nAthletes;
                        result.rating = rating;
                    }
                } else {
                    const { nAthletes, rating } =
                        await getVirtualFields(sequelize, dietIds);

                    query.nAthletes = nAthletes;
                    query.rating = rating;
                }
            },
        },
    });
}


const getDiets = async (sequelize, nutritionistId) => {
    return (await sequelize.models.diet.findAll({
        where: { nutritionistId },
        attributes: ['id'],
        hooks: false,
    })).map(diet => diet.dataValues.id);
}

const getVirtualFields = async (sequelize, dietIds) => {
    const nAthletes = await getNumberOfAthletes(sequelize, dietIds);
    let rating = await getRating(sequelize, dietIds);
    rating = {
        rating: parseInt(rating.avgRate),
        nRates: parseInt(rating.nRates),
    };

    return { nAthletes, rating };
}

const getRating = async (sequelize, dietIds) => {
    // find all comments on dietIds
    // this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: { dietId: dietIds },
        attributes: [
            [sequelize.fn('AVG', sequelize.col('rate')), 'avgRate'],
            [sequelize.fn('COUNT', sequelize.col('rate')), 'nRates'],
        ],
    }))[0].dataValues;
}

const getNumberOfAthletes = async (sequelize, dietIds) => {
    // get number of users whose dietId is in dietIds
    return await sequelize.models.user.count({ where: { dietId: dietIds } });
}
