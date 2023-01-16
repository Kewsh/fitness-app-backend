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
            return res.status(404).json('No picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(nutritionist.picPath));
    } catch (error) {
        return res.status(500).json(error);
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
        return res.status(200).json(diets)
    } catch (error) {
        return res.status(500).json(error);
    }
}
