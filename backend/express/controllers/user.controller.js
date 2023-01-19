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
        const events = await user.getEvents({
            attributes: ['id', 'title'],
            include: {
                model: clubModel,
                attributes: ['name'],
                // sequelize bug: https://github.com/sequelize/sequelize/issues/13450
                push: () => {},
            },
            hooks: false,
        });

        return res.success(200, events);
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
