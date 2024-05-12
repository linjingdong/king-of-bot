import { createRouter, createWebHistory } from 'vue-router'
import PkIndexViewVue from '@/views/pk/PkIndexView.vue'
import RankListIndexViewVue from '@/views/ranklist/RankListIndexView.vue'
import RecordIndexViewVue from '@/views/record/RecordIndexView.vue'
import RecordContentView from '../views/record/RecordContentView.vue'
import UserBotIndexViewVue from '@/views/user/bot/UserBotIndexView.vue'
import NoFoundViewVue from '../views/errors/NoFoundView.vue'
import UserAccountLoginViewVue from '../views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterViewVue from '../views/user/account/UserAccountRegisterView.vue'
import store from '@/store'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: "home",
      redirect: '/pk/',
      meta: {
        requestAuth: true,
      }
    },
    {
      path: '/pk/',
      name: "pk-index",
      component: PkIndexViewVue,
      meta: {
        requestAuth: true,
      }
    },
    {
      path: '/ranklist/',
      name: "ranklist-index",
      component: RankListIndexViewVue,
      meta: {
        requestAuth: true,
      }
    },
    {
      path: '/record/',
      name: "record-index",
      component: RecordIndexViewVue,
      meta: {
        requestAuth: true,
      }
    },
    {
      path: '/record/:recordId/',
      name: "record-content",
      component: RecordContentView,
      meta: {
        requestAuth: true,
      }
    },
    {
      path: '/user/bot/',
      name: "userbot-index",
      component: UserBotIndexViewVue,
      meta: {
        requestAuth: true,
      }
    },
    {
      path: '/user/account/login/',
      name: "user_account_login",
      component: UserAccountLoginViewVue,
      meta: {
        requestAuth: false,
      }
    },
    {
      path: '/user/account/register',
      name: 'user_account_register',
      component: UserAccountRegisterViewVue,
      meta: {
        requestAuth: false,
      }
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

router.beforeEach((to, from, next) => {
  let jwt_token = localStorage.getItem("jwt_token");
  if (jwt_token && !store.state.user.is_login) {
    store.commit("updateToken", jwt_token);
    store.commit("updatePullingInfo", false);
    store.dispatch("getInfo")
      .then(() => {
        console.log(store.state.user);
        next();
      })
      .catch(() => {
        store.dispatch("logout");
        alert("登录授权不合法，请重新登录");
        next({ name: "user_account_login" })
      })
  } else {
    if (to.meta.requestAuth && !store.state.user.is_login)
      next({ name: "user_account_login" });
    else
      next();
  }
})

export default router
