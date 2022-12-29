const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('socialMedia', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        type: {
            type: DataTypes.STRING,
            allowNull: false,
            values: [
                'TWITTER',
                'INSTAGRAM',
                'FACEBOOK',
                'TIKTOK',
                'TELEGRAM',
                'YOUTUBE',
            ],
        },
        url: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isUrl: true,
            },
        },
    });
}
