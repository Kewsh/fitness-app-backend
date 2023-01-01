const router = require('express').Router();

router.get('/:id', (req, res) => {
    // get one
});

router.get('/:id/program', (req, res) => {
    // get workout's program
});

router.get('/:id/cover', (req, res) => {
    // get workout's cover picture
});

router.get('/:id/video', (req, res) => {
    // get workout's instruction video
});

module.exports = router;
