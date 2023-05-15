import request from '@/utils/request'

// 查询设备登记养护，维修，升级改造，故障，报废登记列表
export function listReg(query) {
  return request({
    url: '/system/reg/list',
    method: 'get',
    params: query
  })
}

// 查询设备登记养护，维修，升级改造，故障，报废登记详细
export function getReg(id) {
  return request({
    url: '/system/reg/' + id,
    method: 'get'
  })
}

// 新增设备登记养护，维修，升级改造，故障，报废登记
export function addReg(data) {
  return request({
    url: '/system/reg',
    method: 'post',
    data: data
  })
}

// 修改设备登记养护，维修，升级改造，故障，报废登记
export function updateReg(data) {
  return request({
    url: '/system/reg',
    method: 'put',
    data: data
  })
}

// 删除设备登记养护，维修，升级改造，故障，报废登记
export function delReg(id) {
  return request({
    url: '/system/reg/' + id,
    method: 'delete'
  })
}

// 导出设备登记养护，维修，升级改造，故障，报废登记
export function exportReg(query) {
  return request({
    url: '/system/reg/export',
    method: 'get',
    params: query
  })
}