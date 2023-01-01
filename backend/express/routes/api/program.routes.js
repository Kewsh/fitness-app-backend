const router = require('express').Router();

router.get('/', (req, res) => {
    // get program suggestions
})

router.get('/:id', (req, res) => {
    // get one
});

router.get('/:id/workouts', (req, res) => {
    // get program's workouts
});

router.get('/:id/club', (req, res) => {
    // get program's club
});

router.get('/:id/comments', (req, res) => {
    // get program's comments
});

router.get('/:id/cover', (req, res) => {
    // get program's cover picture
});

router.post('/:id/enroll', (req, res) => {
    // enroll in a program
});

module.exports = router;
