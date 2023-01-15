const { getUploadedFilePath } = require('../file-utils');
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
                attributes: ['id', 'title'],
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
    try {
        const workout = await workoutModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!workout || !workout.coverPicPath) {
            return res.status(404).json('No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(workout.coverPicPath));
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getInstructionVideo = async (req, res) => {
    
}
