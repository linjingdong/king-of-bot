<template>
    <ContentField>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="Register">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">确认密码</label>
                        <input v-model="confirmedPassword" type="Password" class="form-control" id="confirmedPassword"
                            placeholder="请确认密码">
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
import { ref } from 'vue'
import { useStore } from 'vuex';
import { register } from '../../../api/user/account/Register'
import router from '@/router';

const store = useStore();
let username = ref('');
let password = ref('');
let confirmedPassword = ref('');
let error_message = ref('');

const Register = async () => {
    await register({
        username: username.value,
        password: password.value,
        confirmedPassword: confirmedPassword.value
    }).then(resp => {
        if (resp.data.error_message !== "success") {
            error_message.value = resp.data.error_message;
        } else {
            router.push({ name: "user_account_login" })
        }
    }).catch(err => {
        console.log(err.data)
    });
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
</style>@/router/router@/router/router