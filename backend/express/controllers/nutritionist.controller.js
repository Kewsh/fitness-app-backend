const { diet: dietModel } = require('../../sequelize').models;

module.exports.getPicture = async (req, res) => {

}

module.exports.getDiets = async (req, res) => {
    try {
        const diets = await dietModel.findAll({
            where: { nutritionistId: req.params.id }
        });
        return res.status(200).json(diets)
    } catch (error) {
        return res.status(500).json(error);
    }
}
