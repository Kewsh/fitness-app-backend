const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('nutritionist', {
        fullName: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                is: /^[a-zA-Z\s]*$/,
            },
        },
        picPath: {
            type: DataTypes.STRING,
        },
        description: {
            type: DataTypes.STRING(4096),
        },
        since: {
            type: DataTypes.DATEONLY,
        },
    });
}
