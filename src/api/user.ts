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
    return http.post('/user/login', loginForm)
}
/**
 * 管理员登录
 * 
 * @returns 
 */
export function managerLogin (loginForm : any) {
    console.log(loginForm);
    
    return http.post('/user/managerLogin', loginForm)
}
/**
 * 获取用户信息
 *
 * @returns
 */
export function getAllUser () {
    return http.get('/user/getAllUser')
}
/**
 * 更改用户信息
 *
 * @returns
 */
export function updateUser (user) {
    return http.post('/user/updateUser',user)
}
/**
 * 删除用户
 *
 * @returns
 */
export function deleteUser (id) {
    return http.get('/user/deleteUser/'+id)
}



