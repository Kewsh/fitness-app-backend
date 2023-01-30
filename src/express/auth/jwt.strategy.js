const { Strategy: JWTStrategy, ExtractJwt } = require('passport-jwt');
const {
    email: emailModel,
    user: userModel,
    club: clubModel,
    measurement: measurementModel,
} = require('../../sequelize').models;

module.exports = new JWTStrategy(
    {
        secretOrKey: 'SECRET',
        jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
    },
    async (token, cb) => {
        try {
            // token is issued to user account
            if (token.isUser) {
                let user = await userModel.findByPk(
                    token.id,
                    { include: [emailModel, measurementModel] },
                )
                if (!user) {
                    cb(null, false);
                }

                return cb(null, { id: user.id, isUser: true });
            }

            // token is issued to club account
            const club = await clubModel.findByPk(
                token.id,
                { include: emailModel },
            );
            if (!club) {
                cb(null, false);
            }

            //TODO: would be nice if we could put this data in req.club instead of req.user
            return cb(null, { id: club.id, isUser: false });
        } catch (error) {
            return cb(error);
        }
    }
);
