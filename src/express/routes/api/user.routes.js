const router = require('express').Router();
const {
    updateOne,
    getEvents,
    getProfilePicture,
    setProfilePicture,
    deleteProfilePicture,
} = require('../../controllers/user.controller');

router.patch('/:id', updateOne);

router.get('/:id/events', getEvents);

router.get('/:id/profile-picture', getProfilePicture);

router.put('/:id/profile-picture', setProfilePicture);

router.delete('/:id/profile-picture', deleteProfilePicture);

module.exports = router;
