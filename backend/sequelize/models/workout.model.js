const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('workout', {
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
        videoPath: {
            type: DataTypes.STRING,
        },
        reps: {
            type: DataTypes.INTEGER,
            validate: {
                max: 50,
                min: 1,
            }
        },
        sets: {
            type: DataTypes.INTEGER,
            validate: {
                max: 100,
                min: 1,
            },
            allowNull: false,
        },
        setTimeInSeconds: {
            type: DataTypes.INTEGER,
            validate: {
                max: 3600,
                min: 1,
            },
        },
        burntCalories: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0,
            },
        },
        isDone: {
            type: DataTypes.BOOLEAN,
            defaultValue: false,
        },
        day: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 1,
                max: 366,
            },
        },
    }, {
        hooks: {
            beforeCreate: workout => {
                if ((!workout.reps && !workout.setTimeInSeconds) ||
                    (workout.reps && workout.setTimeInSeconds))
                {
                    throw new Error("Workout must have either reps or set time");
                }
            },
            beforeUpdate: workout => {
                if ((!workout.reps && !workout.setTimeInSeconds) ||
                    (workout.reps && workout.setTimeInSeconds))
                {
                    throw new Error("Workout must have either reps or set time");
                }
            },
        }
    });
}
