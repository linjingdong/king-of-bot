import request from "@/utils/request";

export let getList = (page)=> {
    return request.get(`/record/getlist/${page}`);
}