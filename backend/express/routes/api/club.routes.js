const router = require('express').Router();
const {
    createOne,
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

router.post('/signup', createOne);

router.get('/:id', findOneById);

router.patch('/:id', updateOne);

router.get('/:id/programs', getPrograms);

router.get('/:id/events', getEvents);

router.get('/:id/members', getMembers);

router.get('/:id/cover', getCoverPicture);

router.put('/:id/cover', setCoverPicture);

router.delete('/:id/cover', deleteCoverPicture);

router.get('/:id/logo', getLogo);

router.put('/:id/logo', setLogo);

router.delete('/:id/logo', deleteLogo);

module.exports = router;
