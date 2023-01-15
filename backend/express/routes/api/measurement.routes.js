const router = require('express').Router();
const { updateOne } = require('../../controllers/measurement.controller');

router.patch('/:id', updateOne);

module.exports = router;
