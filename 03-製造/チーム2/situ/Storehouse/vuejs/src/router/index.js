import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: { name: 'home' }
  },
  {
    path: '/home',
    name: 'home',
    component: () => import(/* webpackChunkName: 'home' */ '../views/HomeView')
    // component: HomeView,
  },
  {
    path: '/home/addgoods',
    name: 'addgoods',
    // route level code-splitting
    // this generates a separate chunk (addgoods.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: 'add' */ '../views/AddGoodsView')
  },
  {
    path: '/home/ioinfo',
    name: 'ioinfo',
    // 下面2行代码，指定了相同的webpackChunkName，会合并打包成一个js文件。  
    component: () => import(/* webpackChunkName: 'io' */'../views/IOInfoView')
  },
  {
    path: '/home/ioinfo/addio',
    name: 'addio',
    component: () => import(/* webpackChunkName: 'io' */'../views/AddIOView')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: 'login' */'../views/LoginView')
  },
  {
    path: '/home/addunit',
    name: 'addunit',
    component: () => import(/* webpackChunkName: 'addunit' */'../views/AddUnitView')
    // component: AddUnitViewVue
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
