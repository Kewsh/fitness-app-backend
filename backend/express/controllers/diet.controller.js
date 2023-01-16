const { Op } = require('sequelize');
const { getUploadedFilePath } = require('../file-utils');
const {
    diet: dietModel,
    food: foodModel,
    recipe: recipeModel,
    comment: commentModel,
    user: userModel,
    nutritionist: nutritionistModel,
} = require('../../sequelize').models;

module.exports.discover = async (req, res) => {
    try {
        const user = await userModel.findByPk(req.body.userId, {
            attributes: ['id', 'dietId'],
        });

        if (!user) {
            return res.status(404).json('No user found with this id');
        }
        const dietId = user.dietId;

        // find all diets user hasn't picked
        const diets = await dietModel.findAll({
            where: { id: { [Op.not]: dietId } },
            include: {
                model: nutritionistModel,
                attributes: ['fullName'],
            },
            attributes: ['id', 'title'],
            hooks: false,
        })

        return res.status(200).json(diets);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.findOneById = async (req, res) => {
    try {
        const diet = await dietModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
        });

        //TODO: this can't be done in include, since hooks don't run on
        // included models
        diet.dataValues.nutritionist = await diet.getNutritionist({
            attributes: { exclude: ['picPath'] },
        });

        if (!diet) {
            return res.status(404).json('No diet found with this id');
        }
        return res.status(200).json(diet);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const diet = await dietModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!diet || !diet.coverPicPath) {
            return res.status(404).json('No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(diet.coverPicPath));
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getFoods = async (req, res) => {
    try {
        const foods = await foodModel.findAll({
            where: { dietId: req.params.id },
            attributes: ['id', 'amount', 'title', 'amountAndTitle', 'day'],
        });
        return res.status(200).json(foods);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getRecipes = async (req, res) => {
    try {
        const recipes = await recipeModel.findAll({
            where: { dietId: req.params.id },
            attributes: ['id', 'title'],
            hooks: false,
        });
        return res.status(200).json(recipes);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getComments = async (req, res) => {
    try {
        const comments = await commentModel.findAll({
            where: { dietId: req.params.id },
            include: {
                model: userModel,
                attributes: [
                    'id',
                    'firstName',
                    'lastName',
                    'fullName',
                ],
            },
        });

        return res.status(200).json(comments);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.pick = async (req, res) => {
    try {
        if (!req.body.userId) {
            return res.status(400).json(
                'Missing field "userId" in request body'
            );
        }
        const [ affectedRows ] = await userModel.update({
            dietId: req.params.id,
            dietPickDate: new Date(),
        }, {
            where: { id: req.body.userId },
            individualHooks: true,
        });
        if (!affectedRows) {
            return res.status(404).json('No user found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}
