const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('email', {
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isEmail: true,
            },
        },
    }, {
        hooks: {
            beforeCreate: email => {
                if ((email.userId && email.clubId) ||
                    (!email.userId && !email.clubId))
                {
                    throw new Error("Email must belong either to a user or to a club");
                }
            },
        },
    });
}
