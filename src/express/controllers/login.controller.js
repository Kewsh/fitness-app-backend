const jwt = require('jsonwebtoken');
const passport = require('../auth');


module.exports.loginByPassword = (req, res) => {
    passport.authenticate(
        'local',
        (err, response, info) => {
            try {
                if (err || !response) {
                    //TODO: 'Missing credentials' ends up with code 500 but should be 400
                    const errorCode = (info && info.code) || 500;
                    const errorMsg = (info && info.message) || err.message;
                    
                    return res.error(errorCode, errorMsg);
                }

                req.login(
                    response,
                    { session: false },
                    (error) => {
                        if (error) {
                            return res.error(500, error.message);
                        }

                        // generate jwt for client to use for upcoming requests
                        const payload = { id: response.id, isUser: !response.name };
                        const token = generateJWT(payload);

                        return res.success(200, { token, response });
                    }
                )

            } catch (error) {
                return res.error(500, error.message);
            }
        }
    )(req, res);
}

const generateJWT = (payload) => {
    return jwt.sign(
        payload,
        'SECRET',
        { expiresIn: '1h' },
    );
}
