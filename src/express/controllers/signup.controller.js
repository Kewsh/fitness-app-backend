const sequelize = require('../../sequelize');
const {
    club: clubModel,
    socialMedia: socialMediaModel,
    user: userModel,
    measurement: measurementModel,
    email: emailModel,
} = sequelize.models;


module.exports.signUpUser = async (req, res) => {
    const transaction = await sequelize.transaction();

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
            ],
            transaction,
        });

        // exclude some fields from response object
        const { password, profilePicPath, ...response } = user.dataValues;

        await transaction.commit();
        return res.success(201, response);
    } catch (error) {
        await transaction.rollback();
        return res.error(500, error.message);
    }
}

module.exports.signUpClub = async (req, res) => {
    const transaction = await sequelize.transaction();

    try {
        const club = await clubModel.create({
            name: req.body.name,
            manager: req.body.manager,
            description: req.body.description,
            password: req.body.password,
            phoneNumber: req.body.phoneNumber,
            website: req.body.website,
            address: req.body.address,
            socialMedia: [
                { type: 'TWITTER' },
                { type: 'INSTAGRAM' },
                { type: 'FACEBOOK' },
                { type: 'TIKTOK' },
                { type: 'TELEGRAM' },
                { type: 'YOUTUBE' },
            ],
            email: {
                email: req.body.email,
            },
        }, {
            include: [
                socialMediaModel,
                emailModel,
            ],
            transaction,
        });

        // exclude some fields from response
        const {
            password,
            coverPicPath,
            logoPath,
            ...response
        } = club.dataValues;

        await transaction.commit();
        return res.success(201, response);
    } catch (error) {
        await transaction.rollback();
        return res.error(500, error.message);
    }
}
