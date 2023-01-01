const router = require('express').Router();

router.post('/', (req, res) => {
    // create one
});

router.patch('/:id', (req, res) => {
    // update one
});

router.delete('/:id', (req, res) => {
    // delete one
});

router.get('/:id/picture', (req, res) => {
    // get review picture
});

router.put('/:id/picture', (req, res) => {
    // update review picture
});

router.delete('/:id/picture', (req, res) => {
    // delete review picture
});

module.exports = router;
