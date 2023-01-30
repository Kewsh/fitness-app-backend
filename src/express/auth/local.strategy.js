const localStrategy = require('passport-local');
const {
    email: emailModel,
    user: userModel,
    club: clubModel,
    measurement: measurementModel,
} = require('../../sequelize').models;


module.exports = new localStrategy(
    {
        usernameField: 'email',
        passwordField: 'password',
    },
    async (email, password, cb) => {
        try {
            const foundEmail = await emailModel.findOne({
                where: { email },
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
    
            if (!foundEmail) {
                return cb(
                    null,
                    false,
                    {
                        code: 401,
                        message: 'Incorrect email or password',
                    },
                );
            }

            const user = foundEmail.user;
            const club = foundEmail.club;
    
            const response = user ? await validateUser(user, password):
                                    await validateClub(club, password);
    
            if (!response) {
                return cb(
                    null,
                    false,
                    {
                        code: 401,
                        message: 'Incorrect email or password',
                    },
                );
            }
            return cb(null, response);

        } catch (error) {
            return cb(error);
        }
    }
);

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
