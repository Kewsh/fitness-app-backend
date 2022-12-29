const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('recipeIngredient', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        amount: {
            type: DataTypes.STRING,
            allowNull: false,
        },
    });
}
