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
            beforeCreate: comment => {
                if (!comment.programId &&
                    !comment.eventId &&
                    !comment.dietId &&
                    !comment.recipeReviewId)
                {
                    throw new Error('Comment must belong to either a program, event, diet, or recipe review');
                }
            },
        },
    });
}
