const { Op } = require('sequelize');
const { getUploadedFilePath, deleteFile } = require('../utils/file.util');
const { getUserId, getClubId } = require('../utils/auth.util');
const upload = require('../multer');
const {
    event: eventModel,
    club: clubModel,
    user: userModel,
    comment: commentModel,
} = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const clubId = getClubId(req.user);
        const event = await eventModel.create({
            title: req.body.title,
            description: req.body.description,
            price: req.body.price,
            maxAttendees: req.body.maxAttendees,
            startDate: req.body.startDate,
            endDate: req.body.endDate,
            clubId,
        });

        // can't do this in include
        event.dataValues.club = await event.getClub({
            attributes: ['id', 'name'],
            hooks: false,
        });

        // exclude coverPicPath from result;
        const { coverPicPath, ...response } = event.dataValues;

        return res.success(201, response);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.updateOne = async (req, res) => {
    try {
        if (
            !req.body.title &&
            !req.body.description &&
            !req.body.price &&
            !req.body.maxAttendees &&
            !req.body.startDate &&
            !req.body.endDate
        ) {
            return res.error(400, 'Missing fields in request body');
        }

        const clubId = getClubId(req.user);
        const [ affectedRows ] = await eventModel.update({
            title: req.body.title,
            description: req.body.description,
            price: req.body.price,
            maxAttendees: req.body.maxAttendees,
            startDate: req.body.startDate,
            endDate: req.body.endDate,
        }, {
            where: {
                id: req.params.id,
                clubId,
            }
        });

        if (!affectedRows) {
            return res.error(404, 'No event found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.discover = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const user = await userModel.findByPk(userId, {
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
        const events = await eventModel.findAndCountAll({
            where: { id: { [Op.not]: eventIds } },
            limit: req.query.limit,
            offset: req.query.offset,
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
        const comments = await commentModel.findAndCountAll({
            where: { eventId: req.params.id },
            limit: req.query.limit,
            offset: req.query.offset,
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

module.exports.setCoverPicture = async (req, res) => {
    const handler = upload.single('picture');

    handler(req, res, async error => {
        if (error) {
            return res.error(500, error.message);
        }
        try {
            const clubId = getClubId(req.user);
            const event = await eventModel.findOne({
                where: {
                    id: req.params.id,
                    clubId,
                },
                attributes: ['id', 'coverPicPath'],
            });

            if (!event) {
                deleteFile(getUploadedFilePath(req.file.filename));
                throw new Error('No event found with this id');
            }
    
            // delete previous picture from database (if exists)
            if (event.coverPicPath)
                deleteFile(getUploadedFilePath(event.coverPicPath));
    
            // update picture name in database
            event.coverPicPath = req.file.filename;
            await event.save();

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
        const event = await eventModel.findOne({
            where: {
                id: req.params.id,
                clubId,
            },
            attributes: ['id', 'coverPicPath'],
        });
    
        if (!event || !event.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        deleteFile(getUploadedFilePath(event.coverPicPath));

        // update picture name in database
        event.coverPicPath = null;
        await event.save();

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.participate = async (req, res) => {
    try {
        // can we do this all in one query?
        const event = await eventModel.findByPk(req.params.id);
        if (!event) {
            return res.error(404, 'No event found with this id');
        }

        const userId = getUserId(req.user);
        const user = await userModel.findByPk(userId);
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
