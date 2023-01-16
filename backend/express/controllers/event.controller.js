const { Op } = require('sequelize');
const { getUploadedFilePath } = require('../file-utils');
const {
    event: eventModel,
    club: clubModel,
    user: userModel,
    comment: commentModel,
} = require('../../sequelize').models;

module.exports.discover = async (req, res) => {
    try {
        const user = await userModel.findByPk(req.body.userId, {
            attributes: ['id'],
            include: {
                model: eventModel,
                attributes: ['id']
            }
        });

        if (!user) {
            return res.error(404, 'No user found with this id');
        }
        const eventIds = user.dataValues.events.map(event => event.id);

        // find all events user hasn't participated in
        const events = await eventModel.findAll({
            where: { id: { [Op.not]: eventIds } },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
            attributes: ['id', 'title'],
            hooks: false,
        })

        return res.success(200, events);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.findOneById = async (req, res) => {
    try {
        const event = await eventModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath'] },
            include: {
                model: clubModel,
                attributes: ['id', 'name'],
            },
        });
        if (!event) {
            return res.error(404, 'No event found with this id');
        }
        return res.success(200, event);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getComments = async (req, res) => {
    try {
        const comments = await commentModel.findAll({
            where: { eventId: req.params.id },
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
        const event = await eventModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!event || !event.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(event.coverPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.participate = async (req, res) => {
    try {
        if (!req.body.userId) {
            return res.error(400, 'Missing field "userId" in request body');
        }
        // can we do this all in one query?
        const event = await eventModel.findByPk(req.params.id);
        if (!event) {
            return res.error(404, 'No event found with this id');
        }

        const user = await userModel.findByPk(req.body.userId);
        if (!user) {
            return res.error(404, 'No user found with this id');
        }

        const participation = await event.addUser(user);
        if (!participation) {
            return res.error(
                400,
                'User has already participated in this event'
            );
        }

        return res.success(200, participation);
    } catch (error) {
        return res.error(500, error.message);
    }
}
