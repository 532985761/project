import { userStore } from "@/store/user";
import router from "@/router";
import { ElMessage } from "element-plus";

export function logout(route:string){
     userStore().logout();
    router.push(
      route
    );
    ElMessage({
      message: "退出成功",
      type: "success",
      duration: 2 * 1000,
    });
}
