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

            const account = await getAssociatedAccount(foundEmail, password);

            if (!account.user && !account.club) {
                return cb(
                    null,
                    false,
                    {
                        code: 401,
                        message: 'Incorrect email or password',
                    },
                );
            }
            return cb(null, account);

        } catch (error) {
            return cb(error);
        }
    }
);

const getAssociatedAccount = async (email, password) => {
    const account = {};
    const user = email.user;
    if (user) {
        account.user = await validateUser(user, password);
        return account;
    }
    const club = email.club;
    account.club = await validateClub(club, password);
    return account;
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
