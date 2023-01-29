const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('comment', {
        text: {
            type: DataTypes.STRING(1024),
            allowNull: false,
        },
        rate: {
            type: DataTypes.INTEGER,
            validate: {
                max: 50,
                min: 0,
            },
        },
    }, {
        hooks: {
            beforeCreate: ({userId, programId, eventId, dietId, recipeReviewId}) => {
                // at the time of creation, the comment must belong to some user
                // later, however, the comment can belong to a ghost (deleted user)
                if (!userId) {
                    throw new Error('Comment must belong to some user');
                }

                // see if comment belongs to only one thing (program, diet, etc)
                if (onlyOneIsSet(programId, eventId, dietId, recipeReviewId)) {
                    throw new Error('Comment must belong to either a program, event, diet, or recipe review');
                }
            },
        },
    });
}


const onlyOneIsSet = (...args) => {
    return args.map(arg => arg ? 1 : 0).reduce((a, b) => a+b, 0) != 1;
}
