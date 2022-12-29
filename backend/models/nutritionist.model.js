const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('nutritionist', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        fullName: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isAlpha: true,
            },
        },
        picPath: {
            type: DataTypes.STRING,
        },
        description: {
            type: DataTypes.STRING(4096),
        },
        since: {
            type: DataTypes.DATE,
        },
    });
}
