const {
    recipeReview: recipeReviewModel,
    comment: commentModel,
} = require('../../sequelize').models;

module.exports.createOne = async (req, res) => {
    try {
        const recipeReview = await recipeReviewModel.create({
            recipeId: req.body.recipeId,
            comment: {
                text: req.body.text,
                rate: req.body.rate,
                userId: req.body.userId,
            },
        }, {
            include: commentModel,
        });

        // get user's fullname. can we do this in the first query?
        recipeReview.comment.dataValues.authorFullName =
            (await recipeReview.comment.getUser()).fullName;

        return res.status(201).json(recipeReview);
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
            where: { recipeReviewId: req.params.id },
        });

        if (!affectedRows) {
            return res.status(404).json('No recipe review found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.deleteOne = async (req, res) => {
    try {
        const destroyedRows = await recipeReviewModel.destroy({
            where: { id: req.params.id }
        });

        if (!destroyedRows) {
            return res.status(404).json('No recipe review found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.getPicture = async (req, res) => {

}

module.exports.setPicture = async (req, res) => {

}

module.exports.deletePicture = async (req, res) => {

}
