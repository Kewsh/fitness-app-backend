const multer = require('multer');
const { maxImageSize } = require('../consts');
const storage = require('./storage');
const fileFilter = require('./file-filter');

const limits = { fileSize : maxImageSize };

module.exports = multer({ storage, limits, fileFilter });
