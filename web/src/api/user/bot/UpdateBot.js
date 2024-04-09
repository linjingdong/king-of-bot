import request from "@/utils/request";

export let update = (botData) => {
    const headers = {
        'Content-Type': 'application/json', // 设置请求内容类型为 JSON
    };

    return request.post(`user/bot/update/`, botData, { headers });
}