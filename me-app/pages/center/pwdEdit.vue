<template class="bg-gradual-blue">
	<view class="page margin-tb-sm bg-gradual-blue">
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">密码修改</block>
		</cu-custom>
		<view class="cu-form-group">
			<input placeholder="请输入原始密码" v-model="oldPassword" name="input"></input>
		</view>
		<view class="cu-form-group">
			<input placeholder="请输入新密码" v-model="password" name="input"></input>
		</view>
		<view class="padding-sm margin-xs">
			<button class="cu-btn block bg-gradual-blue lg" @click="doReg">确认修改</button>
		</view>
	</view>
</template>

<script>
	import {mapState, mapMutations} from 'vuex';
	export default {
		data() {
			return {
				userInfo:'',
				userName: '',
				password: '',
				isLogin: false,
			}
		},
		onShow() {
			let that = this;
			this.userInfo = this.getUsInfo();
			if(this.userInfo && this.userInfo !=''){
				this.isLogin = true;
			}
			if(!that.isLogin){
				uni.showModal({
					title:"提示",
					content:"请先登录",
					showCancel:false,
					success() {
						that.back();
					}
				})
			}
		},
		onLoad(options) {
		},
		methods: {
			...mapMutations(['login','logout']),
			back(){ 
				uni.navigateBack({
				    delta: 1
				});
			},
			doReg() {
				if(!this.oldPassword || !this.password){
					uni.showModal({
						title:"请输入密码",
						showCancel:false,
					})
					return;
				}
				
				let param = {
					"userId": this.userInfo.userId,
					"oldPassword": this.oldPassword,
					"password": this.password
				};
				this.$request(this.appServiceUrl+'/api/resetPwd', param, "POST").then(res=>{
					if(res.data.code === 200){
						uni.showModal({
							title: '提示',
							content: "密码修改成功:" + this.password,
							showCancel: false,
							success: function (res) {
								uni.reLaunch({
									url: '/pages/Loign/login'
								});
							}
						})
					}else{
						uni.showModal({
							title: '修改失败',
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
