const router = require('express').Router();
const {
    createOne,
    updateOne,
    deleteOne
} = require('../../controllers/comment.controller');

router.post('/', createOne);

router.patch('/:id', updateOne);

router.delete('/:id', deleteOne);

module.exports = router;
