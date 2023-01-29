const multer = require('multer');
const { v4: uuidv4 } = require('uuid');
const { parse } = require('path');
const { uploadsDirectory, maxImageSize } = require('./consts');

const storage = multer.diskStorage({
    destination: uploadsDirectory,
    filename: (req, file, cb) => {
        // generate unique identifier as filename
        const filename = uuidv4();
        const extension = parse(file.originalname).ext;
        cb(null, `${filename}${extension}`);
    },
});

const fileFilter = (req, file, cb) => {
    const fileTypes = /jpeg|jpg|png/;
    const mimetype = fileTypes.test(file.mimetype);
    if (mimetype) {
        return cb(null, true);
    } else {
        return cb(
            new Error(`File type does not match: ${fileTypes}`),
            false,
        );
    }
}

module.exports = multer({
    storage,
    limits : { fileSize : maxImageSize },
    fileFilter
});
