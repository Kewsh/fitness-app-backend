const {
    event: eventModel,
    club: clubModel,
    user: userModel,
    comment: commentModel,
} = require('../../sequelize').models;

module.exports.discover = async (req, res) => {

}

module.exports.findOneById = async (req, res) => {
    try {
        const event = await eventModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
        });
        if (!event) {
            return res.status(404).json('No event found with this id');
        }
        return res.status(200).json(event);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getComments = async (req, res) => {
    try {
        const comments = await commentModel.findAll({
            where: { eventId: req.params.id },
            include: {
                model: userModel,
                attributes: [
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

module.exports.participate = async (req, res) => {
    try {
        if (!req.body.userId) {
            return res.status(400).json(
                'Missing field "userId" in request body'
            );
        }
        // can we do this all in one query?
        const event = await eventModel.findByPk(req.params.id);
        if (!event) {
            return res.status(404).json('No event found with this id');
        }
        const user = await userModel.findByPk(req.body.userId);
        if (!user) {
            return res.status(404).json('No user found with this id');
        }
        const participation = await event.addUser(user);
        if (!participation) {
            return res.status(400).json(
                'User has already participated in this event'
            );
        }
        return res.status(200).json(participation);
    } catch (error) {
        return res.status(500).json(error);
    }
}
