const {
    club: clubModel,
    socialMedia: socialMediaModel,
    program: programModel,
    event: eventModel,
} = require('../../sequelize').models;

module.exports.findOne = async (req, res) => {
    try {
        if (!req.body.email || !req.body.password) {
            return res.status(400).json('Missing fields in request body');
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
            return res.status(404).json('No Club found with this email');
        }
        if (!(await club.isPasswordValid(req.body.password, club.password))) {
            return res.status(401).json('Invalid password');
        }

        // exclude password from response object
        const { password, ...userResponse } = club.dataValues;

        return res.status(200).json(userResponse);
    } catch (error) {
        return res.status(500).json(error);
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

        // exclude password from response object
        const { password, ...userResponse } = club.dataValues;
        
        return res.status(201).json(userResponse);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.findOneById = async(req, res) => {
    try {
        const club = await clubModel.findByPk(req.params.id, {
            attributes: { exclude: ['coverPicPath', 'logoPath', 'password'] },
            include: socialMediaModel,
        });

        if (!club) {
            return res.status(404).json('No Club found with this id');
        }
        return res.status(200).json(club);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getPrograms = async (req, res) => {
    try {
        const programs = await programModel.findAll({
            where: { clubId: req.params.id },
            attributes: { exclude: ['coverPicPath'] },
        });
        return res.status(200).json(programs);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getEvents = async (req, res) => {
    try {
        const events = await eventModel.findAll({
            where: { clubId: req.params.id },
            attributes: { exclude: ['coverPicPath'] },
        });
        return res.status(200).json(events);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getCoverPicture = async (req, res) => {
    
}

module.exports.getLogo = async (req, res) => {
    
}
