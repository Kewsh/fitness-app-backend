const _ = require('lodash');
const { Op } = require('sequelize');
const { getUploadedFilePath } = require('../file-utils');
const {
    club: clubModel,
    socialMedia: socialMediaModel,
    program: programModel,
    event: eventModel,
    user: userModel,
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

module.exports.updateOne = async (req, res) => {
    try {
        const club = await clubModel.findByPk(req.params.id, {
            include: [emailModel, socialMediaModel]
        });

        if (!club) {
            return res.error(404, 'No club found with this id');
        }

        if (req.body.email) {
            club.email.email = req.body.email;
            await club.email.save();
        }

        if (Array.isArray(req.body.socialMedia)) {
            for (const { url, type } of req.body.socialMedia) {
                await socialMediaModel.update({
                    url,
                }, {
                    where: {
                        clubId: club.id,
                        type,
                    },
                });
            }
        }

        // update password only if old password is correct
        let updatedPassword;
        if (
            req.newPassword &&
            req.oldPassword &&
            club.isPasswordValid(req.body.oldPassword, club.password)
        ) {
            updatedPassword = req.body.newPassword;
        }

        await club.update({
            name: req.body.name,
            manager: req.body.manager,
            description: req.body.description,
            since: req.body.since,
            password: updatedPassword,
            phoneNumber: req.body.phoneNumber,
            website: req.body.website,
            address: req.body.address,
        });

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getPrograms = async (req, res) => {
    try {
        // sequelize doesn't accept where clause keys to be undefined and
        // there's no way to use || or something to match all entries if
        // the value is undefined; so we have to build the clause separately
        const where = { clubId: req.params.id };
        if (req.query.exclude) {
            where.id = { [Op.ne]: req.query.exclude };
        }

        const programs = await programModel.findAndCountAll({
            where,
            limit: req.query.limit,
            offset: req.query.offset,
            attributes: { exclude: ['coverPicPath'] },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
        });

        const path = {
            nAthletes: 'nAthletes',
            duration: 'duration',
            price: 'price',
            rating: 'rating.rating',
        }[req.query.order];

        // most orderable-by fields are virtual and are set in the afterFind
        // hook, so we can't use sequelize's order option
        path && orderBy(programs.rows, req.query.sort == 'desc', path);

        return res.success(200, programs);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getEvents = async (req, res) => {
    try {
        const events = await eventModel.findAndCountAll({
            where: { clubId: req.params.id },
            limit: req.query.limit,
            offset: req.query.limit,
            attributes: { exclude: ['coverPicPath'] },
            include: {
                model: clubModel,
                attributes: ['name'],
            },
        });

        const path = {
            nAttendees: 'nAttendees',
            price: 'price',
            startDate: 'startDate',
            endDate: 'endDate',
            rating: 'rating.rating',
        }[req.query.order];

        // most orderable-by fields are virtual and are set in the afterFind
        // hook, so we can't use sequelize's order option
        path && orderBy(events.rows, req.query.sort == 'desc', path);

        return res.success(200, events);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getMembers = async (req, res) => {
    try {
        // if no programId is supplied, get all users of the club
        let programIds;

        if (!req.query.programId) {
            // we can't query on clubId since it's a virtual field.
            // get this club's programs
            programIds = (await programModel.findAll({
                where: {
                    clubId: req.params.id,
                },
                attributes: ['id'],
                hooks: false,
            })).map(program => program.dataValues.id);
        }

        // get all users with req.body.programId or programIds
        const members = await userModel.findAndCountAll({
            where: { programId: req.query.programId || programIds },
            limit: req.query.limit,
            offset: req.query.offset,
            attributes: [
                'id',
                'firstName',
                'lastName',
                'fullName',
                'programEnrolmentDate'
            ],
        });

        return res.success(200, members);
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


const orderBy = (list, desc, path) => {
    list.sort(
        (a, b) => {
            // if NaN, treat as 0
            const aField = _.get(a, path) || 0;
            const bField = _.get(b, path) || 0;
            return desc ? bField - aField : aField - bField;
        }
    );
}
