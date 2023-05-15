<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">充值列表</block>
		</cu-custom>
		
		<view class="padding bg-white">
			<view class="flex" v-for="(item,index) in list" :key="index"  @tap="toDetail(item.goodsId)">
				<view class="flex-sub bg-white padding-sm margin-xs radius">
					<view class="flex">
						<view class="text-sm">{{item.name}}</view>
							<view class="sub padding-left-lg" style="text-decoration: line-through;">
								<text class="text-red" style="">￥</text> <text class="text-sm text-red">{{item.bmoney}}</text>
							</view>
							<view class="sub padding-left-sm">
								<text class="text-red">￥</text> <text class="text-xl text-red">{{item.emoney}}</text>
							</view>
							<view class="padding-top-xs padding-left-xs">
								<view>
									<button class="cu-btn round sm lines-orange" >充值</button>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				appServiceUrl: this.appServiceUrl,
				list:[],
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
