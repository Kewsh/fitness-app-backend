const router = require('express').Router();
const {
    createOne,
    findOneById,
    getPrograms,
    getEvents,
    getMembers,
    getCoverPicture,
    getLogo,
} = require('../../controllers/club.controller');

router.post('/signup', createOne);

router.get('/:id', findOneById);

router.get('/:id/programs', getPrograms);

router.get('/:id/events', getEvents);

router.get('/:id/members', getMembers);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/logo', getLogo);

module.exports = router;
