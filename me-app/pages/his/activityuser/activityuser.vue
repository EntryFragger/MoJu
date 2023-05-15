<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">参与的活动</block>
		</cu-custom>
		<view class="padding bg-white">
			<view class="flex" v-for="(item,index) in list" :key="index"  @tap="toDetail(item.activityId)">
				<view class="flex-sub bg-white padding-sm margin-xs radius">
					<view class="sub">
						<image :src="appServiceUrl+item.url" style="width: 100%; height: 250rpx;"></image>
					</view>
				</view>
				<view class="flex-sub bg-white padding-sm margin-xs radius">
					<view class="sub">
						<view class="text-sm">{{item.activityName}}</view>
						<view class="padding-top-xs">
							<view>
								活动状态：<text class="text-sm text-red"></text> <text class="text-df text-red">{{item.state}}</text>
							</view>
							<view>
								联系人：<text class="text-sm text-red"></text> <text class="text-df text-red">{{item.receiver}}</text>
							</view>
							<view>
								联系电话：<text class="text-sm text-red"></text> <text class="text-df text-red">{{item.phone}}</text>
							</view>
							<view>
								备注：<text class="text-sm text-red"></text> <text class="text-df text-red">{{item.message}}</text>
							</view>
							<view>
								<text class="text-sm text-red"></text> <text class="text-df text-red">{{item.createTime}}</text>
							</view>
						</view>
						<view class="padding-top-xs">
							<view>
								<button class="cu-btn round sm lines-gray" v-if="item.state ==='正常'" @tap.stop="cancel(item)">退出活动</button>
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
			cancel(item, status){
				let that = this;
				let param = {id: item.id};
				that.$request(that.appServiceUrl+'/api/activityuser/del/'+item.id, {}, "POST").then(rs=>{
					uni.showModal({
						title:'提示',
						content: "退出成功!",
						showCancel:false,
						success() {
							that.getList();
						}
					});
				});
			},
			getList(){
				var param = {
					userId : this.userInfo.userId
				}
				this.$request(this.appServiceUrl+'/api/activityuser/list', param, "GET").then(res=>{
					if(res.data.code === 200){
						this.list = res.data.rows;
					}
				});
			},
			toDetail(id){
				uni.navigateTo({
					url: '/pages/activity/detail/detail?id='+id
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
