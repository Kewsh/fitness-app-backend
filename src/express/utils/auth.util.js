module.exports.getClubId = (user) => {
    // <null> can be used for query methods, while <false> cannot
    return !user.isUser ? user.id : null;
}

module.exports.getUserId = (user) => {
    return user.isUser ? user.id : null;
}
