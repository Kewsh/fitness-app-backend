const { Op } = require('sequelize');
const { getUploadedFilePath, deleteFile } = require('../file-utils');
const upload = require('../multer');
const {
    program: programModel,
    workout: workoutModel,
    comment: commentModel,
    user: userModel,
    club: clubModel,
} = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const program = await programModel.create({
            title: req.body.title,
            description: req.body.description,
            coachName: req.body.coachName,
            price: req.body.price,
            clubId: req.body.clubId,
            workouts: req.body.workouts && req.body.workouts.map(workout => ({
                title: workout.title,
                description: workout.description,
                sets: workout.sets,
                reps: workout.reps,
                setTimeInSeconds: workout.setTimeInSeconds,
                burntCalories: workout.burntCalories,
                day: workout.day,
            })),
        }, {
            include: workoutModel,
        });

        return res.success(201, program);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.updateOne = async (req, res) => {
    try {
        if (
            !req.body.title &&
            !req.body.description &&
            !req.body.coachName &&
            !req.body.price &&
            !req.body.workouts
        ) {
            return res.error(400, 'Missing fields in request body');
        }

        const program = await programModel.findByPk(req.params.id);
        if (!program) {
            return res.error(404, 'No program found with this id');
        }

        // only create new ones. deletions and updates can happen through
        // workout's own routes
        await workoutModel.bulkCreate(req.body.workouts.map(
            workout => ({
                title: workout.title,
                description: workout.description,
                sets: workout.sets,
                reps: workout.reps,
                setTimeInSeconds: workout.setTimeInSeconds,
                burntCalories: workout.burntCalories,
                day: workout.day,
                programId: program.id,
            })
        ));

        await program.update({
            title: req.body.title,
            description: req.body.description,
            coachName: req.body.coachName,
            price: req.body.price,
        });

        return res.success(200, {});
    } catch (error) {
        console.log(error);
        return res.error(500, error.message);
    }
}

module.exports.discover = async (req, res) => {
    try {
        const user = await userModel.findByPk(req.body.userId, {
            attributes: ['id', 'programId'],
        });

        if (!user) {
            return res.error(404, 'No user found with this id');
        }
        const programId = user.programId;

        // find all programs user hasn't picked
        const programs = await programModel.findAll({
            where: { id: { [Op.not]: programId } },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
            attributes: ['id', 'title'],
            hooks: false,
        })

        return res.success(200, programs);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.findOneById = async (req, res) => {
    try {
        const program = await programModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
        });

        // this can't be done in include, since hooks don't run on
        // included models
        program.dataValues.club = await program.getClub({
            attributes: { exclude: [
                'coverPicPath',
                'logoPath',
                'password',
                'email',
                'createdAt',
                'updatedAt',
            ]},
        });

        if (!program) {
            return res.error(404, 'No program found with this id');
        }
        return res.success(200, program);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getWorkouts = async (req, res) => {
    try {
        const workouts = await workoutModel.findAll({
            where: { programId: req.params.id },
            attributes: [
                'id',
                'title',
                'sets',
                'reps',
                'setTimeInSeconds',
                'setsAndReps',
                'day'
            ],
        });
        return res.success(200, workouts);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getComments = async (req, res) => {
    try {
        const comments = await commentModel.findAll({
            where: { programId: req.params.id },
            include: {
                model: userModel,
                attributes: [
                    'id',
                    'firstName',
                    'lastName',
                    'fullName'
                ],
            },
        });
        return res.success(200, comments);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const program = await programModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!program || !program.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(program.coverPicPath));
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
            const program = await programModel.findByPk(
                req.params.id,
                { attributes: ['id', 'coverPicPath'] },
            );

            if (!program) {
                deleteFile(getUploadedFilePath(req.file.filename));
                throw new Error('No program found with this id');
            }
    
            // delete previous picture from database (if exists)
            if (program.coverPicPath)
                deleteFile(getUploadedFilePath(program.coverPicPath));
    
            // update picture name in database
            program.coverPicPath = req.file.filename;
            await program.save();

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
        const program = await programModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );
    
        if (!program || !program.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        deleteFile(getUploadedFilePath(program.coverPicPath));

        // update picture name in database
        program.coverPicPath = null;
        await program.save();

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.enroll = async (req, res) => {
    try {
        if (!req.body.userId) {
            return res.error(400, 'Missing field "userId" in request body');
        }
        const [ affectedRows ] = await userModel.update({
            programId: req.params.id,
            programEnrolmentDate: new Date(),
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
