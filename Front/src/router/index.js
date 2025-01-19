import Vue from 'vue'
import VueRouter from 'vue-router'

export const constantRoutes = [
	{
		name: 'index',
		path: '/',
		component: ()=>import('@/views/dashboard/index'),
		hidden: true,
		redirect: '/power',
		children: [
			{
				name: 'power',
				path: 'power',
				meta:{title: '权限管理',defaultActive: '1-0'},
				component: ()=>import('@/views/account/power'),
				hidden: true,
			},
			{
				name: 'state',
				path: 'state',
				meta:{title: '账户状态',defaultActive: '1-1'},				
				component: ()=>import('@/views/account/state'),
				hidden: true,
			},
			{
				name: 'control',
				path: 'control',
				meta:{title: '房源信息',defaultActive: '2-0'},				
				component: ()=>import('@/views/housing/control'),
				hidden: true,
			},
		]
	},
	{
		path: '/404',
		component: () => import('@/views/404'),
		hidden: true
	},
	{
		path: '/login',
		component: ()=>import('@/views/login/index'),
		hidden: true
	},
	{ path: '*', redirect: '/404', hidden: true }
]

//调用 Vue.use()函数，把VueRouter 安装为Vue的插件
const router = new VueRouter({
	mode: 'history',
	routes: constantRoutes
})

//暴露router
export default router