import request from "@/utils/request";

export let getList = () => {
    return request.get(`user/bot/getList/`);
}