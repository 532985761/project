import http from "@/axios";
/**
 * 获取澡堂位置信息
 */
export function getSite() {
    return http.get("/site/getSite");
}
/**
 * 改变澡堂位置信息
 */
export function changeSite(id) {
    return http.get("/site/changeSite/"+id);
}
