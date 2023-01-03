const { DataTypes } = require('sequelize');

module.exports = (sequelize) => {
    sequelize.define('event', {
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
        price: {
            type: DataTypes.INTEGER,
            allowNull: false,
            validate: {
                min: 0,
            },
        },
        maxAttendees: {
            type: DataTypes.INTEGER,
            validate: {
                min: 1,
            },
        },
        startDate: {
            type: DataTypes.DATE,
        },
        endDate: {
            type: DataTypes.DATE,
        },
        attendees: {
            type: DataTypes.VIRTUAL,
        }
    }, {
        hooks: {
            afterFind: async query => {
                // set virtual field attendees
                if (query) {
                    query.attendees = await getNumberOfAttendees(query);
                }
            }
        }
    });
}

const getNumberOfAttendees = async (event) => {
    // get number of users who have participated in the event
    return await event.countUsers();
}
