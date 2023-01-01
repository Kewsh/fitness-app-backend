const router = require('express').Router();

router.get('/', (req, res) => {
    // get event suggestions
});

router.get('/:id', (req, res) => {
    // get one
});

router.get('/:id/club', (req, res) => {
    // get event's club
});

router.get('/:id/comments', (req, res) => {
    // get event's comments
});

router.get('/:id/cover', (req, res) => {
    // get event's cover picture
});

router.post('/:id/participate', (req, res) => {
    // participate in an event
});

module.exports = router;
