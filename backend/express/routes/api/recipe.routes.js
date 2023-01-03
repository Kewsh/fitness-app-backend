const router = require('express').Router();
const {
    findOneById,
    getCoverPicture,
    getReviews
} = require('../../controllers/recipe.controller');

router.get('/:id', findOneById);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/reviews', getReviews);

module.exports = router;
