const router = require('express').Router();
const { findOne } = require('../../controllers/login.controller');

router.post('/', findOne);

module.exports = router;
