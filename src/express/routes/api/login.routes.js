const router = require('express').Router();
const { loginByPassword } = require('../../controllers/login.controller');

router.post('/password', loginByPassword);

module.exports = router;
