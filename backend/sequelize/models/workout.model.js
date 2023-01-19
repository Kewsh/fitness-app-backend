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
                max: 1000,
                min: 1,
            }
        },
        sets: {
            type: DataTypes.INTEGER,
            validate: {
                max: 1000,
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
        day: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 1,
                max: 366,
            },
        },
        setsAndReps: {
            type: DataTypes.VIRTUAL,
            get() {
                return `${this.sets}x${this.reps || (this.setTimeInSeconds + 'sec')} ${this.title}`;
            },
        }
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
