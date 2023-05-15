<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">我发起的活动</block>
		</cu-custom>
		
		<view class="cu-list menu card-menu">
			<view class="padding-sm margin-xs margin-top-lg" >
				<button class="cu-btn block bg-white text-red lg" @tap="createActivity">发起活动</button>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				appServiceUrl: this.appServiceUrl,
				userInfo:{},
				isLogin: false,
			}
		},
		onShow() {
			let that = this;
			this.userInfo = this.getUsInfo();
			if(this.userInfo && this.userInfo !=''){
				this.isLogin = true;
				that.getList();
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
		mounted() {
	
		},
		methods: {
			createActivity(){
				
			},
			getList(){
				var param = {
					userId : this.userInfo.userId
				}
				this.$request(this.appServiceUrl+'/api/vip/list', param, "GET").then(res=>{
					if(res.data.code === 200){
						this.list = res.data.rows;
					}
				});
			},
			back(){
				uni.navigateBack({
				    delta: 1
				});
			},
		}
	}
</script>

<style>

</style>
