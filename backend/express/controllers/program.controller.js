const { Op } = require('sequelize');
const { getUploadedFilePath } = require('../file-utils');
const {
    program: programModel,
    workout: workoutModel,
    comment: commentModel,
    user: userModel,
    club: clubModel,
} = require('../../sequelize').models;

module.exports.discover = async (req, res) => {
    try {
        const user = await userModel.findByPk(req.body.userId, {
            attributes: ['id', 'programId'],
        });

        if (!user) {
            return res.status(404).json('No user found with this id');
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

        return res.status(200).json(programs);
    } catch (error) {
        return res.status(500).json(error);
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
            return res.status(404).json('No program found with this id');
        }
        return res.status(200).json(program);
    } catch (error) {
        return res.status(500).json(error);
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
        return res.status(200).json(workouts);
    } catch (error) {
        return res.status(500).json(error);
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
        return res.status(200).json(comments);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const program = await programModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!program || !program.coverPicPath) {
            return res.status(404).json('No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(program.coverPicPath));
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.enroll = async (req, res) => {
    try {
        if (!req.body.userId) {
            return res.status(400).json(
                'Missing field "userId" in request body'
            );
        }
        const [ affectedRows ] = await userModel.update({
            programId: req.params.id,
            programEnrolmentDate: new Date(),
        }, {
            where: { id: req.body.userId },
            individualHooks: true,
        });
        if (!affectedRows) {
            return res.status(404).json('No user found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}
