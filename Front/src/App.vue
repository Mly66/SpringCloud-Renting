<template>
  <div id="app">
	<router-view/>
  </div>
</template>

<script>
	export default {
	  name: 'App',
	  created(){
	  }
	}
</script>

<style>
	body,html,#app{
		width: 100%;
		height: 100%;
		padding: 0;
		margin: 0;
	}
	a{
		text-decoration: none;
	}
</style>
