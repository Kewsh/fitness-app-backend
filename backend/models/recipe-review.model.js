const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('recipeReview', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        reviewPicPath: {
            type: DataTypes.STRING,
        },
    });
}
