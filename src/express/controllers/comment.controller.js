const { Op } = require('sequelize');
const {
    user: userModel,
    comment: commentModel
} = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const user = await userModel.findByPk(userId);

        if (!user) {
            return res.error(404, 'No user found with this id');
        }

        if (!canCommentOnProgram(user, req.body.programId)) {
            return res.error(403, 'You must be enrolled in this program to comment on it');
        }
        if (!canCommentOnDiet(user, req.body.dietId)) {
            return res.error(403, 'You must be using this diet to comment on it');
        }
        if (!(await canCommentOnEvent(user, req.body.eventId))) {
            return res.error(403, 'You must be participating in this event to comment on it');
        }
        // can't know whether user has tried a recipe!

        const comment = await commentModel.create({
            text: req.body.text,
            rate: req.body.rate,
            programId: req.body.programId,
            eventId: req.body.eventId,
            dietId: req.body.dietId,
            recipeReviewId: req.body.recipeReviewId,
            userId,
        });

        // get author's fullname
        //TODO: find a way to do this in the first query
        comment.dataValues.user = {
            fullName: (await comment.getUser()).fullName
        }

        return res.success(201, comment);
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.updateOne = async (req, res) => {
    try {
        if (!req.body.text && !req.body.rate) {
            return res.error(400, 'Missing fields in request body');
        }
        const userId = getUserId(req.user);
        const [ affectedRows ] = await commentModel.update({
            text: req.body.text,
            rate: req.body.rate,
        }, {
            where: {
                id: req.params.id,
                userId,
            },
        });

        if (!affectedRows) {
            return res.error(404, 'No comment found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.deleteOne = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const destroyedRows = await commentModel.destroy({
            where: {
                id: req.params.id,
                userId,
                // there is no cascade on this foreign key, can't leave it hanging.
                // delete from recipe-review route
                recipeReviewId: {
                    [Op.eq]: null,
                },
            }
        });

        if (!destroyedRows) {
            return res.error(404, 'No comment found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

const getUserId = (user) => {
    // <null> can be used for query methods, while <false> cannot
    return user.isUser ? user.id : null;
}

const canCommentOnProgram = (user, programId) => {
    return !(programId && user.programId != programId);
}

const canCommentOnDiet = (user, dietId) => {
    return !(dietId && user.dietId != dietId);
}

const canCommentOnEvent = async (user, eventId) => {
    const eventIds = (await user.getEvents()).map(event => event.id);
    return !(eventId && !eventIds.includes(eventId));
}
