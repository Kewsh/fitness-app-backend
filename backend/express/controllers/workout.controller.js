const {
    workout: workoutModel,
    program: programModel,
} = require('../../sequelize').models;

module.exports.findOneById = async (req, res) => {
    try {
        const workout = await workoutModel.findByPk(req.params.id, {
            attributes: {
                exclude: ['coverPicPath', 'videoPath']
            },
            include: {
                model: programModel,
                attributes: ['title'],
            },
        });
        if (!workout) {
            return res.status(404).json('No workout found with this id');
        }
        return res.status(200).json(workout);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getCoverPicture = async (req, res) => {

}

module.exports.getInstructionVideo = async (req, res) => {
    
}
