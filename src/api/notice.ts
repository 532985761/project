import http from "@/axios";

/**
 * 添加通知
 */
export function addNotice(notice) {
    return http.post("/notice/addNotice",notice);

}


/**
 * 删除通知
 */
export function deleteNotice(id) {
    return http.get("/notice/deleteNotice/"+id);
}