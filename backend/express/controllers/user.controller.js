const { user: userModel } = require('../../sequelize').models;

module.exports.findOne = async (req, res) => {
    try {
        if (!req.body.email || !req.body.password) {
            return res.status(400).json('Missing fields in request body');
        }

        const user = await userModel.findOne({
            where: {
                email: req.body.email,
            },
            attributes: {
                exclude: ['profilePicPath']
            }
        });

        if (!user) {
            return res.status(404).json('No user found with this email');
        }
        if (!(await user.isPasswordValid(req.body.password, user.password))) {
            return res.status(401).json('Invalid password');
        }

        // exclude password from response object
        const { password, ...userResponse } = user.dataValues;

        return res.status(200).json(userResponse);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.createOne = async (req, res) => {
    try {
        const user = await userModel.create({
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            password: req.body.password,
        });

        // exclude password from response object
        const { password, ...userResponse } = user.dataValues;

        return res.status(201).json(userResponse);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getEvents = async (req, res) => {
    try {
        const user = await userModel.findByPk(req.params.id);
        if (!user) {
            return res.status(404).json('No user found with this id');
        }
        //TODO: get clubName
        const events = await user.getEvents({
            hooks: false,
            attributes: [
                'id',
                'title'
            ],
        });

        return res.status(200).json(events);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getProfilePicture = async (req, res) => {

}
