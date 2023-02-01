module.exports = (req, file, cb) => {
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
