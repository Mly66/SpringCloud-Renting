<template>
	<div class="dashboard">
		<el-container style="height: 100%; border: 1px solid #eee">
		  <el-aside :router="true" width="200px" style="background-color: rgb(234, 237, 241)">
			<el-menu
			  :default-active="defaultActive">
			  <el-submenu :index="''+(i+1)" v-for="(item,i) in roterData" :key="i">
				<template slot="title">		
				  <span>{{item.title}}</span>
				</template>
				<el-menu-item-group>
				  <el-menu-item @click="goRouter(router)" :index="(i+1)+'-'+j" v-for="(router,j) in item.roterchd" :key="j">{{router.title}}</el-menu-item>
				</el-menu-item-group>
			  </el-submenu>	  
			</el-menu>
		  </el-aside>
		  
		  <el-container>
		    <el-header>
				<el-row>
				  <el-col :span="8"><div class="grid-content bg-purple" style="font-weight: bold;">{{routerName}}</div></el-col>
				  <el-col :span="8"><div class="grid-content bg-purple-light" style="text-align: center;">天上人间</div></el-col>
				  <el-col :span="8"><div class="grid-content bg-purple" style="text-align: right;"><span style="margin-right: 20px;">管理员: root</span><el-button type="warning" @click="goLogin">退出登录页</el-button></div></el-col>
				</el-row>		      
		    </el-header>
			
		    <el-main>
				<router-view />
		    </el-main>
		  </el-container>
		</el-container>
	</div>
</template>

<script>
	export default {
		name: "Dashboard",
		data() {
			return {	
				routerName: '',
				defaultActive: '',				
				roterData:[
					{title: '用户管理',roterchd:[{title: '权限管理',url: "power"},{title: '账户状态',url: "state"}]},
					{title: '房源管理',roterchd:[{title: '房源信息',url: "control"}]},
				]
			}
		},
		methods:{
			goRouter(router){				
				if(router.title === this.routerName){
					return;
				}
				this.routerName = router.title;
				this.$router.replace({
				    path: '/'+router.url
				})			
			},
			goLogin(){
				this.$router.replace({
				    path: '/login'
				})
			}
		},
		created(){
			this.routerName = this.$route.meta.title;
			this.defaultActive = this.$route.meta.defaultActive;
		},
		components:{}
	}
</script>

<style lang="scss">
	.el-menu-item-group__title{
		display: none;
	}
	.dashboard{
		height: 100%;
	}
	.el-header {
		background-color: rgb(234, 237, 241);
		color: #333;
		line-height: 60px;
	}

	.el-aside {
		color: #333;
	}
</style>