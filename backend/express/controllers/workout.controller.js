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
            return res.error(404, 'No workout found with this id');
        }
        return res.success(200, workout);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const workout = await workoutModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!workout || !workout.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(workout.coverPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getInstructionVideo = async (req, res) => {
    
}
