const {
    email: emailModel,
    user: userModel,
    club: clubModel,
    measurement: measurementModel,
} = require('../../sequelize').models;

module.exports.findOne = async (req, res) => {
    try {
        if (!req.body.email || !req.body.password) {
            return res.error(400, 'Missing fields in request body');
        }
        const email = await emailModel.findOne({
            where: { email: req.body.email },
            include: [
                {
                    model: userModel,
                    include: measurementModel,
                    attributes: {
                        exclude: ['profilePicPath']
                    }
                },
                {
                    model: clubModel,
                    attributes: {
                        exclude: ['coverPicPath', 'logoPath'],
                    },
                },
            ],
        });

        if (!email) {
            return res.error(404, 'No user or club found with this email');
        }

        const user = email.user;
        const club = email.club;

        const response = user ? await validateUser(user, req.body.password):
                                await validateClub(club, req.body.password);

        if (!response) {
            return res.error(401, 'Invalid password');
        }
        return res.success(200, response);
    } catch (error) {
        return res.error(500, error.message);
    }
}


const validateUser = async (user, pass) => {
    if (!(await user.isPasswordValid(pass, user.password))) {
        return null;
    }

    // exclude password from response object
    const { password, ...response } = user.dataValues;

    return response;
}

const validateClub = async (club, pass) => {
    if (!(await club.isPasswordValid(pass, club.password))) {
        return null;
    }

    // exclude password from response object
    const { password, ...response } = club.dataValues;

    return response;
}
