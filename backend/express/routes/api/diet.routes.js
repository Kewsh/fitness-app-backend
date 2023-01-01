const router = require('express').Router();

router.get('/', (req, res) => {
    // get diet suggestions
});

router.get('/:id', (req, res) => {
    // get one
});

router.get('/:id/cover', (req, res) => {
    // get diet's cover picture
});

router.get('/:id/food', (req, res) => {
    // get diet's foods
});

router.get('/:id/nutritionist', (req, res) => {
    // get diet's nutritionist
});

router.get('/:id/recipes', (req, res) => {
    // get diet's suggested recipes
});

router.get('/:id/comments', (req, res) => {
    // get diet's comments
});

router.post('/:id/pick', (req, res) => {
    // pick a diet
});

module.exports = router;
