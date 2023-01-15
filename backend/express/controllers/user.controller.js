const { getUploadedFilePath } = require('../file-utils');
const {
    user: userModel,
    measurement: measurementModel,
    club: clubModel,
} = require('../../sequelize').models;

module.exports.findOne = async (req, res) => {
    try {
        if (!req.body.email || !req.body.password) {
            return res.status(400).json('Missing fields in request body');
        }

        const user = await userModel.findOne({
            where: {
                email: req.body.email,
            },
            include: measurementModel,
            attributes: {
                exclude: ['profilePicPath']
            }
        });

        if (!user) {
            return res.status(404).json('No user found with this email');
        }
        if (!(await user.isPasswordValid(req.body.password, user.password))) {
            return res.status(401).json('Invalid password');
        }

        // exclude some fields from response object
        const { password, profilePicPath, ...userResponse } = user.dataValues;

        return res.status(200).json(userResponse);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.createOne = async (req, res) => {
    try {
        const user = await userModel.create({
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            password: req.body.password,
            measurements: [
                { type: 'WEIGHT' },
                { type: 'BICEP' },
                { type: 'WAIST' },
            ],
        }, { include: measurementModel });

        // exclude some fields from response object
        const { password, profilePicPath, ...userResponse } = user.dataValues;

        return res.status(201).json(userResponse);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getEvents = async (req, res) => {
    try {   
        const user = await userModel.findByPk(req.params.id);
        if (!user) {
            return res.status(404).json('No user found with this id');
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

        return res.status(200).json(events);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getProfilePicture = async (req, res) => {
    try {
        const user = await userModel.findByPk(
            req.params.id,
            { attributes: ['id', 'profilePicPath'] }
        );

        if (!user || !user.profilePicPath) {
            return res.status(404).json('No profile picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(user.profilePicPath));
    } catch (error) {
        return res.status(500).json(error);
    }
}
