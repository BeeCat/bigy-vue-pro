import request from '@/utils/request'

// 创建课程班级
export function createCourseClass(data) {
  return request({
    url: '/education/course-class/create',
    method: 'post',
    data: data
  })
}

// 更新课程班级
export function updateCourseClass(data) {
  return request({
    url: '/education/course-class/update',
    method: 'put',
    data: data
  })
}

// 删除课程班级
export function deleteCourseClass(id) {
  return request({
    url: '/education/course-class/delete?id=' + id,
    method: 'delete'
  })
}

// 获得课程班级
export function getCourseClass(id) {
  return request({
    url: '/education/course-class/get?id=' + id,
    method: 'get'
  })
}

// 获得课程班级分页
export function getCourseClassPage(query) {
  return request({
    url: '/education/course-class/page',
    method: 'get',
    params: query
  })
}

// 导出课程班级 Excel
export function exportCourseClassExcel(query) {
  return request({
    url: '/education/course-class/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
