const { getUploadedFilePath } = require('../file-utils');
const {
    club: clubModel,
    socialMedia: socialMediaModel,
    program: programModel,
    event: eventModel,
    email: emailModel,
} = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const club = await clubModel.create({
            name: req.body.name,
            manager: req.body.manager,
            description: req.body.description,
            password: req.body.password,
            phoneNumber: req.body.phoneNumber,
            website: req.body.website,
            address: req.body.address,
            email: {
                email: req.body.email,
            },
        }, {
            include: emailModel,
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
            attributes: { exclude: ['coverPicPath'] },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
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
            attributes: { exclude: ['coverPicPath'] },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
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
