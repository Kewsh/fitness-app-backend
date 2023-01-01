const router = require('express').Router();

router.get('/', (req, res) => {
    // get one by user and pass
});

router.post('/', (req, res) => {
    // create one
});

router.get('/:id/program', (req, res) => {
    // get user's program
});

router.get('/:id/diet', (req, res) => {
    // get user's diet
});

router.get('/:id/events', (req, res) => {
    // get user's events
});

router.get('/:id/profile-picture', (req, res) => {
    // get user's profile picture
});

module.exports = router;
