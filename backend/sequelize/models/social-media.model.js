const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('socialMedia', {
        type: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isIn: [
                    'TWITTER',
                    'INSTAGRAM',
                    'FACEBOOK',
                    'TIKTOK',
                    'TELEGRAM',
                    'YOUTUBE',
                ],
            },
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
