<template>
	<div class="page">
		<div class="block">
				<el-pagination
				background
				layout="prev, pager, next"				
				@current-change="handleCurrentChange"
				:current-page.sync="pageI"
				:total="pageIndex">
			</el-pagination>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'Page',
		props:['pageIndex',"index"],
		data(){
			return {
				pageI: this.index
			}
		},
		methods:{
			handleCurrentChange(val) {
				this.pageI = parseInt(val);
				this.$emit("getPage",parseInt(val));
			}
		}
	}
</script>

<style lang="scss">
	.page{
		margin-top: 20px;
		display: flex;
		justify-content: center;
		align-items: center;
	}
</style>