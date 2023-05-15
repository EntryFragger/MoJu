export const request = (url, data, method)=>{
	uni.showLoading({title: '加载中', mask: true});
	return new Promise((resolve, reject)=>{
		// 封装主体：网络请求
		uni.request({
			url: url,
			data: data || {},		
			method: method || 'GET',// 默认值GET，如果有需要改动，在options中设定其他的method值
			success: (res) => {
				resolve(res)
			},
			fail: (err) =>{
				// 页面中弹框显示失败
				uni.showToast({
					title: '操作失败'
				})
				// 返回错误消息
				reject(err)
			},complete: ()=>{
				uni.hideLoading();
			}
		})
	})
}

export default request