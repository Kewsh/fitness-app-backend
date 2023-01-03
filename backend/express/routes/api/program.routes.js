const router = require('express').Router();
const {
    discover,
    findOneById,
    getWorkouts,
    getComments,
    getCoverPicture,
    enroll
} = require('../../controllers/program.controller');

router.get('/', discover);

router.get('/:id', findOneById);

router.get('/:id/workouts', getWorkouts);

router.get('/:id/comments', getComments);

router.get('/:id/cover', getCoverPicture);

router.post('/:id/enroll', enroll);

module.exports = router;
