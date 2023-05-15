<template>
	<view>
		<cu-custom bgColor="bg-gradual-pink" :isBack="false"><block slot="backText"></block></cu-custom>
		<view class="cu-bar bg-white search fixed" :style="[{top:CustomBar + 'px'}]" v-show="modalName !=='viewModal'">
			<view class="search-form round">
				<text class="cuIcon-search"></text>
				<input type="text" placeholder="输入搜索的关键词" v-model="keys" confirm-type="search"></input>
			</view>
			<view class="action">
				<button class="cu-btn bg-gradual-green shadow-blur round" @tap="search">搜索</button>
				<view class="bg-yellow padding-sm margin-xs radius" @tap="showModal" data-target="viewModal">筛选</view>
			</view>
		</view>
		<view class="DrawerWindow " :class="modalName=='viewModal'?'show':''">
			<view class="cu-list menu  margin-top-xl margin-bottom-xl shadow-lg">
				
				<view class="cu-item" >
					<view class="content flex">
						<text class="text-bold text-xl">类别</text>
					</view>
				</view>
				<view class="cu-item" >
					<view class="content">
						<view class='flex '>
							<view class="">
								<view style="margin-left: 30rpx; margin-top: 20rpx;" class='cu-tag text-xl' :class="type ==item.dictValue?'bg-blue':'line-blue'" @tap="typeClick(item.dictValue)" v-for="(item, index) in typeList" :key="index">{{item.dictLabel}}</view>
							</view>
						</view>
					</view>
				</view>
				<view class="bg-white">
					<view class="flex solid-bottom padding justify-end">
						<view class="bg-grey padding-sm margin-xs radius"><button class="cu-btn bg-grey sub" @tap="modalName=null">取  消</button></view>
						<view class="bg-blue padding-sm margin-xs radius"><button class="cu-btn bg-blue sub" @tap="search">确  定</button></view>
					</view>
				</view>
			</view>
		</view>
		<view class="padding cu-bar bg-white" >
			<view class="cu-form-group">
				<view class="title">选择地区</view>
				<picker :value="areaValue" mode="region" @change="bindPickerChange" @columnchange="columnchange">
					<view v-if="areaValueStr === ''">请选择地区</view>
					<view v-else>{{areaValueStr}}</view>
				</picker>
				
				<text class="lg text-gray cuIcon-close" @click="clearArea()"></text>
			</view>
		</view>
		<view class="padding bg-white">
			<view class="flex" v-for="(item,index) in activityList" :key="index"  @tap="toDetail(item.id)">
				<view class="flex-sub bg-white padding-sm margin-xs radius">
					<view class="sub">
						<image :src="appServiceUrl+item.url" style="width: 100%; height: 250rpx;"></image>
					</view>
				</view>
				<view class="flex-sub bg-white padding-sm margin-xs radius">
					<view class="sub">
						<view class="text-xxl">{{item.title}}</view>
						<view class="padding-top-xl text-gray">
							<text>{{item.provinceName}}-{{item.cityName}}-{{item.areaName}}</text> 
						</view>
						<view class="padding-top-xl text-gray">
							<text>计划参与人数: </text>
							<text class="padding-left-sm" v-if="item.pnum">{{item.pnum}}</text> 
							<text class="padding-left-sm" v-else>不限</text> 
						</view>
						<view class="padding-top-xl text-gray ">
							<text>当前参与人数: </text>
							<text class="padding-left-sm">{{item.num}}</text> 
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
				CustomBar: this.CustomBar,
				modalName: '',
				keys: '', //搜索关键词
				type: '',//分类
				activityList: [],  //活动
				typeList: [],
				areaValue: [],
				areaValueStr:'',
			}
		},
		mounted() {
			this.getList();
			this.getDictList();
		},
		methods: {
			clearArea(){
				this.areaValue = [];
				this.areaValueStr = '';
			},
			bindPickerChange(e){
				this.areaValue = e.detail.value;
				this.areaValueStr = e.detail.value[0]+","+e.detail.value[1]+","+e.detail.value[2];
				this.getList();
			},
			search(){
				this.getList();
			},
			getList(){
				let param = {
					state: '正常', 
					title: this.keys,
					typeName: this.type,
					areaVal: this.areaValueStr
				};
				this.$request(this.appServiceUrl+'/api/activity/list', param).then(res=>{
					this.activityList = res.data.rows;
					this.modalName = null;
				});
			},
			toDetail(id){
				uni.navigateTo({
					url: '/pages/activity/detail/detail?id='+id
				});
			},
			showModal(e) {
				if(!this.modalName){
					this.modalName = e.currentTarget.dataset.target;
				}else{
					this.modalName = null
				}
			},
			hideModal(e) {
				this.modalName = null
			},
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
			},
			typeClick(val){
				if(this.type === val){
					this.type ='';
				}else{
					this.type = val;
				}
			},
			getDictList(){
				this.$request(this.appServiceUrl+'/api/dict/data/type/type_name').then(res=>{
					this.typeList = res.data.data;
				});
			},
			MultiChange(e) {
				this.multiIndex = e.detail.value
			},
			MultiColumnChange(e) {
				const index = e.detail.column;
				const  val = e.detail.value;
				if(index === 0){ //第一列
					// 市
					this.multiArray[1] = [];
					this.multiIndex[0] = val;
					const array = new Array();
					const pcode = this.provinceList[val].code;
		            for (let i = 0; i < this.cityList.length; i++) {
						if(this.cityList[i].provinceCode === pcode){
			                array.push(this.cityList[i].name)
						}
		            }
					console.log(array);
					this.multiArray.splice(1, 1, array)
					// this.multiIndex.splice(1, 1, 0)
					// this.multiIndex.splice(2, 1, 0)
					console.log("this.multiArray = ", this.multiArray);
				}
				if(index === 1){ //第二列
					// 区
					this.multiArray[2] = [];
					this.multiIndex[1] = val;
					const pcode = this.cityList[val].code;
					const array = new Array();
		            for (let i = 0; i < this.areaList.length; i++) {
						if(pcode === this.areaList[i].cityCode){
			                array.push(this.areaList[i].name)
						}
		            }
					console.log(array);
					this.multiArray.splice(2, 1, array)
//                     this.multiIndex.splice(2, 1, 0)
					console.log("this.multiArray = ", this.multiArray);
				}
				this.$forceUpdate();
			},
		}
	}
</script>

<style>

	page {
		padding-top: 50rpx;
	}
	
	.DrawerWindow {
		/* background-color: red; */
		z-index: 999;
		position: absolute;
		width: 100%;
		/* height: 100%; */
		left: 0;
		top: 0rpx;
		transform: scale(0.9, 0.9) translateX(-100%);
		opacity: 0;
		pointer-events: none;
		transition: all 0.4s;
		padding: 100upx 0;
	}
	
	.DrawerWindow.show {
		transform: scale(1, 1) translateX(0%);
		opacity: 1;
		pointer-events: all;
	}
	
	.DrawerClose {
		position: absolute;
		width: 40vw;
		height: 100vh;
		right: 0;
		top: 0;
		color: transparent;
		padding-bottom: 30upx;
		display: flex;
		align-items: flex-end;
		justify-content: center;
		background-image: linear-gradient(90deg, rgba(0, 0, 0, 0.01), rgba(0, 0, 0, 0.6));
		letter-spacing: 5px;
		font-size: 50upx;
		opacity: 0;
		pointer-events: none;
		transition: all 0.4s;
	}
	
	.DrawerClose.show {
		opacity: 1;
		pointer-events: all;
		width: 15vw;
		color: #fff;
	}
	
	.DrawerPage .cu-bar.tabbar .action button.icon {
		width: 64upx;
		height: 64upx;
		line-height: 64upx;
		margin: 0;
		display: inline-block;
	}
	
	.DrawerPage .cu-bar.tabbar .action .cu-avatar {
		margin: 0;
	}
	
	.DrawerPage .nav {
		flex: 1;
	}
	
	.DrawerPage .nav .cu-item.cur {
		border-bottom: 0;
		position: relative;
	}
	
	.DrawerPage .nav .cu-item.cur::after {
		content: "";
		width: 10upx;
		height: 10upx;
		background-color: currentColor;
		position: absolute;
		bottom: 10upx;
		border-radius: 10upx;
		left: 0;
		right: 0;
		margin: auto;
	}
	
	.DrawerPage .cu-bar.tabbar .action {
		flex: initial;
	}
</style>
