const app = require('./express/app');
const sequelize = require('./sequelize');
const PORT = 3000;

const assertDatabaseConnectionOk = async () => {
    console.log(`Checking database connection...`);
    try {
        await sequelize.authenticate();
        console.log('Database connection OK!');
    } catch (error) {
        console.log('Unable to connect to the database:');
        console.log(error.message);
        process.exit(1);
    }
}

async function init() {
    await assertDatabaseConnectionOk();

    // synchronize models with database
    await sequelize.sync({ force: true });

    app.listen(PORT, () => {
        console.log(`Server started listening on port ${PORT}`);
    });
}

init();
