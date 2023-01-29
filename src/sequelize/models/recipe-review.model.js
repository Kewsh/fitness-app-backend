const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('recipeReview', {
        reviewPicPath: {
            type: DataTypes.STRING,
        },
    });
}
