import request from "@/utils/request";


export let add = (botData) => {
    const headers = {
        'Content-Type': 'application/json', // 设置请求内容类型为 JSON
    };
    return request.post(`user/bot/add/`, botData, { headers });
}