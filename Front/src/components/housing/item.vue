<template>
	<div class="housing-item">
		<el-card class="box-card">
		  <div slot="header" class="clearfix">
		    <span>{{item.housingTitle}}</span>
		    <el-button @click="delItem" style="float: right; padding: 3px 0" type="text">删除</el-button>
		  </div>
		  <div class="text item">
			  <div class="item-l">
				  <div class="demo-image__lazy">
						<el-image v-if="item.imgArr != null" v-for="(url,index) in item.imgArr" :key="index" :src="imgStr(url)" lazy></el-image>
						<el-image v-if="item.imgArr == null" :src='require("@/assets/404_images/404img.png")' lazy></el-image>					
				  </div>
			  </div>
			  <div class="item-r">
				  <el-descriptions class="margin-top" :title="'房源序号：'+item.housingManagementId" :column="3" border>
				      <el-descriptions-item>
				        <template slot="label">
				          <i class="el-icon-user"></i>
				          房东序号
				        </template>
				        {{item.accountId}}
				      </el-descriptions-item>
				      <el-descriptions-item>
				        <template slot="label">
				          <i class="el-icon-school"></i>
				          城市
				        </template>
				        {{item.cityName}}
				      </el-descriptions-item>
				      <el-descriptions-item>
				        <template slot="label">
				          <i class="el-icon-s-grid"></i>
				          室
				        </template>
				        {{item.room}}
				      </el-descriptions-item>
				      <el-descriptions-item>
				        <template slot="label">
				          <i class="el-icon-coin"></i>
				          租金
				        </template>
						{{item.rent}}
				      </el-descriptions-item>
				      <el-descriptions-item>
				        <template slot="label">
				          <i class="el-icon-office-building"></i>
				          出租方式
				        </template>
				        {{item.mode}}
				      </el-descriptions-item>
					  <el-descriptions-item>
					    <template slot="label">
					      <i class="el-icon-menu"></i>
					      厅
					    </template>
					    {{item.hall}}
					  </el-descriptions-item>
					  <el-descriptions-item>
					    <template slot="label">
					      <i class="el-icon-location-outline"></i>
					      详情地址
					    </template>
					    {{item.address}}
					  </el-descriptions-item>
				    </el-descriptions>
			  </div>
		  </div>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: 'HousingItem',
		props:['item'],
		data(){
			return {
				imgerr: "",
			}
		},
		computed: {
			imgStr(){
				return function(img){
					return "http://192.168.37.129/"+img;
				}
			}
		},
		methods:{
			delItem(){
				this.$emit("getItem",this.item);
			}
		}
	}
</script>

<style lang="scss">
	.housing-item{
		margin-bottom: 20px;
	}
	.item{
		height: 200px;
		display: flex;
	}
	.item-l{			
		width: 30%;
		box-shadow: #a2a2f3 0px 0px 1px 1px;
		.demo-image__lazy{
			height: 100%;
			overflow-y: auto;
			.el-image {
			    display: block;
			    min-height: 185px;			    
			}
		}
		  /*滚动条整体样式,高宽分别对应横竖滚动条的尺寸*/
		  .demo-image__lazy::-webkit-scrollbar {
		    width: 10px;
		    height: 10px;
		    scrollbar-arrow-color:#898989;
		  }
		  /*滚动条里面小方块*/
		  .demo-image__lazy::-webkit-scrollbar-thumb {
		    border-radius: 5px;
		    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
		    background: rgba(0, 170, 255, 0.4);
		    scrollbar-arrow-color:#898989;
		  }
		  /*滚动条里面轨道*/
		  .demo-image__lazy::-webkit-scrollbar-track {
		    -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
		    border-radius: 0;
		    background: rgba(0,0,0,0.1);
		  }		
	}
	.item-r{
		margin-left: 20px;
		width: 70%;
	}
</style>