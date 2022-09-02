import http from "@/axios";
/**
 * 获取储物柜信息
 */
export function getStore() {
    return http.get("/store/getStore");
}
/**
 * 获取通知信息
 */
export function getNotice() {
    return http.get("/notice/getNotice");
}
