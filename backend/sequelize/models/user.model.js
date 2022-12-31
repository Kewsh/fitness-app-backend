const { DataTypes } = require('sequelize');
const bcrypt = require('bcrypt');

module.exports = (sequelize) => {
    sequelize.define('user', {
        firstName: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                is: /^[a-zA-Z\s]*$/,
            },
        },
        lastName: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                is: /^[a-zA-Z\s]*$/,
            },
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isEmail: true,
            },
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        profilePicPath: {
            type: DataTypes.STRING,
        },
        weightInDg: {
            type: DataTypes.INTEGER,
            validate: {
                min: 100,
            },
        },
        programEnrolmentDate: {
            type: DataTypes.DATEONLY,         
        },
        dietPickDate: {
            type: DataTypes.DATEONLY,
        },
        targetWeightInDg: {
            type: DataTypes.INTEGER,
            validate: {
                min: 100,
            },
        },
        waistWidthInMm: {
            type: DataTypes.INTEGER,
            validate: {
                min: 10,
            },
        },
        targetWaistWidthInMm: {
            type: DataTypes.INTEGER,
            validate: {
                min: 10,
            },
        },
        bicepWidthInMm: {
            type: DataTypes.INTEGER,
            validate: {
                min: 10,
            },
        },
        targetBicepWidthInMm: {
            type: DataTypes.INTEGER,
            validate: {
                min: 10,
            },
        },
    }, {
        hooks: {
            beforeCreate: async user => {
                const salt = await bcrypt.genSalt();
                user.password = await bcrypt.hash(user.password, salt);
            },
            beforeUpdate: async user => {
                // programId and programEnrolmentDate must be set together
                // same goes for dietId and dietPickDate
                if ((user.programId && !user.programEnrolmentDate) ||
                    (!user.programId && user.programEnrolmentDate) ||
                    (user.dietId && !user.dietPickDate) ||
                    (!user.dietId && user.dietPickDate))
                {
                    throw new Error("Date of program/diet pick must be given");
                }

                const salt = await bcrypt.genSalt();
                user.password = await bcrypt.hash(user.password, salt);
            },
        },
    });
}
