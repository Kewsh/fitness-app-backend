const {
    recipe: recipeModel,
    recipeIngredient: recipeIngredientModel,
    recipeReview: recipeReviewModel,
    comment: commentModel,
    user: userModel,
} = require('../../sequelize').models;

module.exports.findOneById = async (req, res) => {
    try {
        const recipe = await recipeModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
            include: recipeIngredientModel,
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
