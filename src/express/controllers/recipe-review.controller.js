const { getUploadedFilePath, deleteFile } = require('../utils/file.util');
const { getUserId } = require('../utils/auth.util');
const upload = require('../multer');
const sequelize = require('../../sequelize');
const {
    recipeReview: recipeReviewModel,
    comment: commentModel,
} = sequelize.models;

module.exports.createOne = async (req, res) => {
    const transaction = await sequelize.transaction();

    try {
        const userId = getUserId(req.user);
        const recipeReview = await recipeReviewModel.create({
            recipeId: req.body.recipeId,
            comment: {
                text: req.body.text,
                rate: req.body.rate,
                userId,
            },
        }, {
            include: commentModel,
            transaction,
        });

        // get author's fullname
        // find a way to do this in the first query
        recipeReview.comment.dataValues.user = {
            fullName: (await recipeReview.comment.getUser()).fullName
        }

        await transaction.commit();
        return res.success(201, recipeReview);
    } catch (error) {
        await transaction.rollback();
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
                recipeReviewId: req.params.id,
                userId,
            },
        });

        if (!affectedRows) {
            return res.error(404, 'No recipe review found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.deleteOne = async (req, res) => {
    const transaction = await sequelize.transaction();

    try {
        // sequelize doesn't support including on the destroy method, so we
        // need to first find the instance, then delete it separately
        const userId = getUserId(req.user);
        const recipeReview = await recipeReviewModel.findOne({
            where: {
                id: req.params.id,
            },
            include: {
                model: commentModel,
                where: { userId },
            },
        });

        if (!recipeReview) {
            return res.error(404, 'No recipe review found with this id');
        }

        await recipeReview.comment.destroy({ transaction });
        await recipeReview.destroy({ transaction });

        await transaction.commit();
        return res.success(200, {});
    } catch (error) {
        await transaction.rollback();
        return res.error(500, error.message);
    }
}

module.exports.getPicture = async (req, res) => {
    try {
        const recipeReview = await recipeReviewModel.findByPk(
            req.params.id,
            { attributes: ['id', 'reviewPicPath'] }
        );

        if (!recipeReview || !recipeReview.reviewPicPath) {
            return res.error(404, 'No review picture found');
        }

        res.status(200)
           .sendFile(getUploadedFilePath(recipeReview.reviewPicPath));
    } catch (error) {
        return res.error(500, error.message);
    }
}

module.exports.setPicture = async (req, res) => {
    const handler = upload.single('picture');

    handler(req, res, async error => {
        if (error) {
            return res.error(500, error.message);
        }
        try {
            const userId = getUserId(req.user);
            const recipeReview = await recipeReviewModel.findOne({
                where: {
                    id: req.params.id,
                },
                include: {
                    model: commentModel,
                    where: { userId },
                },
                attributes: ['id', 'reviewPicPath'],
            });

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

            return res.success(200, {});
        } catch (error) {
            // abort file upload
            deleteFile(getUploadedFilePath(req.file.filename));

            return res.error(500, error.message);
        }
    });
}

module.exports.deletePicture = async (req, res) => {
    try {
        const userId = getUserId(req.user);
        const recipeReview = await recipeReviewModel.findOne({
            where: {
                id: req.params.id,
            },
            include: {
                model: commentModel,
                where: { userId },
            },
            attributes: ['id', 'reviewPicPath'],
        });
    
        if (!recipeReview || !recipeReview.reviewPicPath) {
            return res.error(404, 'No review picture found');
        }

        deleteFile(getUploadedFilePath(recipeReview.reviewPicPath));

        // update picture name in database
        recipeReview.reviewPicPath = null;
        await recipeReview.save();

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}
