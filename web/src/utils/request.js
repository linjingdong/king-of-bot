import axios from "axios";
import store from "@/store";

const request = axios.create({
    baseURL: 'http://localhost:8080/',
    timeout: 10000
})

request.interceptors.request.use(
    config => {
        config.headers.Authorization = "Bearer " + store.state.user.token;
        return config;
    },
    error => Promise.error(error)
)
export default request