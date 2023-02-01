const router = require('express').Router();
const {
    updateOne,
    getEvents,
    getProfilePicture,
    setProfilePicture,
    deleteProfilePicture,
} = require('../../controllers/user.controller');

router.patch('/', updateOne);

router.get('/events', getEvents);

router.put('/profile-picture', setProfilePicture);

router.delete('/profile-picture', deleteProfilePicture);

router.get('/:id/profile-picture', getProfilePicture);

module.exports = router;
