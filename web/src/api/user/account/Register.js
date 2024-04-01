import request from "@/utils/request";

export let register = (userInfo) => {
    return request.post(`user/account/register/`, userInfo)
}