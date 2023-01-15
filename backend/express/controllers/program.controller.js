const {
    program: programModel,
    workout: workoutModel,
    comment: commentModel,
    user: userModel,
} = require('../../sequelize').models;

module.exports.discover = async (req, res) => {

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
