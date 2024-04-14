export default {
    state: {
        status: "matching", // matching表示正在匹配，playing表示对战界面
        socket: null,
        opponent_username: "",
        opponent_photo: "",
        game_map: null,
    },
    getters: {
    },
    mutations: {
        updateSocket(state, socket) {
            state.socket = socket;
        },
        udpateOpponent(state, opponent) {
            state.opponent_username = opponent.username;
            state.opponent_photo = opponent.photo;
        },
        updateStatus(state, status) {
            state.status = status;
        },
        updateGameMap(state, game_map) {
            state.game_map = game_map;
        }
    },
    actions: {
    },
    modules: {}
}