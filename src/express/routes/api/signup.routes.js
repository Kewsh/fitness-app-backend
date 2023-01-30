const router = require('express').Router();
const {
    signUpUser,
    signUpClub
} = require('../../controllers/signup.controller');

router.post('/user', signUpUser);

router.post('/club', signUpClub);

module.exports = router;
