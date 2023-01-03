const { DataTypes } = require('sequelize');
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
        email: {
            type: DataTypes.STRING,
            allowNull: false,
            unique: true,
            validate: {
                isEmail: true,
            },
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
        numberOfAthletes: {
            type: DataTypes.VIRTUAL,
        },
        rating: {
            type: DataTypes.VIRTUAL,
        },
        numberOfRatings: {
            type: DataTypes.VIRTUAL,
        }
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

                // get program ids that belong to this club
                const programIds = await getPrograms(sequelize, query.id);
                
                // set virtual fields
                query.numberOfAthletes = await getNumberOfAthletes(
                    sequelize,
                    programIds
                );
                const rating = await getRating(sequelize, programIds);
                query.rating = parseInt(rating.dataValues.avgRate);
                query.numberOfRatings = Number(rating.dataValues.nRates);
            },
        },
    });
}

const getPrograms = async (sequelize, clubId) => {
    return (await sequelize.models.program.findAll({
        where: { clubId },
        attributes: ['id'],
        hooks: false,
    })).map(program => program.dataValues.id);
}

const getRating = async (sequelize, programIds) => {
    // find all comments whose programId is in programIds and have ratings
    // this query returns a list with one member (why?)
    return (await sequelize.models.comment.findAll({
        where: { programId: programIds },
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
