<template>
    <div class="playground">
        <div class="row">
            <div class="col-6">
                <div class="user-photo">
                    <img :src="$store.state.user.photo" alt="">
                </div>
                <div class="user-username">
                    {{ $store.state.user.username }}
                </div>
            </div>
            <div class="col-6">
                <div class="user-photo">
                    <img :src="$store.state.pk.opponent_photo" alt="">
                </div>
                <div class="user-username">
                    {{ $store.state.pk.opponent_username }}
                </div>
            </div>
            <div class="col-12 btn-matching">
                <button @click="click_match_btn" type="button" class="btn btn-warning btn-lg">{{ match_btn_info }}</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useStore } from 'vuex';
import { ref } from 'vue';

const store = useStore();
let match_btn_info = ref("开始匹配");

const click_match_btn = () => {
    if (match_btn_info.value === "开始匹配") {
        match_btn_info.value = "取消";
        store.state.pk.socket.send(JSON.stringify({
            event: "start-matching",
        }))
    } else {
        match_btn_info.value = "开始匹配";
        store.state.pk.socket.send(JSON.stringify({
            event: "stop-matching",
        }))
    }
}

</script>

<style scoped>
div.playground {
    height: 70vh;
    width: 60vw;
    margin: 40px auto;
    background-color: rgba(50, 50, 50, 0.5);
}

div.user-photo {
    text-align: center;
    padding-top: 10vh;
}

div.user-photo>img {
    border-radius: 50%;
    width: 20vh;
}

div.user-username {
    text-align: center;
    font-size: 24px;
    font-size: 600;
    color: white;
    padding-top: 2vh;
}

div.btn-matching {
    text-align: center;
    padding-top: 15vh;
}
</style>