const router = require('express').Router();
const {
    createOne,
    findOneById,
    updateOne,
    getPrograms,
    getEvents,
    getMembers,
    getCoverPicture,
    getLogo,
} = require('../../controllers/club.controller');

router.post('/signup', createOne);

router.get('/:id', findOneById);

router.patch('/:id', updateOne);

router.get('/:id/programs', getPrograms);

router.get('/:id/events', getEvents);

router.get('/:id/members', getMembers);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/logo', getLogo);

module.exports = router;
