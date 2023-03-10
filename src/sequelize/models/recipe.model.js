const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('recipe', {
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        description: {
            type: DataTypes.STRING(4096),
            allowNull: false,
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        origin: {
            type: DataTypes.STRING,
        },
        prepTimeInMinutes: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0,
            },
        },
        servings: {
            type: DataTypes.INTEGER,
            validate: {
                min: 1,
            },
        },
        price: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0,
            },
        },
        stepByStepGuide: {
            type: DataTypes.STRING(65000),
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
                        const { rating } =
                            await getVirtualFields(sequelize, result.id);

                        result.rating = rating;
                    }
                } else {
                    const { rating } =
                        await getVirtualFields(sequelize, query.id);

                    query.rating = rating;
                }
            }
        }
    });
}


const getVirtualFields = async (sequelize, id) => {
    let rating = await getRating(sequelize, id);
    rating = {
        rating: parseInt(rating.avgRate),
        nRates: parseInt(rating.nRates),
    };

    return { rating };
}

const getRating = async (sequelize, recipeId) => {
    // can we do this all in one query?
    // find all recipeReviews that belong to this recipe
    const recipeReviewIds = (await sequelize.models.recipeReview.findAll({
        where: { recipeId },
        attributes: ['id'],
    })).map(recipeReview => recipeReview.dataValues.id);

    // find all comments that belong to these recipe review ids
    // this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: { recipeReviewId: recipeReviewIds },
        attributes: [
            [sequelize.fn('AVG', sequelize.col('rate')), 'avgRate'],
            [sequelize.fn('COUNT', sequelize.col('rate')), 'nRates'],
        ],
    }))[0].dataValues;
}
