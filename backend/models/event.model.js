const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('event', {
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
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        price: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 0,
            },
        },
        maxAttendees: {
            type: DataTypes.INTEGER,
            validate: {
                min: 1,
            },
        },
        startDate: {
            type: DataTypes.Date,
        },
        endDate: {
            type: DataTypes.Date,
        },
    });
}
