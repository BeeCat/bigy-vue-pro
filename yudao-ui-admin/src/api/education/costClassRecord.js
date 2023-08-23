import request from '@/utils/request'

// 创建任课记录
export function createCostClassRecord(data) {
  return request({
    url: '/zhh/cost-class-record/create',
    method: 'post',
    data: data
  })
}

// 创建任课记录
export function handCostClassRecord(data) {
  return request({
    url: '/zhh/cost-class-record/handCost',
    method: 'post',
    data: data
  })
}

// 更新任课记录
export function updateCostClassRecord(data) {
  return request({
    url: '/zhh/cost-class-record/update',
    method: 'put',
    data: data
  })
}

// 删除消课记录
export function deleteCostClassRecord(id) {
  return request({
    url: '/zhh/cost-class-record/delete?id=' + id,
    method: 'delete'
  })
}

// 获得消课记录
export function getCostClassRecord(id) {
  return request({
    url: '/zhh/cost-class-record/get?id=' + id,
    method: 'get'
  })
}

// 获得消课记录分页
export function getCostClassRecordPage(query) {
  return request({
    url: '/zhh/cost-class-record/page',
    method: 'get',
    params: query
  })
}

// 导出消课记录 Excel
export function exportCostClassRecordExcel(query) {
  return request({
    url: '/zhh/cost-class-record/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
