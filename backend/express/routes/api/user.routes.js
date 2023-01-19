const router = require('express').Router();
const {
    createOne,
    getEvents,
    getProfilePicture,
} = require('../../controllers/user.controller');

router.post('/signup', createOne);

router.get('/:id/events', getEvents);

router.get('/:id/profile-picture', getProfilePicture);

module.exports = router;
