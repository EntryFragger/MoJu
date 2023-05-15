<template name="center">
	<view class="page margin-tb-sm bg-gradual-blue">
		<cu-custom bgColor="bg-gradual-blue" >
			<block slot="content">我的</block>
		</cu-custom>
		<view class="absolute-orange-bg"></view>
		<view class="flex padding-lg">
			<view class="cu-avatar round xl">
				
				<view class="cu-avatar xl round " :style="'background-image:url('+(userInfo.avatar?appServiceUrl+userInfo.avatar:'/static/bg.jpeg')+');'"></view>
				</view>
			<view class="content margin-lr-lg">
				<view class="text-white u-font-18" v-if="userInfo.userName">{{userInfo.userName}}</view>
				<view class="text-white u-font-18" v-if="!userInfo.userName" @tap="exit">请登录!</view>
				<view class="padding-top-sm text-white" v-if="userInfo.phonenumber">{{userInfo.phonenumber}}</view>
				<view class="text-white u-font-18" v-if="userInfo.userName">硬币数: 0 个</view>
			</view>
		</view>
		<view class="cu-list menu card-menu">
			
			<view class="cu-item arrow">
				<view class="content" hover-class="none" @tap="toPage('/pages/his/activityuser/activityuser')" >
					<text class="cuIcon-evaluate text-black"></text>
					<text class="text-black">参与活动列表</text>
				</view>
			</view>
			<view class="cu-item arrow">
				<view class="content" hover-class="none" @tap="toPage('/pages/activity/createActivity/createList')" >
					<text class="cuIcon-evaluate text-black"></text>
					<text class="text-black">发起活动</text>
				</view>
			</view>
			<view class="cu-item arrow">
				<view class="content" hover-class="none" @tap="toPage('/pages/his/vip/vipList/vipList')">
					<text class="cuIcon-favorfill text-black"></text>
					<text class="text-black">会员充值</text>
				</view>
			</view>
			<view class="cu-item arrow">
				<view class="content" hover-class="none" @tap="toPage('/pages/center/pwdEdit')">
					<text class="cuIcon-lock text-black"></text>
					<text class="text-black">修改密码</text>
				</view>
			</view>
		</view>
		<view class="padding-sm margin-xs margin-top-lg" v-if="isLogin">
			<button class="cu-btn block bg-white text-red lg" @tap="exit">退出登录</button>
		</view>
		<view class="padding-sm margin-xs margin-top-lg" v-else>
			<button class="cu-btn block bg-white text-red lg" @tap="exit">去 登 录</button>
		</view>
	</view>
</template>

<script>
	import {mapState, mapMutations} from 'vuex'
	export default{
		name: "center",
		data() {
			return {
				userInfo:{},
				isLogin: false,
			}
		},
		onShow() {
			this.userInfo = this.getUsInfo();
			if(this.userInfo && this.userInfo !=''){
				this.isLogin = true;
			}
		},
		methods: {
			...mapMutations(['login']),
			getInfo(){
				this.userInfo = this.getUsInfo();
			},
			//跳转到登陆页面
			exit() {
				uni.navigateTo({
					url: '/pages/Loign/login?rs=/pages/center/home'
				});
			},
			toPage(url){
				uni.navigateTo({
					url: url
				});
			},
		}
	}
</script>
 
<style>
.page{
	background-color: #F0AD4E;
}
</style>
