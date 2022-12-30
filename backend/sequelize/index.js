const { Sequelize } = require('sequelize');
const applyAssociations = require('./associations');

const sequelize = new Sequelize('postgres://postgres:123456@localhost:5433/postgres');

const modelDefiners = [
    require('./models/club.model'),
    require('./models/comment.model'),
    require('./models/diet.model'),
    require('./models/event.model'),
    require('./models/food.model'),
    require('./models/nutritionist.model'),
    require('./models/program.model'),
    require('./models/recipe-ingredient.model'),
    require('./models/recipe-review.model'),
    require('./models/recipe.model'),
    require('./models/social-media.model'),
    require('./models/user.model'),
    require('./models/workout.model'),
]

for (const modelDefiner of modelDefiners) {
    modelDefiner(sequelize);
}

applyAssociations(sequelize);

module.exports = sequelize;
