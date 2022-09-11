import request from '@/utils/request'

// 创建学员管理
export function create(data) {
  return request({
    url: '/student//create',
    method: 'post',
    data: data
  })
}

// 更新学员管理
export function update(data) {
  return request({
    url: '/student//update',
    method: 'put',
    data: data
  })
}

// 删除学员管理
export function deleteStu(id) {
  return request({
    url: '/student//delete?id=' + id,
    method: 'delete'
  })
}

// 获得学员管理
export function get(id) {
  return request({
    url: '/student//get?id=' + id,
    method: 'get'
  })
}

// 获得学员管理分页
export function getPage(query) {
  return request({
    url: '/student//page',
    method: 'get',
    params: query
  })
}

// 导出学员管理 Excel
export function exportExcel(query) {
  return request({
    url: '/student//export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
