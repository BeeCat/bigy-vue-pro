import request from '@/utils/request'

// 创建班级学生
export function createClassStudent(data) {
  return request({
    url: '/zhh/class-student/create',
    method: 'post',
    data: data
  })
}

// 更新班级学生
export function updateClassStudent(data) {
  return request({
    url: '/zhh/class-student/update',
    method: 'put',
    data: data
  })
}

// 删除班级学生
export function deleteClassStudent(id) {
  return request({
    url: '/zhh/class-student/delete?id=' + id,
    method: 'delete'
  })
}

// 获得班级学生
export function getClassStudent(id) {
  return request({
    url: '/zhh/class-student/get?id=' + id,
    method: 'get'
  })
}

// 获得班级学生分页
export function getClassStudentPage(query) {
  return request({
    url: '/zhh/class-student/page',
    method: 'get',
    params: query
  })
}

// 导出班级学生 Excel
export function exportClassStudentExcel(query) {
  return request({
    url: '/zhh/class-student/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
