import { getToken } from "@/api/user/account/Login";
import { getInfo } from "@/api/user/account/Info";
import store from ".";

export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
        pulling_info: true, // 表示是否正在拉取信息
    },
    getters: {

    },
    mutations: {
        updateUser(state, user) {
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        updateToken(state, token) {
            state.token = token;
        },
        logout(state) {
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
            state.pulling_info = true; // 默认未登录或未刷新从云端拉取数据之前都在拉取数据
        },
        updatePullingInfo(state, pulling_info) {
            state.pulling_info = pulling_info;
        }
    },
    actions: {
        async login(context, data) {
            await getToken(data.userInfo).then(resp => {
                if (resp.data.error_message === "success") {
                    localStorage.setItem("jwt_token", resp.data.token);
                    context.commit("updateToken", resp.data.token);
                } else {
                    data.Error(resp)
                }
            }).catch(error => {
                data.Error(error)
            })
        },
        async getInfo(context, data) {
            await getInfo().then(resp => {
                if (resp.data.error_message === 'success') {
                    context.commit("updateUser", {
                        ...resp.data,
                        is_login: true,
                    });
                } else {
                    console.log(resp)
                }

            }).catch(error => {
                console.log(error)
            })
        },
        logout(context) {
            localStorage.removeItem("jwt_token");
            context.commit("logout");
            console.log(store.state.user.pulling_info)
        }
    },
    modules: {}
}