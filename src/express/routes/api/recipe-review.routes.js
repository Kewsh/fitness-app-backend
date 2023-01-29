const router = require('express').Router();
const {
    createOne,
    updateOne,
    deleteOne,
    getPicture,
    setPicture,
    deletePicture
} = require('../../controllers/recipe-review.controller');

router.post('/', createOne);

router.patch('/:id', updateOne);

router.delete('/:id', deleteOne);

router.get('/:id/picture', getPicture);

router.put('/:id/picture', setPicture);

router.delete('/:id/picture', deletePicture);

module.exports = router;
