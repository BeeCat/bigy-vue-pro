import request from '@/utils/request'

// 创建课程体系
export function createCourseContent(data) {
  return request({
    url: '/zhh/course-content/create',
    method: 'post',
    data: data
  })
}

// 更新课程体系
export function updateCourseContent(data) {
  return request({
    url: '/zhh/course-content/update',
    method: 'put',
    data: data
  })
}

// 删除课程体系
export function deleteCourseContent(id) {
  return request({
    url: '/zhh/course-content/delete?id=' + id,
    method: 'delete'
  })
}

// 获得课程体系
export function getCourseContent(id) {
  return request({
    url: '/zhh/course-content/get?id=' + id,
    method: 'get'
  })
}

// 获得课程体系分页
export function getCourseContentPage(query) {
  return request({
    url: '/zhh/course-content/page',
    method: 'get',
    params: query
  })
}

// 导出课程体系 Excel
export function exportCourseContentExcel(query) {
  return request({
    url: '/zhh/course-content/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
