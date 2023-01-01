const router = require('express').Router();

router.get('/', (req, res) => {
    // get one by email and password (log in)
});

router.post('/', (req, res) => {
    // create one
});

router.get('/:id', (req, res) => {
    // get one (show profile) (populate social media)
});

router.get('/:id/programs', (req, res) => {
    // get club's programs
});

router.get('/:id/events', (req, res) => {
    // get club's events
});

router.get('/:id/cover', (req, res) => {
    // get club's cover picture
});

router.get('/:id/logo', (req, res) => {
    // get club's logo
});

module.exports = router;
