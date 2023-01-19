const { getUploadedFilePath } = require('../file-utils');
const {
    user: userModel,
    measurement: measurementModel,
    club: clubModel,
    email: emailModel,
} = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const user = await userModel.create({
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            password: req.body.password,
            measurements: [
                { type: 'WEIGHT' },
                { type: 'BICEP' },
                { type: 'WAIST' },
            ],
            email: {
                email: req.body.email
            }
        }, {
            include: [
                measurementModel,
                emailModel,
            ]
        });

        // exclude some fields from response object
        const { password, profilePicPath, ...userResponse } = user.dataValues;

        return res.success(201, userResponse);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getEvents = async (req, res) => {
    try {   
        const user = await userModel.findByPk(req.params.id);
        if (!user) {
            return res.error(404, 'No user found with this id');
        }

        // don't have something like getEventsAndCount
        const count = await user.countEvents({ hooks: false });
        const events = await user.getEvents({
            attributes: ['id', 'title'],
            limit: req.query.limit,
            offset: req.query.offset,
            include: [{
                model: clubModel,
                attributes: ['name'],
            }],
            hooks: false,
        });

        return res.success(200, { count, rows: events });
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getProfilePicture = async (req, res) => {
    try {
        const user = await userModel.findByPk(
            req.params.id,
            { attributes: ['id', 'profilePicPath'] }
        );

        if (!user || !user.profilePicPath) {
            return res.error(404, 'No profile picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(user.profilePicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}
