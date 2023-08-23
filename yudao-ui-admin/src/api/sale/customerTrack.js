import request from '@/utils/request'

// 创建
export function createCustomerTrack(data) {
  return request({
    url: '/zhh/customer-track/create',
    method: 'post',
    data: data
  })
}

// 创建
export function createCustomerInvite(data) {
  return request({
    url: '/zhh/customer-track/inviteClass',
    method: 'post',
    data: data
  })
}

// 更新
export function updateCustomerTrack(data) {
  return request({
    url: '/zhh/customer-track/update',
    method: 'put',
    data: data
  })
}

// 删除
export function deleteCustomerTrack(id) {
  return request({
    url: '/zhh/customer-track/delete?id=' + id,
    method: 'delete'
  })
}

// 获得
export function getCustomerTrack(id) {
  return request({
    url: '/zhh/customer-track/get?id=' + id,
    method: 'get'
  })
}

// 获得分页
export function getCustomerTrackPage(query) {
  return request({
    url: '/zhh/customer-track/page',
    method: 'get',
    params: query
  })
}

// 导出 Excel
export function exportCustomerTrackExcel(query) {
  return request({
    url: '/zhh/customer-track/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
