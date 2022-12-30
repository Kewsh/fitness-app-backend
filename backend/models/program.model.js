const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('program', {
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        description: {
            type: DataTypes.STRING(4096),
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        coachName: {
            type: DataTypes.STRING,
            validate: {
                isAlpha: true,
            }
        },
        price: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 0,
            },
        },
    });
}
