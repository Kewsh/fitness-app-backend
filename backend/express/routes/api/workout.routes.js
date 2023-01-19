const router = require('express').Router();
const {
    findOneById,
    updateOne,
    deleteOne,
    getCoverPicture,
    setCoverPicture,
    deleteCoverPicture,
} = require('../../controllers/workout.controller');

router.get('/:id', findOneById);

router.patch('/:id', updateOne);

router.delete('/:id', deleteOne);

router.get('/:id/cover', getCoverPicture);

router.put('/:id/cover', setCoverPicture);

router.delete('/:id/cover', deleteCoverPicture);

module.exports = router;
