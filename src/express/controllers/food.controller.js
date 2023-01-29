const { getUploadedFilePath } = require('../file-utils');
const { food: foodModel } = require('../../sequelize').models;

module.exports.getCoverPicture = async (req, res) => {
    try {
        const food = await foodModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!food || !food.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(food.coverPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}
