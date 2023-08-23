import request from '@/utils/request'

// 创建学生账户
export function createStudentAccount(data) {
  return request({
    url: '/zhh/student-account/create',
    method: 'post',
    data: data
  })
}

// 创建学生账户
export function createBuyCourse(data) {
  return request({
    url: '/zhh/student-account/buyCourse',
    method: 'post',
    data: data
  })
}

// 更新学生账户
export function updateStudentAccount(data) {
  return request({
    url: '/zhh/student-account/update',
    method: 'put',
    data: data
  })
}

// 删除学生账户
export function deleteStudentAccount(id) {
  return request({
    url: '/zhh/student-account/delete?id=' + id,
    method: 'delete'
  })
}

// 获得学生账户
export function getStudentAccount(id) {
  return request({
    url: '/zhh/student-account/get?id=' + id,
    method: 'get'
  })
}

// 获得学生账户分页
export function getStudentAccountPage(query) {
  return request({
    url: '/zhh/student-account/page',
    method: 'get',
    params: query
  })
}

// 导出学生账户 Excel
export function exportStudentAccountExcel(query) {
  return request({
    url: '/zhh/student-account/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
