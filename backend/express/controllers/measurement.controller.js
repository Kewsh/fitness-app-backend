const { measurement: measurementModel } = require('../../sequelize').models;

module.exports.updateOne = async (req, res) => {
    try {
        if (!req.body.current && !req.body.start && !req.body.target) {
            return res.error(400, 'Missing fields in request body');
        }
        const [ affectedRows ] = await measurementModel.update({
            current: req.body.current,
            start: req.body.start,
            target: req.body.target,
        }, {
            where: { id: req.params.id },
        });

        if (!affectedRows) {
            return res.error(404, 'No measurement found with this id');
        }

        return res.success(200, {});
    } catch (error) {
        return res.error(500, error.message);
    }
}
