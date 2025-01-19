<template>
	<div class="power">
		<div class="search">
			<el-input v-model="inputName" placeholder="请输入用户名"></el-input>
			<el-button @click="search" type="primary" icon="el-icon-search">搜索</el-button>
		</div>
		<div class="power-data">
			<table-data @getTableItem='getTableItem' :tableData='tableData' :optionData='optionData' title='权限'></table-data>
		</div>
		<page ref="page" @getPage='getPage' :pageIndex='pageIndex' :index='pageI'></page>
	</div>
</template>

<script>
	import page from "@/components/page/index"
	import search from "@/components/search/index"
	import tableData from "@/components/table/index"
	
	export default {
		name: "Power",
		data(){
			return {
				pageI: 1,
				isShow: false,
				inputName: '',
				searchName: '',
				tableData: [],
				pageIndex: -1,
				pageRows: 10,	//页数
				optionData:[
					{id: 1002,title: "商家"},
					{id: 1003,title: "用户"},
				]
			}
		},
		methods: {
			search(){
				this.$refs.page.pageI = 1; //修改子组件当前页
				this.searchName = this.inputName;
				this.getAccountPower(1,this.pageRows,this.searchName);
				this.getAccountPowerPage(this.searchName);
			},
			// /m/getAccounts
			getAccountPower(page,rows,username){
				if(username.trim() === ''){
					username = "null";
				}
				this.request({
					url: '/account/m/getAccounts/'+page+'/'+rows,
					method: 'POST',
					params: {
						username: username
					}
				}).then(res => {
					this.tableData = [];						
					for(let i=0;i<res.data.length;i++){
						let data = res.data[i];
						data.isUpdata = true;
						data.select_3_id = res.data[i].powerId;
						data.select_3 = res.data[i].powerName;
						this.tableData.push(data);
					}
				}).catch(err => {
					console.log(err);
				})
			},
			getAccountPowerPage(username){
				this.request({
					url: '/account/m/getAccountCounts',
					method: 'POST',
					params: {
						username: username
					}
				}).then(res => {
					this.pageIndex = (res.data / this.pageRows) * 10;
				}).catch(err => {
					console.log(err);
				})
			},
			getPage(index){
				this.getAccountPower(index,this.pageRows,this.searchName);
			},
			getTableItem(item){	
				this.request({
					url: '/account/m/reviseByIdPower/'+item.id+'/'+item.select_3_id,
					method: 'GET'
				}).then(res => {					
					this.$message({
					  message: '修改成功',
					  type: 'success'
					});
				}).catch(err => {
					console.log(err);
				})
			}
		},
		destroyed(){
		},		
		created() {
			this.getAccountPower(1,this.pageRows,this.searchName);
			this.getAccountPowerPage(this.searchName);
		},
		components: {page,search,tableData}
	}
</script>

<style lang="scss">
	.search{
		display: flex;
		margin-bottom: 20px;
		box-shadow: #00aaff 0px 0px 7px px;		
	}
</style>