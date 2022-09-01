import http from '@/axios'

/**
 * 用户注册
 * 
 * @returns 
 */
export function registerUser(yu:any) {

    return http.post('/user/register',yu)
}
/**
 * 测试接口
 * 
 * @returns 
 */
export function testUserApi () {
    return http.get('/isrpUser/t/test')
}

/**
 * 获取验证码
 * 
 * @returns 
 */
export function captcha () {
    return http({
        url: '/captcha',
        method: 'get',
        responseType: 'blob'
    })
}

/**
 * 用户登录
 * 
 * @returns 
 */
export function userLogin (loginForm : any) {
    return http.post('/isrpUser/isrpUser/userLogin', loginForm)
}
/**
 * 商家登录
 * 
 * @returns 
 */
export function businessLogin (loginForm : any) {
    return http.post('/isrpUser/isrpUser/businessLogin', loginForm)
}
/**
 * 管理员登录
 * 
 * @returns 
 */
export function managerLogin (loginForm : any) {
    console.log(loginForm);
    
    return http.post('/user/login', loginForm)
}
/**
 * 获取用户信息
 * 
 * @returns 
 */
export function getOneUserInfo (userId:string) {
    return http.post('/isrpUser/isrpUser/getUserInfo/?userId='+userId)
}
/**
 * 
 *用户注册
 * @returns 
 */
//  export function registerUser (user:any) {
//     console.log(user);
    
//     return http.post('/isrpUser/isrpUser/register',JSON.stringify(user))
// }

/**
 * 分页查询用户
 * 
 * @param page 
 * @param size 
 * @returns 
 */
export function queryUserByPage (page:number, size:number) {
    return http.get('/isrpUser/isrpUser/queryByPage/' + page + "/" + size)
}

/**
 * 分页查询用户
 * 
 * @param page 
 * @param size 
 * @returns 
 */
export function addUser (user:any) {
    return http.post('/isrpUser/isrpUser/add', user)
}

/**
 * 删除用户
 * 
 * @param userId 
 * @returns 
 */
export function deleteUser (userId:any) {
    return http.delete('/isrpUser/isrpUser/delete/' + userId)
}
