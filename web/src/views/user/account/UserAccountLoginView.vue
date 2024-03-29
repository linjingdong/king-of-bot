<template>
    <ContentField>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error_message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">提交</button>
                </form>
            </div>
        </div>
    </ContentField>
</template>

<script setup>
import ContentField from '@/components/ContentField.vue';
import { useStore } from 'vuex'
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const store = useStore();
const router = useRouter();

let username = ref('')
let password = ref('')
let error_message = ref('')

const login = () => {
    error_message.value = "";
    store.dispatch("login", {
        userInfo: {
            username: username.value,
            password: password.value
        }
    })
        .then(async () => {
            await store.dispatch("getInfo")
                .then(() => {
                    console.log(store.state.user)
                    router.push({ name: "home" });
                })
                .catch(() => {
                    console.log("error");
                })

        })
        .catch(() => {
            error_message.value = "用户名或密码错误";
        })
}
</script>

<style scoped>
.btn {
    width: 100%;
}

div.error_message {
    color: red;
    padding-bottom: 20px;
}
</style>