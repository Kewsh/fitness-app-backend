const { join } = require('path');
const { unlink } = require('fs');
const { uploadsDirectory } = require('../consts');

module.exports.getUploadedFilePath = fileName => {
    return join(process.cwd(), uploadsDirectory, fileName);
}

module.exports.deleteFile = filePath => {
    unlink(filePath, err => {
        if (err) console.log(err);
    });
}
