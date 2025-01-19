<template>
	<div class="control">
		<div class="search">
			<el-input v-model="inputName" placeholder="请输入用户名"></el-input>
			<el-button @click="search" type="primary" icon="el-icon-search">搜索</el-button>
		</div>
		<div class="control-data">
			<housing-item @getItem='getItem' v-for="(item,index) in housingData" :item='item'></housing-item>
		</div>
		<page ref="page" @getPage='getPage' :pageIndex='pageIndex' :index='pageI'></page>
	</div>
</template>

<script>
	import page from "@/components/page/index"
	import search from "@/components/search/index"
	import housingItem from "@/components/housing/item"
	
	export default {
		name: "Power",
		components: {page,search,housingItem},
		data(){
			return {				
				pageI: 1,
				pageIndex: -1,
				pageRows: 6,	//页数
				inputName: '',
				searchName: '',
				housingData: []
			}
		},
		methods:{
			search(){
				this.$refs.page.pageI = 1; //修改子组件当前页
				this.searchName = this.inputName;
				this.getHousingData(this.searchName,1,this.pageRows);
				this.getHousingPage(this.searchName);
			},
			getHousingData(accountId,page,rows){
				if(accountId.trim() === ''){
					accountId = -1;
				}
				this.request({
					url: '/housing/m/getVgUidHousing/'+page+'/'+rows,
					method: 'POST',
					params: {
						accountId: accountId
					}
				}).then(res => {
					this.housingData = [];				
					for(let i=0;i<res.data.length;i++){
						let obj = res.data[i];
						if(res.data[i].housingFileDTO.fileName != null){
							let fileName = res.data[i].housingFileDTO.fileName;
							let groupName = res.data[i].housingFileDTO.groupName;
							let imgArr = this.formtHousingImg(fileName.split(","),groupName.split(","));												
							obj.imgArr = imgArr;
						}else{
							obj.imgArr = null;
						}
						this.housingData.push(res.data[i]);						
					}
				}).catch(err => {
					console.log(err);
				})
			},
			formtHousingImg(fileNames,groupName){
				let imgArr = [];
				for(let i = 0;i<fileNames.length;i++){
					let temp = groupName[i]+'/'+fileNames[i];
					imgArr.push(temp);
				}
				return imgArr;
			},
			getPage(index){
				this.getHousingData(this.searchName,index,this.pageRows);
			},
			getHousingPage(accountId){
				if(accountId.trim() === ''){
					accountId = -1;
				}
				this.request({
					url: '/housing/m/getVgUidHousingCounts/'+accountId,
					method: 'GET'
				}).then(res => {					
					this.pageIndex = (res.data / this.pageRows) * 10;
				}).catch(err => {
					console.log(err);
				})
			},
			getItem(item){
				let housingFileDTO = item.housingFileDTO;
				this.$confirm('此操作将永久房屋信息, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.request({
						url: 'housing/bus/delUserManagement/'+item.housingManagementId+'/'+item.accountId,								
						method: 'POST',
						data: housingFileDTO
						// params: housingFileDTO
					}).then(res => {					
						this.$message({
							type: 'success',
							message: '删除成功!'
						});
						this.$refs.page.pageI = 1; //修改子组件当前页
						this.getHousingData(this.searchName,1,this.pageRows);
						this.getHousingPage(this.searchName);
					}).catch(err => {
						console.log(err);
					})

				}).catch(() => {    
				});
			}
		},
		created() {
			this.getHousingData(this.searchName,1,this.pageRows);
			this.getHousingPage(this.searchName);
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