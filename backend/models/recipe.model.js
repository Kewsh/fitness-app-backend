const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('recipe', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        description: {
            type: DataTypes.STRING(4096),
            allowNull: false,
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        origin: {
            type: DataTypes.STRING,
        },
        prepTimeInMinutes: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0,
            },
        },
        servings: {
            type: DataTypes.INTEGER,
            validate: {
                min: 1,
            },
        },
        price: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0,
            },
        },
        stepByStepGuide: {
            type: DataTypes.STRING(65000),
        },
    });
}
