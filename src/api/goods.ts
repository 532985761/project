import http from "@/axios";

/**
 * 获取所有商品
 */
export function getAllGoods() {
  return http.get("/goods/getAllGoods");
}

/**
 * 添加商品
 */
export function addGoods(goods:any) {
  return http.post("/goods/addGoods",goods);
}
/**
 * 编辑商品
 */
export function editGoods(goods:any) {
  return http.post("/goods/editGoods",goods);
}
/**
 * 删除商品
 */
export function deleteGoods(id) {
  return http.post("/goods/deleteGoods/"+id);
}