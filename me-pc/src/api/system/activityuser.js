import request from '@/utils/request'

// 查询加入得活动列表
export function listActivityuser(query) {
  return request({
    url: '/system/activityuser/list',
    method: 'get',
    params: query
  })
}

// 查询加入得活动详细
export function getActivityuser(id) {
  return request({
    url: '/system/activityuser/' + id,
    method: 'get'
  })
}

// 新增加入得活动
export function addActivityuser(data) {
  return request({
    url: '/system/activityuser',
    method: 'post',
    data: data
  })
}

// 修改加入得活动
export function updateActivityuser(data) {
  return request({
    url: '/system/activityuser',
    method: 'put',
    data: data
  })
}

// 删除加入得活动
export function delActivityuser(id) {
  return request({
    url: '/system/activityuser/' + id,
    method: 'delete'
  })
}

// 导出加入得活动
export function exportActivityuser(query) {
  return request({
    url: '/system/activityuser/export',
    method: 'get',
    params: query
  })
}