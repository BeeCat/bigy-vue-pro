import request from '@/utils/request'

// 创建
export function createCustomer(data) {
  return request({
    url: '/zhh/customer/create',
    method: 'post',
    data: data
  })
}

// 更新
export function updateCustomer(data) {
  return request({
    url: '/zhh/customer/update',
    method: 'put',
    data: data
  })
}

// 删除
export function deleteCustomer(id) {
  return request({
    url: '/zhh/customer/delete?id=' + id,
    method: 'delete'
  })
}

// 获得
export function getCustomer(id) {
  return request({
    url: '/zhh/customer/get?id=' + id,
    method: 'get'
  })
}

// 获得分页
export function getCustomerPage(query) {
  return request({
    url: '/zhh/customer/page',
    method: 'get',
    params: query
  })
}

// 导出 Excel
export function exportCustomerExcel(query) {
  return request({
    url: '/zhh/customer/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
