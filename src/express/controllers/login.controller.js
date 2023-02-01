const jwt = require('jsonwebtoken');
const passport = require('../auth');


module.exports.loginByPassword = (req, res) => {
    passport.authenticate(
        'local',
        (err, account, info) => {
            try {
                if (err || !account) {
                    //TODO: 'Missing credentials' ends up with code 500 but should be 400
                    const errorCode = (info && info.code) || 500;
                    const errorMsg = (info && info.message) || err.message;
                    
                    return res.error(errorCode, errorMsg);
                }

                req.login(
                    account,
                    { session: false },
                    (error) => {
                        if (error) {
                            return res.error(500, error.message);
                        }
                        const payload = generatePayload(account);
                        const token = generateJWT(payload);
                        const response = generateResponse(token, account);

                        return res.success(200, response);
                    }
                )

            } catch (error) {
                return res.error(500, error.message);
            }
        }
    )(req, res);
}

const generateResponse = (token, account) => {
    const response = { token };
    if (account.user) {
        response.user = account.user;
        return response;
    }
    response.club = account.club;
    return response;
}

const generatePayload = (account) => {
    const payload = {};
    if (account.user) {
        payload.id = account.user.id;
        payload.isUser = true;
        return payload;
    }
    // account is club
    payload.id = account.club.id;
    payload.isUser = false;
    return payload;
}

const generateJWT = (payload) => {
    return jwt.sign(
        payload,
        'SECRET',
        { expiresIn: '1h' },
    );
}
