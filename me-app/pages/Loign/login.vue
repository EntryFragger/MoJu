<template class="bg-gradual-blue">
	<view class="page margin-tb-sm bg-gradual-blue">
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">登录</block>
		</cu-custom>
		<view class="flex margin-sm padding-top-xl">
			<text class="text-xxl text-bold">欢迎登录<br>组局活动小程序</text>
		</view>
		<view class="cu-form-group">
			<input placeholder="请输入手机号/邮箱" v-model="userName" name="input"></input>
		</view>
		<view class="cu-form-group">
			<input placeholder="请输入密码" v-model="password" name="input"></input>
		</view>
		<view class="padding-sm margin-xs">
			<button class="cu-btn block bg-gradual-blue lg" @click="doLogin">登录</button>
			<button class="cu-btn block bg-gradual-orange lg" @tap="doReg">去注册</button>
		</view>
	</view>
</template>

<script>
	import {mapState, mapMutations} from 'vuex';
	export default {
		data() {
			return {
				userName: 'user1',
				password: '123456',
				rsUrl:'',//来源url 从哪里来就要跳到哪里去
			}
		},
		onShow() {
			//清除登录信息
			this.logout();
		},
		onLoad(options) {
			console.log(options);
			if(options && options.rs){
				this.rsUrl = options.rs;
			}
		},
		methods: {
			...mapMutations(['login','logout']),
			back(){ 
				// uni.switchTab({
				//     url: '/pages/center/home'
				// });
				uni.navigateBack({
				    delta: 1
				});
			},
			doReg(){
				uni.navigateTo({
					url: 'reg'
				});
			},
			doLogin() {
				if(!this.userName || !this.password){
					uni.showModal({
						title:"请输入账号密码",
						showCancel:false,
					})
					return;
				}
				
				let param = {
					"userName": this.userName,
					"password": this.password
				};
				this.$request(this.appServiceUrl+'/api/login', param, "POST").then(res=>{
					if(res.data.code === 200){
						this.login(res.data.data);
						console.log(this.rsUrl);
						uni.navigateBack({
						    delta: 1
						});
					}else{
						uni.showModal({
							title: '登录失败',
							content: res.data.msg,
							showCancel: false,
							success: function (res) {
							}
						})
					}
				});
				
				// uni.showLoading({
				// 	title: '登录中...',
				// 	mask: true
				// });
			},
		}
	}
</script>

<style>
	page{
	}
</style>
