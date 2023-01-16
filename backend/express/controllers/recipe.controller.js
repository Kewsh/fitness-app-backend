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
            return res.status(404).json('No recipe found with this id');
        }
        return res.status(200).json(recipe);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const recipe = await recipeModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!recipe || !recipe.coverPicPath) {
            return res.status(404).json('No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(recipe.coverPicPath));
    } catch (error) {
        return res.status(500).json(error);
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

        return res.status(200).json(reviews);
    } catch (error) {
        return res.status(500).json(error);
    }
}
