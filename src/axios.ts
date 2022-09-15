// 封装请求
import axios from "axios";
import { ElMessage } from "element-plus";
import { userStore } from "@/store/user";
import router from "./router";
import { start, close } from '@/utils/nprogress';

const userstore = userStore()

// 请求
const http = axios.create({
  baseURL: "http://localhost:8888/ws",
  timeout: 6000,
});
// 请求拦截
http.interceptors.request.use(
  (config) => {
    // 加载
    start()
    // 请求头设置
    config.headers = {
      ...config.headers,
      // "content-type": "application/json",
    }
    return config;
  },
  (err) => {
  }
);
// 响应拦截
http.interceptors.response.use(
  (arr) => {
    // 对响应码的处理
    if (arr.status != 200) {
      ElMessage({
        message: arr.data,
        type: "warning",
        duration: 2 * 1000,
      });
    }
    // 关闭加载
    close();
    return arr;
  },
  (err) => {
    if(err.code == "ECONNABORTED") {
      ElMessage({
        message: "请求超时",
        type: "warning",
        duration: 2 * 1000,
      });
      close();
      return;
    }
    
    // 对异常处理
    ElMessage({
      message: err.response.data,
      type: "error",
      duration: 2 * 1000,
    });
    // 关闭加载
    close()
    return err.response;
  }
);
// 返出
export default http;
