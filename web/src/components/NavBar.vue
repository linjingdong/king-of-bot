<template>
    <nav class="navbar navbar-expand-lg bg-dark" data-bs-theme="dark">
        <div class="container">
            <RouterLink class="navbar-brand" :to="{ name: 'pk-index' }">King Of Bots</RouterLink>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <RouterLink :class="route_name == 'pk-index' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'pk-index' }">对战</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink :class="route_name == 'record-index' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'record-index' }">对战列表</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink :class="route_name == 'ranklist-index' ? 'nav-link active' : 'nav-link'"
                            :to="{ name: 'ranklist-index' }">排行榜</RouterLink>
                    </li>
                </ul>
                <ul class="navbar-nav " v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="true">
                            {{ store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <RouterLink class="dropdown-item" :to="{ name: 'userbot-index' }">我的bot</RouterLink>
                            </li>

                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav " v-else-if="$store.state.user.pulling_info">
                    <li class="nav-item">
                        <RouterLink class="nav-link" role="button" :to="{ name: 'user_account_login' }">
                            登录
                        </RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink class="nav-link" role="button" :to="{ name: 'user_account_register' }">
                            注册
                        </RouterLink>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script setup>
import { RouterLink, useRoute } from 'vue-router';
import { computed } from 'vue';
import { useStore } from 'vuex';

const route = useRoute();
const store = useStore();
let route_name = computed(() => route.name);

const logout = () => {
    store.dispatch("logout");
}
</script>

<style scoped></style>