const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('food', {
        title: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        amount: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        amountAndTitle: {
            type: DataTypes.VIRTUAL,
            get() {
                return `${this.amount} ${this.title}`;
            },
        },
        day: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 1,
                max: 366,
            },
        },
    });
}
