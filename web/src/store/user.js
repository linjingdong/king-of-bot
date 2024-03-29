import { getToken } from "@/api/user/account/Login";
import { getInfo } from "@/api/user/account/Info";

export default {
    state: {
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
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
        }
    },
    actions: {
        async login(context, data) {
            await getToken(data.userInfo).then(resp => {
                if (resp.data.error_message === "success") {
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
            context.commit("logout");
        }
    },
    modules: {}
}