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
        nAttendees: {
            type: DataTypes.VIRTUAL,
        },
        rating: {
            type: DataTypes.VIRTUAL,
        },
    }, {
        hooks: {
            afterFind: async query => {
                // skip this hook if no match is found
                if (!query) return;

                // set virtual fields.
                // e.g. query can be from findAll or findOne
                if (Array.isArray(query)){
                    for (const result of query) {
                        const { nAttendees, rating } =
                            await getVirtualFields(sequelize, result);

                        result.nAttendees = nAttendees;
                        result.rating = rating;
                    }
                } else {
                    const { nAttendees, rating } =
                        await getVirtualFields(sequelize, query);

                    query.nAttendees = nAttendees;
                    query.rating = rating;
                }
            }
        }
    });
}


const getVirtualFields = async (sequelize, event) => {
    const nAttendees = await getNumberOfAttendees(event);
    let rating = await getRating(sequelize, event.id);
    rating = {
        rating: parseInt(rating.avgRate),
        nRates: parseInt(rating.nRates),
    };

    return { nAttendees, rating };
}

const getRating = async (sequelize, eventId) => {
    // find all comments whose eventId is the same as param programId
    // this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: { eventId },
        attributes: [
            [sequelize.fn('AVG', sequelize.col('rate')), 'avgRate'],
            [sequelize.fn('COUNT', sequelize.col('rate')), 'nRates'],
        ],
    }))[0].dataValues;
}

const getNumberOfAttendees = async (event) => {
    // get number of users who have participated in the event
    return await event.countUsers();
}
