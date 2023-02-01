const { getUploadedFilePath, deleteFile } = require('../file-utils');
const upload = require('../multer');
const {
    user: userModel,
    measurement: measurementModel,
    club: clubModel,
    email: emailModel,
} = require('../../sequelize').models;

module.exports.updateOne = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const user = await userModel.findByPk(userId, {
            include: [emailModel, measurementModel]
        });

        if (!user) {
            return res.error(404, 'No user found with this id');
        }

        if (req.body.email) {
            user.email.email = req.body.email;
            await user.email.save();
        }

        if (Array.isArray(req.body.measurements)) {
            for (const { current, start, target, type } of req.body.measurements) {
                await measurementModel.update({
                    current,
                    start,
                    target,
                }, {
                    where: {
                        userId: user.id,
                        type,
                    },
                });
            }
        }

        // update password only if old password is correct
        let updatedPassword;
        if (
            req.newPassword &&
            req.oldPassword &&
            user.isPasswordValid(req.body.oldPassword, user.password)
        ) {
            updatedPassword = req.body.newPassword;
        }

        await user.update({
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            sex: req.body.sex,
            birthday: req.body.birthday,
            height: req.body.height,
            phoneNumber: req.body.phoneNumber,
            password: updatedPassword,
        })

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getEvents = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const user = await userModel.findByPk(userId);

        if (!user) {
            return res.error(404, 'No user found with this id');
        }

        // don't have something like getEventsAndCount
        const count = await user.countEvents({ hooks: false });
        const events = await user.getEvents({
            attributes: ['id', 'title'],
            limit: req.query.limit,
            offset: req.query.offset,
            include: [{
                model: clubModel,
                attributes: ['name'],
            }],
            hooks: false,
        });

        return res.success(200, { count, rows: events });
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.getProfilePicture = async (req, res) => {
    try {
        const user = await userModel.findByPk(
            req.params.id,
            { attributes: ['id', 'profilePicPath'] }
        );

        if (!user || !user.profilePicPath) {
            return res.error(404, 'No profile picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(user.profilePicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.setProfilePicture = async (req, res) => {
    const handler = upload.single('picture');

    handler(req, res, async error => {
        if (error) {
            return res.error(500, error.message);
        }
        try {
            const userId = getUserId(req.user);
            const user = await userModel.findByPk(
                userId,
                { attributes: ['id', 'profilePicPath'] },
            );

            if (!user) {
                deleteFile(getUploadedFilePath(req.file.filename));
                throw new Error('No user found with this id');
            }

            // delete previous picture from database (if exists)
            if (user.profilePicPath)
                deleteFile(getUploadedFilePath(user.profilePicPath));

            // update picture name in database
            user.profilePicPath = req.file.filename;
            await user.save();

            return res.success(200, {});
        } catch (error) {
            // abort file upload
            deleteFile(getUploadedFilePath(req.file.filename));

            return res.error(500, error.message);
        }
    });
}

module.exports.deleteProfilePicture = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const user = await userModel.findByPk(
            userId,
            { attributes: ['id', 'profilePicPath'] }
        );

        if (!user || !user.profilePicPath) {
            return res.error(404, 'No profile picture found');
        }

        deleteFile(getUploadedFilePath(user.profilePicPath));

        // update picture name in database
        user.profilePicPath = null;
        await user.save();

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

const getUserId = (user) => {
    // <null> can be used for query methods, while <false> cannot
    return user.isUser ? user.id : null;
}
