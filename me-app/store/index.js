import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		username: '',//用户名/手机号码
		userId:'',//用户Id
	},
	mutations: {
		getUserInfo(state){
			return uni.getStorageSync("uerInfo");
		},
		login(state, provider) {
			state.isLogin = true;
			state.username = provider.username;
			state.userId = provider.userId;
			uni.setStorage({//将用户信息保存在本地  
                key: 'uerInfo',  
                data: provider  
            })  
		},
		//强制用户登录,如果没登录,则跳转登录页面
		forceLogin(state){
			if(!state.userId){
				//跳转登录页面
				uni.reLaunch({
					url: '/pages/Login/Login'
				});
			}
			return state.isLogin;
		},
		logout(state) {
			state.isLogin = false;
			state.username = '';
			state.useId = '';
			uni.removeStorage({  
                key: 'uerInfo'  
            })  
		},
	}
})

export default store
