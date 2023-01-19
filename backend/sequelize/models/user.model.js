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
        fullName: {
            type: DataTypes.VIRTUAL,
            get() {
                return `${this.firstName} ${this.lastName}`;
            },
        },
        sex: {
            type: DataTypes.STRING,
            validate: {
                isIn: [[
                    'MALE',
                    'FEMALE',
                    'OTHER',
                ]],
            },
        },
        birthday: {
            type: DataTypes.DATEONLY,
        },
        height: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0
            },
        },
        phoneNumber: {
            type: DataTypes.STRING,
            validate: {
                is: /^\+(?:[0-9] ?){6,14}[0-9]$/,
            },
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
        },
        profilePicPath: {
            type: DataTypes.STRING,
        },
        programEnrolmentDate: {
            type: DataTypes.DATEONLY,         
        },
        dietPickDate: {
            type: DataTypes.DATEONLY,
        },
        clubId: {
            type: DataTypes.VIRTUAL,
        }
    }, {
        hooks: {
            beforeCreate: async user => {
                const salt = await bcrypt.genSalt();
                user.password = await bcrypt.hash(user.password, salt);
            },
            beforeUpdate: async user => {
                // programId and programEnrolmentDate must be set together
                // same goes for dietId and dietPickDate
                if ((user.programId != user.previous('programId') &&
                    !user.programEnrolmentDate) ||
                    (user.dietId != user.previous('dietId') &&
                    !user.dietPickDate))
                {
                    throw new Error("Date of program/diet pick must be given");
                }

                // hash password only if it's been updated
                if (user.password != user.previous('password')) {
                    const salt = await bcrypt.genSalt();
                    user.password = await bcrypt.hash(user.password, salt);
                }
            },
            afterFind: async query => {
                if (query) {
                    // get user's clubId from the program they're enrolled in
                    const program = await sequelize.models.program.findByPk(
                        query.programId,
                        {
                            attributes: ['id', 'clubId'],
                            hooks: false,
                        },
                    );
                    if (program)
                        query.clubId = program.clubId;
                }
            }
        }
    });
}
