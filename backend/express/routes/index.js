module.exports = (app) => {
    app.use('/club', require('./api/club.routes')),
    app.use('/comment', require('./api/comment.routes'));
    app.use('/diet', require('./api/diet.routes'));
    app.use('/event', require('./api/event.routes'));
    app.use('/food', require('./api/food.routes'));
    app.use('/measurement', require('./api/measurement.routes'));
    app.use('/nutritionist', require('./api/nutritionist.routes'));
    app.use('/program', require('./api/program.routes'));
    app.use('/recipe-review', require('./api/recipe-review.routes'));
    app.use('/recipe', require('./api/recipe.routes'));
    app.use('/user', require('./api/user.routes'));
    app.use('/workout', require('./api/workout.routes'));
}
