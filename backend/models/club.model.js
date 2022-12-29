const { DataTypes } = require('sequelize');
const bcrypt = require('bcrypt');

const clubSchema = (sequelize) => {
    sequelize.define('club', {
        id: {
            type: DataTypes.INTEGER,
            autoIncrementIdentity: true,
            primaryKey: true,
        },
        name: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isAlpha: true,
            },
        },
        manager: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isAlpha: true,
            },
        },
        description: {
            type: DataTypes.STRING(4096),
        },
        coverPicPath: {
            type: DataTypes.STRING,
        },
        logoPath: {
            type: DataTypes.STRING,
        },
        since: {
            type: DataTypes.DATE,
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isEmail: true,
            },
        },
        isEmailPublic: {
            type: DataTypes.BOOLEAN,
            defaultValue: false,
        },
        phoneNumber: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                is: /^\+(?:[0-9] ?){6,14}[0-9]$/,
            },
        },
        website: {
            type: DataTypes.STRING,
            validate: {
                isUrl: true,
            },
        },
        address: {
            type: DataTypes.STRING(1024),
        },
    }, {
        hooks: {
            beforeCreate: async club => {
                const salt = await bcrypt.genSalt();
                club.password = await bcrypt.hash(club.password, salt);
            },
            beforeUpdate: async club => {
                const salt = await bcrypt.genSalt();
                club.password = await bcrypt.hash(club.password, salt);
            }
        }
    });
}

clubSchema.prototype.isPasswordValid =
    async (password, hash) => await bcrypt.compare(password, hash);

module.exports = clubSchema;
