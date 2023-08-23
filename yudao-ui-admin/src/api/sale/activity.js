import request from '@/utils/request'

// 创建
export function createActivity(data) {
  return request({
    url: '/zhh/activity/create',
    method: 'post',
    data: data
  })
}

// 更新
export function updateActivity(data) {
  return request({
    url: '/zhh/activity/update',
    method: 'put',
    data: data
  })
}

// 删除
export function deleteActivity(id) {
  return request({
    url: '/zhh/activity/delete?id=' + id,
    method: 'delete'
  })
}

// 获得
export function getActivity(id) {
  return request({
    url: '/zhh/activity/get?id=' + id,
    method: 'get'
  })
}

// 获得分页
export function getActivityPage(query) {
  return request({
    url: '/zhh/activity/page',
    method: 'get',
    params: query
  })
}

// 导出 Excel
export function exportActivityExcel(query) {
  return request({
    url: '/zhh/activity/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
