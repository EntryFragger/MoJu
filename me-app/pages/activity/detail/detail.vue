<template>
	<view>
		<cu-custom bgColor="bg-gradual-blue" :isBack="true" @click-left="back">
			<block slot="content">活动详情</block>
		</cu-custom>
		<view class="padding margin">
			<image :src="appServiceUrl+activity.url"></image>
		</view>
		<view class="cu-card dynamic ">
			<view class="cu-item shadow">
				<view class="cu-list">
					<view class="cu-item padding grid padding-bottom-xs">
						<view class="flex-sub text-left text-xl">{{activity.title}}</view>
					</view>
					<view class="cu-item padding flex padding-top-xs">
						<view class="flex-sub text-left text-xl text-gray">分类名称: {{activity.typeName}}</view>
					</view>
					<view class="cu-item padding flex padding-top-xs">
						<view class="flex-sub text-left text-xl text-gray">活动地区: {{activity.provinceName}}-{{activity.cityName}}-{{activity.areaName}}</view>
					</view>
					<view class="cu-item padding flex padding-top-xs">
						<view class="flex-sub text-left text-xl text-gray">计划参与人数: {{activity.pnum?activity.pnum : '不限'}}</view>
					</view>
					<view class="cu-item padding flex padding-top-xs">
						<view class="flex-sub text-left text-xl text-gray">当前报名人数: {{activity.num}}</view>
					</view>
					<view class="cu-item padding flex padding-top-xs">
						<view class="flex-sub text-left text-xl text-gray">发布时间: {{activity.createTime}}</view>
					</view>
				</view>
			</view>
		</view>
		<view class="cu-bar bg-white solid-bottom" >
			<view class="action">
				<text class="cuIcon-titles text-orange "></text> 活动详情
			</view>
		</view>
		<view class="cu-card dynamic ">
			<view class="cu-item shadow padding-top-sm">
				<view class="text-content">
					{{activity.remark}}
				</view>
			</view>
		</view>
		<view class="cu-bar bg-white tabbar border shop">
			<view class="btn-group" v-if="!isActivity">
				<button class="cu-btn bg-red round shadow-blur" @tap="modalName='Modal'">立即参与</button>
			</view>
			<view class="btn-group" v-if="isActivity">
				<button class="cu-btn bg-red round shadow-blur" >已参与</button>
			</view>
		</view>
		
		<view class="cu-modal" :class="modalName=='Modal'?'show':''">
			<view class="cu-dialog">
				<view class="cu-bar bg-white justify-end">
					<view class="content">填写参与基本信息</view>
					<view class="action" @tap="modalName=''">
						<text class="cuIcon-close text-red"></text>
					</view>
				</view>
				<view class="padding-xl">
					<form>
						<view class="cu-form-group">
							<input placeholder="请输入姓名" v-model="activityuser.receiver" name="input"></input>
						</view>
						<view class="cu-form-group">
							<input placeholder="请输入联系电话" v-model="activityuser.phone" name="input"></input>
						</view>
						<view class="cu-form-group">
							<textarea maxlength="-1" placeholder="请输入留言" v-model="activityuser.message"></textarea>
						</view>
						<view class="padding">
							<button class="cu-btn block bg-blue margin-tb-sm lg"  @tap="subactivityuser">确认参加</button>
							<button class="cu-btn block line-blue margin-tb-sm lg" @tap="modalName=''">取消</button>
						</view>
					</form>
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
				activity: {},
				modalName: '',
				activityuser:{
					userId:'',
					userName:'',
					activityId:'',
					activityName:'',
					url:'',
					receiver: '', //联系人姓名
					phone:'', //联系电话
					message:'' //消息
				},
				userInfo: {},
				isLogin: false,
				isActivity : false, //是否报名
			}
		},
		mounted() {
			this.userInfo = this.getUsInfo();
			if(this.userInfo && this.userInfo !=''){
				this.isLogin = true;
			}
			if(this.id){
				this.getactivity();
				if(this.isLogin){
					this.getisActivity();
				}
			}
		},
		 onLoad(option) { 
			this.id = option.id;
		},
		methods: {
			subactivityuser(){
				if(!this.isLogin){
					return;
				}
				if(!this.activityuser.receiver){
					uni.showModal({
						title:'提示',
						content:'请输入联系人姓名',
						showCancel:false
					})
					return;
				}
				if(!this.activityuser.phone){
					uni.showModal({
						title:'提示',
						content:'请输入联系电话',
						showCancel:false
					})
					return;
				}
				this.activityuser.userId = this.userInfo.userId;
				this.activityuser.userName = this.userInfo.userName;
				this.activityuser.activityId = this.activity.id;
				this.activityuser.activityName = this.activity.title;
				this.activityuser.url = this.activity.url;
				let that = this;
				this.$request(this.appServiceUrl+'/api/activityuser/add', this.activityuser, "POST").then(res=>{
					if(res.data.code == 200){
						uni.showModal({
							title:'提示',
							content:'报名成功',
							success(e) {
								that.modalName = '';
								that.isActivity = true;
							}
						});
						this.getactivity();
					}else{
						uni.showModal({
							title:'失败',
							content: res.data.msg,
							showCancel:false
						});
					}
				});
			},
			rest(){
				this.order = {
					userId:'',
					userName:'',
					activityId:'',
					activityName:'',
					url:'',
					receiver:'', //联系人
					phone:'', //联系电话
					message:'' //消息
				}
			},
			delLike(){
				
			},
			addLike(){
			},
			getisActivity(){
				let that = this;
				let param = {userId: this.userInfo.userId, activityId: this.id};
				this.$request(this.appServiceUrl+'/api/activityuser/list', param).then(res=>{
					if(res.data.rows.length > 0){
						that.isActivity = true;
						console.log("that.isActivity = ", that.isActivity);
					}
				});
			},
			getactivity(){
				this.$request(this.appServiceUrl+'/api/activity/'+this.id, {}).then(res=>{
					this.activity = res.data.data;
				});
			},
			back(){
				uni.navigateBack({
				    delta: 1
				});
			}
		}
	}
</script>

<style>

</style>
