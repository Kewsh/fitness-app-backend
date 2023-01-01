const router = require('express').Router();

router.get('/:id', (req, res) => {
    // get one (populate ingredients)
});

router.get('/:id/cover', (req, res) => {
    // get recipe's cover picture
});

router.get('/:id/diet', (req, res) => {
    // get recipe's diet
});

router.get('/:id/reviews', (req, res) => {
    // get recipe's reviews (populate comment)
});

module.exports = router;
