const { DataTypes } = require("sequelize");

module.exports = (sequelize) => {
    sequelize.define('measurement', {
        type: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                isIn: [[
                    'WEIGHT',
                    'BICEP',
                    'WAIST',
                ]],
            },
        },
        current: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0
            }
        },
        start: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0
            }
        },
        target: {
            type: DataTypes.INTEGER,
            validate: {
                min: 0
            }
        },
        progressPercentage: {
            type: DataTypes.VIRTUAL,
            get() {
                if (this.current && this.start && this.target) {
                    return calculateProgress(
                        this.current,
                        this.start,
                        this.target
                    );
                }
            },
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
