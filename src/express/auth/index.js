const passport = require('passport');
const localStrategy = require('./local.strategy');
const JWTStrategy = require('./jwt.strategy');

// register authentication strategies
passport.use(localStrategy);
passport.use(JWTStrategy);

module.exports = passport;
