const multer = require('multer');
const { v4: uuidv4 } = require('uuid');
const { parse } = require('path');
const { uploadsDirectory } = require('../consts');

module.exports = multer.diskStorage({
    destination: uploadsDirectory,
    filename: (req, file, cb) => {
        // generate unique identifier as filename
        const filename = uuidv4();
        const extension = parse(file.originalname).ext;
        cb(null, `${filename}${extension}`);
    },
});
