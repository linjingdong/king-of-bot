import axios from "axios";
import store from "@/store";

const request = axios.create({
    baseURL: 'http://localhost:8080/',
    timeout: 10000
})

request.interceptors.request.use(
    config => {
        let token = localStorage.getItem("jwt_token")
        if (token !== null) {
            config.headers.Authorization = "Bearer " + token;
        }
        return config;
    },
    error => Promise.error(error)
)
export default request