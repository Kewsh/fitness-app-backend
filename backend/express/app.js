const express = require('express');

const app = express();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// set up routes
require('./routes')(app);


// custom res.send methods to wrap response in standard format
express.response.success = function(code, data) {
    return this.status(code).json({
        status: 'success',
        code,
        data,
    });
}

express.response.error = function(code, message) {
    return this.status(code).json({
        status: 'error',
        code,
        message,
    });
}


module.exports = app;
