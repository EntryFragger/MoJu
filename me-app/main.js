import Vue from 'vue'
import App from './App'

import store from './store'

import request from './common/request.js';


import center from './pages/center/home.vue'
Vue.component('center', center)
import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom',cuCustom)

Vue.prototype.$util = require("./common/util.js");

Vue.config.productionTip = false

App.mpType = 'app'
let appServiceUrl = 'http://localhost:8081';
console.log('服务端url:'+appServiceUrl)
//服务端url
Vue.prototype.appServiceUrl = appServiceUrl;
Vue.prototype.$request = request;
Vue.prototype.getUsInfo = function(){
	let userInfo = uni.getStorageSync("uerInfo");
	return userInfo;
};

Vue.prototype.$store = store;
const app = new Vue({
	store,
    ...App
})
app.$mount()

 



