const router = require('express').Router();
const {
    createOne,
    discover,
    updateOne,
    findOneById,
    getComments,
    setCoverPicture,
    getCoverPicture,
    participate,
    deleteCoverPicture
} = require('../../controllers/event.controller');

router.post('/', createOne);

router.post('/discover', discover);

router.patch('/:id', updateOne)

router.get('/:id', findOneById);

router.get('/:id/comments', getComments);

router.get('/:id/cover', getCoverPicture);

router.put('/:id/cover', setCoverPicture);

router.delete('/:id/cover', deleteCoverPicture);

router.post('/:id/participate', participate);

module.exports = router;
