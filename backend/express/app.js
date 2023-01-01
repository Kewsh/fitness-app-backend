const express = require('express');
const app = express();

// set up routes
require('./routes')(app);

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

module.exports = app;
