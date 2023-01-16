const { getUploadedFilePath, deleteFile } = require('../file-utils');
const upload = require('../multer');
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

        // get author's fullname
        // find a way to do this in the first query
        recipeReview.comment.dataValues.user = {
            fullName: (await recipeReview.comment.getUser()).fullName
        }

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
    try {
        const recipeReview = await recipeReviewModel.findByPk(
            req.params.id,
            { attributes: ['id', 'reviewPicPath'] }
        );

        if (!recipeReview || !recipeReview.reviewPicPath) {
            return res.status(404).json('No review picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(recipeReview.reviewPicPath));
    } catch (error) {
        return res.status(500).json(error);
    }
}

module.exports.setPicture = async (req, res) => {
    const handler = upload.single('picture');

    handler(req, res, async error => {
        if (error) {
            return res.status(500).json(error.message);
        }
        try {
            const recipeReview = await recipeReviewModel.findByPk(
                req.params.id,
                { attributes: ['id', 'reviewPicPath'] },
            );

            if (!recipeReview) {
                //TODO: find a better way to abort the upload
                deleteFile(getUploadedFilePath(req.file.filename));
                throw new Error('No recipe review found with this id');
            }
    
            // delete previous picture from database (if exists)
            if (recipeReview.reviewPicPath)
                deleteFile(getUploadedFilePath(recipeReview.reviewPicPath));
    
            // update picture name in database
            recipeReview.reviewPicPath = req.file.filename;
            await recipeReview.save();

            return res.status(200).json();
        } catch (error) {
            // abort file upload
            deleteFile(getUploadedFilePath(req.file.filename));
            return res.status(500).json(error);
        }
    });
}

module.exports.deletePicture = async (req, res) => {
    try {
        const recipeReview = await recipeReviewModel.findByPk(
            req.params.id,
            { attributes: ['id', 'reviewPicPath'] }
        );
    
        if (!recipeReview || !recipeReview.reviewPicPath) {
            return res.status(404).json('No review picture found');
        }

        deleteFile(getUploadedFilePath(recipeReview.reviewPicPath));

        // update picture name in database
        recipeReview.reviewPicPath = null;
        await recipeReview.save();

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}
