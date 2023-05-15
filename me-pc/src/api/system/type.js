import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listType(query) {
  return request({
    url: '/system/type/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getType(id) {
  return request({
    url: '/system/type/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addType(data) {
  return request({
    url: '/system/type',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateType(data) {
  return request({
    url: '/system/type',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delType(id) {
  return request({
    url: '/system/type/' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportType(query) {
  return request({
    url: '/system/type/export',
    method: 'get',
    params: query
  })
}