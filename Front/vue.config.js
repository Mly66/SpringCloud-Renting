const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
	transpileDependencies: true,
	lintOnSave:false,
	devServer: {
		port: 8081,   // 端口号  		
		proxy:{
			'/api':{
				target:'http://localhost:17000',
				changeOrigin:true,
				pathRewrite:{
					'^/api': ''
				}
			}
		}		
	}
})