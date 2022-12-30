const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('comment', {
        text: {
            type: DataTypes.STRING(1024),
            allowNull: false,
        },
        rate: {
            type: DataTypes.INTEGER,
            validate: {
                max: 500,
                min: 0,
            },
        },
        on: {
            type: DataTypes.STRING,
            allowNull: false,
            values: [
                'PROGRAM',
                'DIET',
                'RECIPE',
                'EVENT',
            ],
        },
    });
}
