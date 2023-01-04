const router = require('express').Router();
const {
    findOneById,
    getCoverPicture,
    getInstructionVideo
} = require('../../controllers/workout.controller');

router.get('/:id', findOneById);

router.get('/:id/cover', getCoverPicture);

router.get('/:id/video', getInstructionVideo);

module.exports = router;
