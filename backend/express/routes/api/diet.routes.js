const router = require('express').Router();
const {
    discover,
    findOneById,
    getCoverPicture,
    getFoods,
    getRecipes,
    getComments,
    pick
} = require('../../controllers/diet.controller');

router.get('/', discover);

router.get('/:id', findOneById);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/foods', getFoods);

router.get('/:id/recipes', getRecipes);

router.get('/:id/comments', getComments);

router.post('/:id/pick', pick);

module.exports = router;
