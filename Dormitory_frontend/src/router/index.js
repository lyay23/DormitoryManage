import Layout from '../layout/Layout.vue'
import {createRouter, createWebHistory} from "vue-router";


export const constantRoutes = [
    {path: '/Login', name: 'Login', component: () => import("@/views/Login")},
    {path: '/register', name: 'Register', component: () => import("@/views/Register.vue")},
    {
        path: '/Layout', name: 'Layout', component: Layout, children: [
            //
            {path: '/home', name: 'Home', component: () => import("@/views/Home")},
            {path: '/stuInfo', name: 'StuInfo', component: () => import("@/views/StuInfo")},
            {path: '/dormManagerInfo', name: 'DormManagerInfo', component: () => import("@/views/DormManagerInfo")},
            {path: '/buildingInfo', name: 'BuildingInfo', component: () => import("@/views/BuildingInfo")},
            {path: '/roomInfo', name: 'RoomInfo', component: () => import("@/views/RoomInfo")},
            {path: '/noticeInfo', name: 'NoticeInfo', component: () => import("@/views/NoticeInfo")},
            {path: '/adjustRoomInfo', name: 'AdjustRoomInfo', component: () => import("@/views/AdjustRoomInfo")},
            {path: '/repairInfo', name: 'RepairInfo', component: () => import("@/views/RepairInfo")},
            {path: '/visitorInfo', name: 'VisitorInfo', component: () => import("@/views/VisitorInfo")},
            //
            {path: '/myRoomInfo', name: 'MyRoomInfo', component: () => import("@/views/MyRoomInfo")},
            {path: '/applyRepairInfo', name: 'ApplyRepairInfo', component: () => import("@/views/ApplyRepairInfo")},
            {path: '/applyChangeRoom', name: 'ApplyChangeRoom', component: () => import("@/views/ApplyChangeRoom")},

            {path: '/selfInfo', name: 'SelfInfo', component: () => import("@/views/SelfInfo")},
        ]
    },

]
const router = createRouter({
    routes: constantRoutes,
    history: createWebHistory(process.env.BASE_URL)
})
//路由守卫
router.beforeEach((to, from, next) => {
    //to 要访问的路径
    //from 代表从哪个路径跳转而来
    // next 是函数，表示放行
    // next() 放行
    // next('/*') 强制跳转
    const user = window.sessionStorage.getItem('user')
    // 如果目标是登录页或注册页，则直接放行
    if (to.path === '/Login' || to.path === '/register') {
        return next();
    }
    // 如果用户未登录，且目标不是登录页或注册页，则跳转到登录页
    if (!user) {
        return next('/Login')
    }
    // 如果用户已登录，且访问根路径，则跳转到首页
    if (to.path === '/' && user) {
        return next('/home')
    }
    next()
})

export default router
