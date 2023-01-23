const router = require('express').Router();
const {
    createOne,
    updateOne,
    getEvents,
    getProfilePicture,
} = require('../../controllers/user.controller');

router.post('/signup', createOne);

router.patch('/:id', updateOne);

router.get('/:id/events', getEvents);

router.get('/:id/profile-picture', getProfilePicture);

module.exports = router;
