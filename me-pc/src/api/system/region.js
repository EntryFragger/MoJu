import request from '@/utils/request'

// 查询省市区列表
export function listRegion(query) {
  return request({
    url: '/system/region/list',
    method: 'get',
    params: query
  })
}

// 查询省市区详细
export function getRegion(code) {
  return request({
    url: '/system/region/' + code,
    method: 'get'
  })
}

// 新增省市区
export function addRegion(data) {
  return request({
    url: '/system/region',
    method: 'post',
    data: data
  })
}

// 修改省市区
export function updateRegion(data) {
  return request({
    url: '/system/region',
    method: 'put',
    data: data
  })
}

// 删除省市区
export function delRegion(code) {
  return request({
    url: '/system/region/' + code,
    method: 'delete'
  })
}

// 导出省市区
export function exportRegion(query) {
  return request({
    url: '/system/region/export',
    method: 'get',
    params: query
  })
}