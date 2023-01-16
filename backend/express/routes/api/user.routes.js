const router = require('express').Router();
const {
    findOne,
    createOne,
    getEvents,
    getProfilePicture,
} = require('../../controllers/user.controller');

router.post('/login', findOne);

router.post('/signup', createOne);

router.get('/:id/events', getEvents);

router.get('/:id/profile-picture', getProfilePicture);

module.exports = router;
