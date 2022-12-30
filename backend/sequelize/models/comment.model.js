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
            beforeCreate: ({programId, eventId, dietId, recipeReviewId}) => {
                // make sure only one of the IDs is defined
                if ([
                        programId ? 1 : 0,
                        eventId ? 1 : 0,
                        dietId ? 1 : 0,
                        recipeReviewId ? 1 : 0
                    ].reduce((a,b) => a+b, 0) != 1)
                {
                    throw new Error('Comment must belong to either a program, event, diet, or recipe review');
                }
            },
        },
    });
}
