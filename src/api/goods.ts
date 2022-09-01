import http from "@/axios";

/**
 * 测试接口
 *
 * @returns
 */
export function getAllGoods() {
  return http.get("/goods/getAllGoods");
}