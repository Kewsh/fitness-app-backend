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
            return res.error(404, 'No user found with this id');
        }
        const dietId = user.dietId;

        // find all diets user hasn't picked
        const diets = await dietModel.findAndCountAll({
            where: { id: { [Op.not]: dietId } },
            limit: req.query.limit,
            offset: req.query.offset,
            include: {
                model: nutritionistModel,
                attributes: ['fullName'],
            },
            attributes: ['id', 'title'],
            hooks: false,
        })

        return res.success(200, diets);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.findOneById = async (req, res) => {
    try {
        const diet = await dietModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
        });

        if (!diet) {
            return res.error(404, 'No diet found with this id');
        }

        //TODO: this can't be done in include, since hooks don't run on
        // included models
        diet.dataValues.nutritionist = await diet.getNutritionist({
            attributes: { exclude: ['picPath'] },
        });

        return res.success(200, diet);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const diet = await dietModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!diet || !diet.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(diet.coverPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getFoods = async (req, res) => {
    try {
        const where = { dietId: req.params.id };
        if (req.query.day) {
            where.day = req.query.day; 
        }

        const foods = await foodModel.findAndCountAll({
            where,
            order: ['day'],
            limit: req.query.limit,
            offset: req.query.offset,
            attributes: ['id', 'amount', 'title', 'amountAndTitle', 'day'],
        });
        return res.success(200, foods);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getRecipes = async (req, res) => {
    try {
        const recipes = await recipeModel.findAndCountAll({
            where: { dietId: req.params.id },
            limit: req.query.limit,
            offset: req.query.offset,
            attributes: ['id', 'title'],
            hooks: false,
        });
        return res.success(200, recipes);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getComments = async (req, res) => {
    try {
        const comments = await commentModel.findAndCountAll({
            where: { dietId: req.params.id },
            limit: req.query.limit,
            offset: req.query.offset,
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

        return res.success(200, comments);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.pick = async (req, res) => {
    try {
        if (!req.body.userId) {
            return res.error(400, 'Missing field "userId" in request body');
        }
        const [ affectedRows ] = await userModel.update({
            dietId: req.params.id,
            dietPickDate: new Date(),
        }, {
            where: { id: req.body.userId },
            individualHooks: true,
        });
        if (!affectedRows) {
            return res.error(404, 'No user found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}
