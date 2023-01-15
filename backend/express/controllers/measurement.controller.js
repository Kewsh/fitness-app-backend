const { measurement: measurementModel } = require('../../sequelize').models;

module.exports.updateOne = async (req, res) => {
    try {
        if (!req.body.current && !req.body.start && !req.body.target) {
            return res.status(400).json('Missing fields in request body');
        }
        const [ affectedRows ] = await measurementModel.update({
            current: req.body.current,
            start: req.body.start,
            target: req.body.target,
        }, {
            where: { id: req.params.id },
        });

        if (!affectedRows) {
            return res.status(404).json('No measurement found with this id');
        }

        return res.status(200).json();
    } catch (error) {
        return res.status(500).json(error);
    }
}
