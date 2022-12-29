const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('eventParticipation', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
    });
}
