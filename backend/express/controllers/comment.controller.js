const { comment: commentModel } = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const comment = await commentModel.create({
            text: req.body.text,
            rate: req.body.rate,
            programId: req.body.programId,
            eventId: req.body.eventId,
            dietId: req.body.dietId,
            recipeReviewId: req.body.recipeReviewId,
            userId: req.body.userId,
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
        const [ affectedRows ] = await commentModel.update({
            text: req.body.text,
            rate: req.body.rate,
        }, {
            where: { id: req.params.id },
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
        const destroyedRows = await commentModel.destroy({
            where: { id: req.params.id }
        });

        if (!destroyedRows) {
            return res.error(404, 'No comment found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}
