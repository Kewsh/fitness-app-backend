const router = require('express').Router();
const {
    discover,
    findOneById,
    getWorkouts,
    getComments,
    getCoverPicture,
    enroll,
    createOne,
    updateOne,
    setCoverPicture,
    deleteCoverPicture
} = require('../../controllers/program.controller');

router.post('/', createOne);

router.post('/discover', discover);

router.patch('/:id', updateOne);

router.get('/:id', findOneById);

router.get('/:id/workouts', getWorkouts);

router.get('/:id/comments', getComments);

router.get('/:id/cover', getCoverPicture);

router.put('/:id/cover', setCoverPicture);

router.delete('/:id/cover', deleteCoverPicture);

router.post('/:id/enroll', enroll);

module.exports = router;
