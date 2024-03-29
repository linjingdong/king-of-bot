import request from "@/utils/request";

export let getInfo = () => {
    return request.get(`user/account/info/`);
}