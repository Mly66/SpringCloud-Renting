<template>
	<div class="login-centext">
		<div class="login-mask">
		</div>
		<div class="login">
			<div class="login-layer">
				<div class="login-logo">
					<img src="@/assets/logo.png">
				</div>
				<div class="login-from">
					<el-form label-position="top" :model="loginData" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
						<el-form-item label="管理员名称" prop="username">
						  <el-input v-model.number="loginData.username"></el-input>
						</el-form-item>
					  <el-form-item label="管理员密码" prop="password">
						<el-input type="password" v-model="loginData.password"></el-input>
					  </el-form-item>
					  <el-form-item class="footer">
						<el-button :disabled="isSubmit" style="margin-right: 50px;" type="primary" @click="submitForm('ruleForm')">登录</el-button>
						<el-button @click="resetForm('ruleForm')">重置</el-button>
					  </el-form-item>
					</el-form>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {cookieUtils} from '@/utils/cookieUtils'
	
	export default {
		name: "Login",
		data(){
		  return {
				isSubmit: false,
				loginData:{
					client_id: "root",
					client_secret: "root",
					grant_type: "password",
					redirect_uri: "http://www.baidu.com",
					username: "",
					password: "",
					domain: "r"
				},
				rules: {
					password: [
					  { required: true, message: '请输入密码', trigger: 'blur' }
					],
					username: [
					  { required: true, message: '请输入用户名', trigger: 'blur' }
					]
				}
			};
		},
		methods:{
		  submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					this.isSubmit = true;
					this.request({
						url: 'uaa/oauth/token',
						method: 'POST',
						params: this.loginData
					}).then(res => {
						if(res.access_token != undefined){
							this.$message({
							  message: '登录成功',
							  type: 'success'
							});
							cookieUtils.setCookie("token",res.access_token,2);
							this.$router.replace({
								path: '/'
							})	
						}
						this.isSubmit = false;
					}).catch(err => {
						console.log(err);
						this.isSubmit = false;
					})
				} else {
					return false;
				}
			});
		  },
		  resetForm(formName) {
			this.$refs[formName].resetFields();
		  }
		}
	}
</script>

<style>
	.login-centext{
		height: 100%;
	}
	.login-centext .login-mask{
		position: absolute;
		width: 100%;
		height: 100%;
		background: url("/public/banner.jpeg") no-repeat 0 0;
		background-size: cover;
		z-index: -2;
	}
	.login{
		color: #000;
		height: 90%;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.login-layer{
		width: 400px;
		height: auto;
	}
	.login-layer .login-logo{
		text-align: center;
	}
	.login-layer .login-logo img{
		filter: brightness(200%);
		width: 100%;
		height: 100%;
	}
	.el-form-item__label{
		color: #fff !important;
	}
	.footer{
		text-align: center;
	}
</style>