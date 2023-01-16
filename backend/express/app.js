const express = require('express');
const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// set up routes
require('./routes')(app);

module.exports = app;
