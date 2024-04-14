<template>
    <PlayGround v-if="$store.state.pk.status === 'playing'"></PlayGround>
    <MatchGround v-if="$store.state.pk.status === 'matching'"></MatchGround>
</template>

<script setup>
import PlayGround from '@/components/PlayGround.vue';
import MatchGround from '@/components/MatchGround.vue';
import { useStore } from 'vuex';
import { onMounted, onUnmounted } from 'vue';

const store = useStore();
const socketUrl = `ws://localhost:8080/websocket/${store.state.user.token}`;

let socket = null;
onMounted(() => {
    store.commit("udpateOpponent", {
        username: "我的对手",
        photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
    })
    socket = new WebSocket(socketUrl);

    // 创建连接
    socket.onopen = () => {
        console.log("connected!");
        store.commit("updateSocket", socket);
    }

    // 发送信息
    socket.onmessage = msg => {
        const data = JSON.parse(msg.data);
        console.log(data);
        if (data.event === "start-matching") {
            store.commit("udpateOpponent", {
                username: data.opponent_username,
                photo: data.opponent_photo,
            })

            setTimeout(() => {
                store.commit("updateStatus", "playing")
            }, 2000)

            store.commit("updateGameMap", data.gamemap);
        }
    }

    // 关闭连接
    socket.onclose = () => {
        console.log("disconnected!");
    }
});

onUnmounted(() => {
    socket.close();
    store.commit("updateStatus", "matching");
})
</script>

<style scoped></style>