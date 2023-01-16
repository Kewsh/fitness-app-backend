const { getUploadedFilePath } = require('../file-utils');
const {
    recipe: recipeModel,
    diet: dietModel,
    recipeIngredient: recipeIngredientModel,
    recipeReview: recipeReviewModel,
    comment: commentModel,
    user: userModel,
} = require('../../sequelize').models;

module.exports.findOneById = async (req, res) => {
    try {
        const recipe = await recipeModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
            include: [
                {
                    model: recipeIngredientModel,
                    attributes: ['title', 'amount', 'amountAndTitle'],
                },
                {
                    model: dietModel,
                    attributes: ['id', 'title'],
                }
            ],
        });

        if (!recipe) {
            return res.error(404, 'No recipe found with this id');
        }
        return res.success(200, recipe);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const recipe = await recipeModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!recipe || !recipe.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(recipe.coverPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getReviews = async (req, res) => {
    try {
        const reviews = await recipeReviewModel.findAll({
            where: { recipeId: req.params.id },
            include: {
                model: commentModel,
                include: {
                    model: userModel,
                    attributes: [
                        'id',
                        'firstName',
                        'lastName',
                        'fullName'
                    ],
                },
            },
            attributes: { exclude: ['reviewPicPath'] },
        });

        return res.success(200, reviews);
    } catch (error) {
        return res.error(500, error.message);
    }
}
