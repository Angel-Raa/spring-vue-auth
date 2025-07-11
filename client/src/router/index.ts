import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/view/home/HomeView.vue'),
  },
  {
    path: '/:catchAll(.*)',
    name: 'NotFound',
    component: () => import('@/view/not-found/NotFoundView.vue'),
  },
  {
    path: '/book',
    name: 'Book',
    component: () => import('@/view/book/BookView.vue'),
  },

  {
    path: '/book/:slug',
    name: 'BookDetails',
    component: () => import('@/view/book/BookDetailsView.vue'),
  },
  {
    path: '/auth/login',
    name: 'Login',
    component: () => import('@/view/auth/LoginView.vue'),
  },
]
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
