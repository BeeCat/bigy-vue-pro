import request from '@/utils/request'

// 创建todolist 待办项
export function createTodoList(data) {
  return request({
    url: '/zhh/todo-list/create',
    method: 'post',
    data: data
  })
}

// 更新todolist 待办项
export function updateTodoList(data) {
  return request({
    url: '/zhh/todo-list/update',
    method: 'put',
    data: data
  })
}

// 删除todolist 待办项
export function deleteTodoList(id) {
  return request({
    url: '/zhh/todo-list/delete?id=' + id,
    method: 'delete'
  })
}

// 获得todolist 待办项
export function getTodoList(id) {
  return request({
    url: '/zhh/todo-list/get?id=' + id,
    method: 'get'
  })
}

// 获得todolist 待办项分页
export function getTodoListPage(query) {
  return request({
    url: '/zhh/todo-list/page',
    method: 'get',
    params: query
  })
}

// 导出todolist 待办项 Excel
export function exportTodoListExcel(query) {
  return request({
    url: '/zhh/todo-list/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
