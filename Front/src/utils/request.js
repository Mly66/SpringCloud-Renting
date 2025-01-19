import axios from "axios"
import {cookieUtils} from '@/utils/cookieUtils'
import { Message } from 'element-ui'
import router from "@/router";

// 创建一个错误
function errorCreate (msg) {
  const error = new Error(msg)
  errorLog(error)
  throw error
}

// 记录和显示错误
function errorLog (error) {
  // 打印到控制台
  if (process.env.NODE_ENV === 'development') {
    console.log(error)
  }
  // 显示提示
  Message({
    message: error.message,
    type: 'error',
    duration: 5 * 1000
  })
}

// 创建一个 axios 实例
const service = axios.create({
	baseURL: '/api',
	timeout: 5000 // 请求超时时间
});

// 请求拦截
service.interceptors.request.use(
	config => {
		if (config.url === '/uaa/oauth/token') {
			config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
			return config;
		}
		const token = cookieUtils.getCookie("token")
		if(token){
			config.headers['Authorization'] = `Bearer ` + token
		}
		return config;
	},
	error => {
		// 发送失败
		return Promise.reject(error)
	}	
)

// 响应拦截器
service.interceptors.response.use(
	response => {
		const dataAxios = response.data	
		const code = dataAxios.code
		if(code === 200){
			// router.replace({
			// 	path: '/control'
			// })	
			return dataAxios;
		}else{
			console.log(dataAxios.message);
		}
		return dataAxios;
	},
	  error => {
		if(error.response.config.url === "uaa/oauth/token"){		
			error.message = error.response.data.error_description;
			errorLog(error);
			return Promise.reject(error)
		}
	    if (error && error.response) {
	      switch (error.response.status) {
	        case 400: error.message = '请求错误'; break
	        case 401: error.message = '未授权，请登录'; break
	        case 403: error.message = '拒绝访问'; break
	        case 404: error.message = `请求地址出错: ${error.response.config.url}`; break
	        case 408: error.message = '请求超时'; break
	        case 500: error.message = '服务器内部错误'; break
	        case 501: error.message = '服务未实现'; break
	        case 502: error.message = '网关错误'; break
	        case 503: error.message = '服务不可用'; break
	        case 504: error.message = '网关超时'; break
	        case 505: error.message = 'HTTP版本不受支持'; break
	        default: break
	      }
	    }
	    errorLog(error)
	    return Promise.reject(error)
	  }
)

export default service;