import request from '@/utils/request'

// 创建课程包套餐
export function createCoursePackage(data) {
  return request({
    url: '/zhh/course-package/create',
    method: 'post',
    data: data
  })
}

// 更新课程包套餐
export function updateCoursePackage(data) {
  return request({
    url: '/zhh/course-package/update',
    method: 'put',
    data: data
  })
}

// 删除课程包套餐
export function deleteCoursePackage(id) {
  return request({
    url: '/zhh/course-package/delete?id=' + id,
    method: 'delete'
  })
}

// 获得课程包套餐
export function getCoursePackage(id) {
  return request({
    url: '/zhh/course-package/get?id=' + id,
    method: 'get'
  })
}

// 获得课程包套餐分页
export function getCoursePackagePage(query) {
  return request({
    url: '/zhh/course-package/page',
    method: 'get',
    params: query
  })
}

// 导出课程包套餐 Excel
export function exportCoursePackageExcel(query) {
  return request({
    url: '/zhh/course-package/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
