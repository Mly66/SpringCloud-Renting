<template>
	<div class="search">
		<el-input :placeholder="title"></el-input>
		<el-button type="primary" icon="el-icon-search">搜索</el-button>
	</div>
</template>

<script>
	export default {
		name: 'Search',
		props: ['title']
	}
</script>

<style lang="scss">
	.search{
		display: flex;
		margin-bottom: 20px;
		box-shadow: #00aaff 0px 0px 7px px;		
	}
</style>