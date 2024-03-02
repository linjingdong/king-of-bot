import request from "@/utils/request";

export let getBotInfo = () => { return request.get(`pk/getBotInfo/`) }