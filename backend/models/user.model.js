const { DataTypes } = require('sequelize');
const bcrypt = require('bcrypt');

const userSchema = (sequelize) => {
    sequelize.define('user', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        firstName: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isAlpha: true,
            },
        },
        lastName: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isAlpha: true,
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
                const salt = await bcrypt.genSalt();
                user.password = await bcrypt.hash(user.password, salt);
            }
        }
    });
}

userSchema.prototype.isPasswordValid =
    async (password, hash) => await bcrypt.compare(password, hash);

module.exports = userSchema;
