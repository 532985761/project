import http from '@/axios'

/**
 * 测试接口
 * 
 * @returns 
 */
export function testOrderApi () {
    return http.get('/isrpOrder/t/test')
}

/**
 * 分页查询订单模式
 * 
 * @param page 
 * @param size 
 * @returns 
 */
export function queryOrderModelByPage (page:number, size:number) {
    return http.get('/isrpOrder/isrpOrderModel/queryByPage/' + page + '/' + size)
}

/**
 * 添加订单模式
 * 
 * @param orderModelName
 * @returns 
 */
export function addOrderModel (orderModelName:string) {
    return http.post('/isrpOrder/isrpOrderModel/add', {orderModelName: orderModelName})
}

/**
 * 删除订单模式
 * 
 * @param orderModelId 
 * @returns 
 */
export function deleteOrderModel (orderModelId:number) {
    return http.delete('/isrpOrder/isrpOrderModel/delete/' + orderModelId)
}

/**
 * 修改订单模式
 * 
 * @param orderModel 
 * @returns 
 */
export function editOrderModel (orderModel:any) {
    return http.put('/isrpOrder/isrpOrderModel/edit', orderModel)
}

/**
 * 查询订单模式和订单流程
 * 
 * @returns 
 */
export function queryModelAndProcess () {
    return http.get('/isrpOrder/isrpOrderModel/queryModelAndProcess')
}

/**
 * 批量添加流程
 * 
 * @param form 
 * @returns 
 */
export function addProcessBatch (form : any) {
    return http.post('/isrpOrder/isrpOrderProcess/addBatch', form)
}

/**
 * 根据ModelId删除流程
 * 
 * @param number 
 * @returns 
 */
export function deleteProcessByModelId (orderModelId : number) {
    return http.delete('/isrpOrder/isrpOrderProcess/delete/' + orderModelId)
}

/**
 * 通过商家id查询待支付订单
 */
export function selectOrderByShopUserId(shopUserId){
    return http.get('/isrpOrder/isrpOrder/selectOrderIndexByShopUserId/'+shopUserId)
}

/**
 * 通过商家id查询已完成订单
 */
export function selectOrderFinishByShopUserId(shopUserId){
    return http.get('/isrpOrder/isrpOrder/selectOrderFinishByShopUserId/'+shopUserId)
}
/**
 * 通过商家id查询所有订单
 */
 export function selectOrderAllByShopUserId(shopUserId){
    return http.get('/isrpOrder/isrpOrder/selectOrderAllByShopUserId/'+shopUserId)
}
/**
 * 通过orderid删除订单
 */
export function deleteOrder(orderId){
    return http.post('/isrpOrder/isrpOrder/deleteOrder/'+orderId)
}