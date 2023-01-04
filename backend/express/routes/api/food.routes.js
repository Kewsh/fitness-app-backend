const router = require('express').Router();
const { getCoverPicture } = require('../../controllers/food.controller');

router.get('/:id/cover', getCoverPicture);

module.exports = router;
