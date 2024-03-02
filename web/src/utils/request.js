import axios from "axios";

const request = axios.create({
    baseURL:'http://localhost:8080/',
    timeout:10000
})

export default request