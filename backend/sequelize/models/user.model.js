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
        programEnrolmentDate: {
            type: DataTypes.DATEONLY,         
        },
        dietPickDate: {
            type: DataTypes.DATEONLY,
        },
        currentWeightInDg: {
            type: DataTypes.INTEGER,
            validate: {
                min: 100,
            },
        },
        startWeightInDg: {
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
        currentWaistWidthInMm: {
            type: DataTypes.INTEGER,
            validate: {
                min: 10,
            },
        },
        startWaistWidthInMm: {
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
        currentBicepWidthInMm: {
            type: DataTypes.INTEGER,
            validate: {
                min: 10,
            },
        },
        startBicepWidthInMm: {
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
        targetWeightProgressPercentage: {
            type: DataTypes.VIRTUAL,
        },
        targetWaistWidthProgressPercentage: {
            type: DataTypes.VIRTUAL,
        },
        targetBicepWidthProgressPercentage: {
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

                const salt = await bcrypt.genSalt();
                user.password = await bcrypt.hash(user.password, salt);
            },
            afterFind: async query => {
                // set virtual fields
                if (query.targetWeightInDg) {
                    query.targetWeightProgressPercentage = calculateProgress(
                        query.currentWeightInDg,
                        query.startWeightInDg,
                        query.targetWeightInDg,
                    );
                }
                if (query.targetWaistWidthInMm) {
                    query.targetWaistWidthProgressPercentage = calculateProgress(
                        query.currentWaistWidthInMm,
                        query.startWaistWidthInMm,
                        query.targetWaistWidthInMm,
                    );
                }
                if (query.targetBicepWidthInMm) {
                    query.targetBicepWidthProgressPercentage = calculateProgress(
                        query.currentBicepWidthInMm,
                        query.startBicepWidthInMm,
                        query.targetBicepWidthInMm,
                    );
                }
            }
        },
    });
}


const calculateProgress = (current, start, target) => {
    if (start < target) {
        const initialDifference = target-start;
        const progress = Math.floor((
            (initialDifference-target+current) / initialDifference)
            *100
        );
        return progress >= 100 ? 100 : progress;
    }
    const initialDifference = start-target;
    const progress = Math.floor((
        (initialDifference-current+target) / initialDifference)
        *100
    );
    return progress >= 100 ? 100 : progress;
}
