const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('recipeIngredient', {
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
