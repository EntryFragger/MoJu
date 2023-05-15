<template class="bg-gradual-blue">
	<view class="page margin-tb-sm bg-gradual-blue">
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">注册</block>
		</cu-custom>
		<view class="flex margin-sm padding-top-xl">
			<text class="text-xxl text-bold">欢迎注册<br><strong>组局活动小程序</strong></text>
		</view>
		<view class="cu-form-group">
			<input placeholder="请输入手机号/邮箱" v-model="userName" name="input"></input>
		</view>
		<view class="cu-form-group">
			<input placeholder="请输入密码" v-model="password" name="input"></input>
		</view>
		<view class="padding-sm margin-xs">
			<button class="cu-btn block bg-gradual-blue lg" @click="doReg">确认注册</button>
		</view>
	</view>
</template>

<script>
	import {mapState, mapMutations} from 'vuex';
	export default {
		data() {
			return {
				userName: '',
				password: '',
				rsUrl:'',//来源url 从哪里来就要跳到哪里去
			}
		},
		onShow() {
			//退出登录
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
			doReg() {
				if(!this.userName || !this.password){
					uni.showModal({
						title:"请输入账号密码",
						showCancel:false,
					})
					return;
				}
				
				let param = {
					"username": this.userName,
					"password": this.password
				};
				this.$request(this.appServiceUrl+'/register', param, "POST").then(res=>{
					if(res.data.code === 200){
						uni.showModal({
							title: '提示',
							content: "注册成功",
							showCancel: false,
							success: function (res) {
								uni.navigateBack({
									delta: 1
								});
							}
						})
					}else{
						uni.showModal({
							title: '注册失败',
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
