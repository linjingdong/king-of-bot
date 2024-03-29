import { createRouter, createWebHistory } from 'vue-router'
import PkIndexViewVue from '@/views/pk/PkIndexView.vue'
import RankListIndexViewVue from '@/views/ranklist/RankListIndexView.vue'
import RecordIndexViewVue from '@/views/record/RecordIndexView.vue'
import UserBotIndexViewVue from '@/views/user/bot/UserBotIndexView.vue'
import NoFoundViewVue from '../views/errors/NoFoundView.vue'
import UserAccountLoginViewVue from '../views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterViewVue from '../views/user/account/UserAccountRegisterView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: "home",
      redirect: '/pk/'
    },
    {
      path: '/pk/',
      name: "pk-index",
      component: PkIndexViewVue
    },
    {
      path: '/ranklist/',
      name: "ranklist-index",
      component: RankListIndexViewVue
    },
    {
      path: '/record/',
      name: "record-index",
      component: RecordIndexViewVue
    },
    {
      path: '/user/bot/',
      name: "userbot-index",
      component: UserBotIndexViewVue
    },
    {
      path: '/user/account/login/',
      name: "user_account_login",
      component: UserAccountLoginViewVue
    },
    {
      path: '/user/account/register',
      name: 'user_account_register',
      component: UserAccountRegisterViewVue
    },
    {
      path: '/404/',
      name: "404",
      component: NoFoundViewVue
    },
    {
      path: '/:catchAll(.*)/',
      redirect: '/404/'
    }
  ]
})

export default router
