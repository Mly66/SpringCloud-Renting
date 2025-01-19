<template>
	<div>
		<div class="search">
			<el-input v-model="inputName" placeholder="请输入用户名"></el-input>
			<el-button @click="search" type="primary" icon="el-icon-search">搜索</el-button>
		</div>
		<div class="state-data">
			<table-data  @getTableItem='getTableItem' :tableData='tableData' :optionData='optionData' title='状态'></table-data>
		</div>
		<page ref="page" @getPage='getPage' :pageIndex='pageIndex' :index='pageI'></page>
	</div>
</template>

<script>
	import page from "@/components/page/index"
	import search from "@/components/search/index"
	import tableData from "@/components/table/index"
	
	export default {
		name: "State",
		components: {page,search,tableData},
		data(){
			return {
				pageI: 1,
				tableData: [],
				inputName: '',
				searchName: '',
				pageIndex: -1,
				pageRows: 10,	//页数
				optionData:[
					{id: 1,title: "正常"},
					{id: 0,title: "冻结"},					
				]				
			}
		},
		methods: {
			search(){
				this.$refs.page.pageI = 1; //修改子组件当前页
				this.searchName = this.inputName;
				this.getAccountState(1,this.pageRows,this.searchName);
				this.getAccountStatePage(this.searchName);
			},
			getAccountState(page,rows,username){
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
						data.select_3_id = res.data[i].state;
						data.select_3 = res.data[i].state===1?'正常':'冻结';
						this.tableData.push(data);
					}
				}).catch(err => {
					console.log(err);
				})
			},
			getPage(index){
				this.getAccountState(index,this.pageRows,this.searchName);
			},
			getTableItem(item){
				this.request({
					url: '/account/m/reviseAccountState/'+item.id+'/'+item.select_3_id,
					method: 'GET'
				}).then(res => {					
					this.$message({
					  message: '修改成功',
					  type: 'success'
					});
				}).catch(err => {
					console.log(err);
				})
			},
			getAccountStatePage(username){
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
		},
		created(){
			this.getAccountState(1,this.pageRows,this.searchName);
			this.getAccountStatePage(this.searchName);
		}
	}
</script>

<style lang="scss">
	.search{
		display: flex;
		margin-bottom: 20px;
		box-shadow: #00aaff 0px 0px 7px px;		
	}
</style>