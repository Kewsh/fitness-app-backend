const router = require('express').Router();
const {
    getPicture,
    getDiets
} = require('../../controllers/nutritionist.controller');

router.get('/:id/picture', getPicture);

router.get('/:id/diets', getDiets);

module.exports = router;
