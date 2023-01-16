const { getUploadedFilePath } = require('../file-utils');
const {
    club: clubModel,
    socialMedia: socialMediaModel,
    program: programModel,
    event: eventModel,
} = require('../../sequelize').models;

module.exports.findOne = async (req, res) => {
    try {
        if (!req.body.email || !req.body.password) {
            return res.error(400, 'Missing fields in request body');
        }

        const club = await clubModel.findOne({
            where: {
                email: req.body.email,
            },
            attributes: {
                exclude: ['coverPicPath', 'logoPath'],
            },
        });

        if (!club) {
            return res.error(404, 'No club found with this email');
        }
        if (!(await club.isPasswordValid(req.body.password, club.password))) {
            return res.error(401, 'Invalid password');
        }

        // exclude some fields from response
        const {
            password,
            coverPicPath,
            logoPath,
            ...userResponse
        } = club.dataValues;

        return res.success(200, userResponse)
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.createOne = async (req, res) => {
    try {
        const club = await clubModel.create({
            name: req.body.name,
            manager: req.body.manager,
            description: req.body.description,
            email: req.body.email,
            password: req.body.password,
            phoneNumber: req.body.phoneNumber,
            website: req.body.website,
            address: req.body.address,
        });

        // exclude some fields from response
        const {
            password,
            coverPicPath,
            logoPath,
            ...userResponse
        } = club.dataValues;
        
        return res.success(201, userResponse);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.findOneById = async(req, res) => {
    try {
        const club = await clubModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath', 'logoPath', 'password'] },
            include: socialMediaModel,
        });

        if (!club) {
            return res.error(404, 'No club found with this id');
        }
        return res.success(200, club);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getPrograms = async (req, res) => {
    try {
        const programs = await programModel.findAll({
            where: { clubId: req.params.id },
            attributes: ['id', 'title'],
            include: {
                model: clubModel,
                attributes: ['name'],
            },
            hooks: false,
        });
        return res.success(200, programs);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getEvents = async (req, res) => {
    try {
        const events = await eventModel.findAll({
            where: { clubId: req.params.id },
            attributes: ['id', 'title'],
            include: {
                model: clubModel,
                attributes: ['name'],
            },
            hooks: false,
        });
        return res.success(200, events);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    try {
        const club = await clubModel.findByPk(
            req.params.id,
            { attributes: ['id', 'coverPicPath'] }
        );

        if (!club || !club.coverPicPath) {
            return res.error(404, 'No cover picture found');
        }

        //TODO: send both body and file? how?
        res.status(200)
           .sendFile(getUploadedFilePath(club.coverPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getLogo = async (req, res) => {
    try {
        const club = await clubModel.findByPk(
            req.params.id,
            { attributes: ['id', 'logoPath'] }
        );

        if (!club || !club.logoPath) {
            return res.error(404, 'No logo found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(club.logoPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}
