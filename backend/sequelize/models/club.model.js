const { DataTypes, Op } = require('sequelize');
const bcrypt = require('bcrypt');

module.exports = (sequelize) => {
    sequelize.define('club', {
        name: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                is: /^[a-zA-Z\s]*$/,
            },
        },
        manager: {
            type: DataTypes.STRING,
            allowNull: false,
            validate: {
                is: /^[a-zA-Z\s]*$/,
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
            type: DataTypes.DATEONLY,
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false,
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
        nAthletes: {
            type: DataTypes.VIRTUAL,
        },
        rating: {
            type: DataTypes.VIRTUAL,
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
            },
            afterFind: async query => {
                // skip this hook if no match is found
                if (!query) return;

                // get program and event ids that belong to this club
                const programIds = await getPrograms(sequelize, query.id);
                const eventIds = await getEvents(sequelize, query.id);
                
                // set virtual fields
                query.nAthletes = await getNumberOfAthletes(
                    sequelize,
                    programIds
                );
                const rating = await getRating(sequelize, programIds, eventIds);
                query.rating = {
                    rating: parseInt(rating.dataValues.avgRate),
                    nRates: query.numberOfRatings = parseInt(
                        rating.dataValues.nRates
                    ),
                };
            },
        },
    });
}


const getEvents = async (sequelize, clubId) => {
    return (await sequelize.models.event.findAll({
        where: { clubId },
        attributes: ['id'],
        hooks: false,
    })).map(event => event.dataValues.id);
}

const getPrograms = async (sequelize, clubId) => {
    return (await sequelize.models.program.findAll({
        where: { clubId },
        attributes: ['id'],
        hooks: false,
    })).map(program => program.dataValues.id);
}

const getRating = async (sequelize, programIds, eventIds) => {
    // find all comments on this club's programs and events
    //TODO: this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: {
            [Op.or]: [{ programId: programIds }, { eventId: eventIds }],
        },
        attributes: [
            [sequelize.fn('AVG', sequelize.col('rate')), 'avgRate'],
            [sequelize.fn('COUNT', sequelize.col('rate')), 'nRates'],
        ],
    }))[0];
}

const getNumberOfAthletes = async (sequelize, programIds) => {
    // get number of users whose programId is in programIds
    return await sequelize.models.user.count({
        where: {
            programId: programIds,
        },
    });
}
