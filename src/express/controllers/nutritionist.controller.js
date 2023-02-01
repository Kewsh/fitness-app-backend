const { Op } = require('sequelize');
const { getUploadedFilePath } = require('../utils/file.util');
const {
    diet: dietModel,
    nutritionist: nutritionistModel,
} = require('../../sequelize').models;

module.exports.getPicture = async (req, res) => {
    try {
        const nutritionist = await nutritionistModel.findByPk(
            req.params.id,
            { attributes: ['id', 'picPath'] }
        );

        if (!nutritionist || !nutritionist.picPath) {
            return res.error(404, 'No picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(nutritionist.picPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getDiets = async (req, res) => {
    try {
        const where = { nutritionistId: req.params.id };
        if (req.query.exclude) {
            where.id = { [Op.ne]: req.query.exclude };
        }

        const diets = await dietModel.findAndCountAll({
            where,
            limit: req.query.limit,
            offset: req.query.offset,
            include: {
                model: nutritionistModel,
                attributes: ['fullName'],
            },
            attributes: ['id', 'title'],
            hooks: false,
        });

        return res.success(200, diets);
    } catch (error) {
        return res.error(500, error.message);
    }
}
