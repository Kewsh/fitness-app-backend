const router = require('express').Router();
const {
    findOneById,
    updateOne,
    getPrograms,
    getEvents,
    getMembers,
    getCoverPicture,
    setCoverPicture,
    deleteCoverPicture,
    getLogo,
    setLogo,
    deleteLogo,
} = require('../../controllers/club.controller');

router.patch('/', updateOne);

router.get('/members', getMembers);

router.put('/cover', setCoverPicture);

router.delete('/cover', deleteCoverPicture);

router.put('/logo', setLogo);

router.delete('/logo', deleteLogo);

router.get('/:id', findOneById);

router.get('/:id/programs', getPrograms);

router.get('/:id/events', getEvents);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/logo', getLogo);

module.exports = router;
