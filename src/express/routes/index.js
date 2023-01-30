const passport = require('passport');

module.exports = (app) => {
    // unauthenticated routes
    app.use('/login', require('./api/login.routes'));
    app.use('/signup', require('./api/signup.routes'));
    
    // authenticated routes;
    app.use(
        '/club',
        passport.authenticate('jwt', { session: false }),
        require('./api/club.routes')
    ),
    app.use(
        '/comment',
        passport.authenticate('jwt', { session: false }),
        require('./api/comment.routes')
    );
    app.use(
        '/diet',
        passport.authenticate('jwt', { session: false }),
        require('./api/diet.routes')
    );
    app.use(
        '/event',
        passport.authenticate('jwt', { session: false }),
        require('./api/event.routes')
    );
    app.use(
        '/food',
        passport.authenticate('jwt', { session: false }),
        require('./api/food.routes')
    );
    app.use(
        '/nutritionist',
        passport.authenticate('jwt', { session: false }),
        require('./api/nutritionist.routes')
    );
    app.use(
        '/program',
        passport.authenticate('jwt', { session: false }),
        require('./api/program.routes')
    );
    app.use(
        '/recipe-review',
        passport.authenticate('jwt', { session: false }),
        require('./api/recipe-review.routes')
    );
    app.use(
        '/recipe',
        passport.authenticate('jwt', { session: false }),
        require('./api/recipe.routes')
    );
    app.use(
        '/user',
        passport.authenticate('jwt', { session: false }),
        require('./api/user.routes')
    );
    app.use(
        '/workout',
        passport.authenticate('jwt', { session: false }),
        require('./api/workout.routes')
    );
}
