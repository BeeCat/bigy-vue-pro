import request from '@/utils/request'

// 创建学生账户
export function createStudentAccountRecord(data) {
  return request({
    url: '/zhh/student-account-record/create',
    method: 'post',
    data: data
  })
}

// 更新学生账户
export function updateStudentAccountRecord(data) {
  return request({
    url: '/zhh/student-account-record/update',
    method: 'put',
    data: data
  })
}

// 删除学生账户
export function deleteStudentAccountRecord(id) {
  return request({
    url: '/zhh/student-account-record/delete?id=' + id,
    method: 'delete'
  })
}

// 获得学生账户
export function getStudentAccountRecord(id) {
  return request({
    url: '/zhh/student-account-record/get?id=' + id,
    method: 'get'
  })
}

// 获得学生账户分页
export function getStudentAccountRecordPage(query) {
  return request({
    url: '/zhh/student-account-record/page',
    method: 'get',
    params: query
  })
}

// 导出学生账户 Excel
export function exportStudentAccountRecordExcel(query) {
  return request({
    url: '/zhh/student-account-record/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
