const bcrypt = require('bcrypt');

module.exports = (sequelize) => {
    const {
        club,
        comment,
        diet,
        email,
        event,
        food,
        measurement,
        nutritionist,
        program,
        recipeIngredient,
        recipeReview,
        recipe,
        socialMedia,
        user,
        workout,
    } = sequelize.models;

    // set up instance methods
    user.prototype.isPasswordValid =
        async (password, hash) => await bcrypt.compare(password, hash);

    club.prototype.isPasswordValid =
        async (password, hash) => await bcrypt.compare(password, hash);

    user.hasOne(email, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'userId',
            unique: true,
        },
    });
    email.belongsTo(user);

    club.hasOne(email, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'clubId',
            unique: true,
        },
    });
    email.belongsTo(club);

    // set up associations in pairs
    club.hasMany(socialMedia, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'clubId',
            allowNull: false,
        },
    });
    socialMedia.belongsTo(club);

    club.hasMany(program, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'clubId',
            allowNull: false,
        },
    });
    program.belongsTo(club);

    club.hasMany(event, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'clubId',
            allowNull: false,
        },
    });
    event.belongsTo(club);

    program.hasMany(workout, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'programId',
            allowNull: false,
        },
    });
    workout.belongsTo(program);

    program.hasMany(comment, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'programId',
        },
    });
    comment.belongsTo(program);

    program.hasMany(user, {
        onDelete: 'SET NULL',
        foreignKey: {
            name: 'programId',
        },
    });
    user.belongsTo(program);

    event.belongsToMany(user, { through: 'eventParticipations' });
    user.belongsToMany(event, { through: 'eventParticipations' });

    event.hasMany(comment, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'eventId',
        },
    });
    comment.belongsTo(event);

    diet.hasMany(user, {
        onDelete: 'SET NULL',
        foreignKey: {
            name: 'dietId',
        },
    });
    user.belongsTo(diet);

    diet.hasMany(food, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'dietId',
            allowNull: false,
        },
    });
    food.belongsTo(diet);

    nutritionist.hasMany(diet, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'nutritionistId',
            allowNull: false,
        },
    });
    diet.belongsTo(nutritionist);

    diet.hasMany(comment, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'dietId',
        },
    });
    comment.belongsTo(diet);

    diet.hasMany(recipe, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'dietId',
            allowNull: false,
        },
    });
    recipe.belongsTo(diet);

    recipe.hasMany(recipeIngredient, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'recipeId',
            allowNull: false,
        },
    });
    recipeIngredient.belongsTo(recipe);

    recipe.hasMany(recipeReview, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'recipeId',
            allowNull: false,
        },
    });
    recipeReview.belongsTo(recipe);

    recipeReview.hasOne(comment, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'recipeReviewId',
        },
    });
    comment.belongsTo(recipeReview);

    user.hasMany(comment, {
        onDelete: 'SET NULL',
        foreignKey: {
            name: 'userId',
        },
    });
    comment.belongsTo(user);

    user.hasMany(measurement, {
        onDelete: 'CASCADE',
        foreignKey: {
            name: 'userId',
            allowNull: false,
        },
    });
    measurement.belongsTo(user);
}
