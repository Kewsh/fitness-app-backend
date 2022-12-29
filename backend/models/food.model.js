const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('food', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        amount: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        day: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 1,
                max: 366,
            },
        },
    });
}
