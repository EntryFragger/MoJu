import request from '@/utils/request'

// 查询充值优惠列表
export function listVip(query) {
  return request({
    url: '/system/vip/list',
    method: 'get',
    params: query
  })
}

// 查询充值优惠详细
export function getVip(id) {
  return request({
    url: '/system/vip/' + id,
    method: 'get'
  })
}

// 新增充值优惠
export function addVip(data) {
  return request({
    url: '/system/vip',
    method: 'post',
    data: data
  })
}

// 修改充值优惠
export function updateVip(data) {
  return request({
    url: '/system/vip',
    method: 'put',
    data: data
  })
}

// 删除充值优惠
export function delVip(id) {
  return request({
    url: '/system/vip/' + id,
    method: 'delete'
  })
}

// 导出充值优惠
export function exportVip(query) {
  return request({
    url: '/system/vip/export',
    method: 'get',
    params: query
  })
}