import request from '@/utils/request'

// 创建课程教室
export function createClassRoom(data) {
  return request({
    url: '/zhh/class-room/create',
    method: 'post',
    data: data
  })
}

// 更新课程教室
export function updateClassRoom(data) {
  return request({
    url: '/zhh/class-room/update',
    method: 'put',
    data: data
  })
}

// 删除课程教室
export function deleteClassRoom(id) {
  return request({
    url: '/zhh/class-room/delete?id=' + id,
    method: 'delete'
  })
}

// 获得课程教室
export function getClassRoom(id) {
  return request({
    url: '/zhh/class-room/get?id=' + id,
    method: 'get'
  })
}

// 获得课程教室分页
export function getClassRoomPage(query) {
  return request({
    url: '/zhh/class-room/page',
    method: 'get',
    params: query
  })
}

// 导出课程教室 Excel
export function exportClassRoomExcel(query) {
  return request({
    url: '/zhh/class-room/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
