const { getUploadedFilePath, deleteFile } = require('../file-utils');
const upload = require('../multer');
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

module.exports.updateOne = async (req, res) => {
    try {
        if (
            !req.body.title &&
            !req.body.description &&
            !req.body.reps &&
            !req.body.sets &&
            !req.body.setTimeInSeconds &&
            !req.body.burntCalories &&
            !req.body.day
        ) {
            return res.error(400, 'Missing fields in request body');
        }

        // workout must belong to one of your programs
        const clubId = getClubId(req.user);
        const programIds = await getProgramIds(clubId);

        const [ affectedRows ] = await workoutModel.update({
            title: req.body.title,
            description: req.body.description,
            reps: req.body.reps,
            sets: req.body.sets,
            setTimeInSeconds: req.body.setTimeInSeconds,
            burntCalories: req.body.burntCalories,
            day: req.body.day,
        }, {
            where: {
                id: req.params.id,
                programId: programIds,
            },
            individualHooks: true,
        });

        if (!affectedRows) {
            return res.error(404, 'No workout found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.deleteOne = async (req, res) => {
    try {``
        const clubId = getClubId(req.user);
        const programIds = await getProgramIds(clubId);

        const destroyedRows = await workoutModel.destroy({
            where: {
                id: req.params.id,
                programId: programIds,
            }
        });

        if (!destroyedRows) {
            return res.error(404, 'No workout found with this id');
        }

        return res.success(200, {});
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

module.exports.setCoverPicture = async (req, res) => {
    const handler = upload.single('picture');

    handler(req, res, async error => {
        if (error) {
            return res.error(500, error.message);
        }
        try {
            const clubId = getClubId(req.user);
            const programIds = await getProgramIds(clubId);

            const workout = await workoutModel.findOne({
                where: {
                    id: req.params.id,
                    programId: programIds,
                },
                attributes: ['id', 'coverPicPath'],
            });

            if (!workout) {
                deleteFile(getUploadedFilePath(req.file.filename));
                throw new Error('No workout found with this id');
            }
    
            // delete previous picture from database (if exists)
            if (workout.coverPicPath)
                deleteFile(getUploadedFilePath(workout.coverPicPath));
    
            // update picture name in database
            workout.coverPicPath = req.file.filename;
            await workout.save();

            return res.success(200, {});
        } catch (error) {
            // abort file upload
            deleteFile(getUploadedFilePath(req.file.filename));

            return res.error(500, error.message);
        }
    });
}

module.exports.deleteCoverPicture = async (req, res) => {
    try {
        const clubId = getClubId(req.user);
        const programIds = await getProgramIds(clubId);

        const workout = await workoutModel.findOne({
            where: {
                id: req.params.id,
                programId: programIds,
            },
            attributes: ['id', 'coverPicPath'],
        });
    
        if (!workout || !workout.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        deleteFile(getUploadedFilePath(workout.coverPicPath));

        // update picture name in database
        workout.coverPicPath = null;
        await workout.save();

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

const getClubId = (user) => {
    // <null> can be used for query methods, while <false> cannot
    return !user.isUser ? user.id : null;
}

const getProgramIds = async (clubId) => {
    const programIds = (await programModel.findAll({
        where: { clubId },
        attributes: ['id'],
        hooks: false,
    })).map(program => program.dataValues.id);

    return programIds;
}
