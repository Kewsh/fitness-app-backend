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

        return res.status(201).json(comment);
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.updateOne = async (req, res) => {
    try {
        if (!req.body.text && !req.body.rate) {
            return res.status(400).json('Missing fields in request body');
        }
        const [ affectedRows ] = await commentModel.update({
            text: req.body.text,
            rate: req.body.rate,
        }, {
            where: { id: req.params.id },
        });

        if (!affectedRows) {
            return res.status(404).json('No comment found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.deleteOne = async (req, res) => {
    try {
        const destroyedRows = await commentModel.destroy({
            where: { id: req.params.id }
        });

        if (!destroyedRows) {
            return res.status(404).json('No comment found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}
