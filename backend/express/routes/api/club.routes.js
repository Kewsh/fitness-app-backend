const router = require('express').Router();
const {
    findOne,
    createOne,
    findOneById,
    getPrograms,
    getEvents,
    getCoverPicture,
    getLogo,
} = require('../../controllers/club.controller');

router.get('/', findOne);

router.post('/', createOne);

router.get('/:id', findOneById);

router.get('/:id/programs', getPrograms);

router.get('/:id/events', getEvents);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/logo', getLogo);

module.exports = router;
