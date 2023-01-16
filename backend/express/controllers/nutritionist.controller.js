const { getUploadedFilePath } = require('../file-utils');
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
        const diets = await dietModel.findAll({
            where: { nutritionistId: req.params.id },
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
