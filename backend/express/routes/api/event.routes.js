const router = require('express').Router();
const {
    discover,
    findOneById,
    getComments,
    getCoverPicture,
    participate
} = require('../../controllers/event.controller');

router.post('/discover', discover);

router.get('/:id', findOneById);

router.get('/:id/comments', getComments);

router.get('/:id/cover', getCoverPicture);

router.post('/:id/participate', participate);

module.exports = router;
