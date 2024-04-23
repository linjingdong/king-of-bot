<template>
    <div ref="parent" class="gamemap">
        <canvas ref="canvas" tabindex="0"></canvas>
    </div>
</template>

<script setup>
import { GameMap } from '../asssets/scripts/GameMap';
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';

const store = useStore();
let parent = ref(null);
let canvas = ref(null);

onMounted(() => {
    store.commit(
        "udpateGameObject",
        new GameMap(canvas.value.getContext('2d'), parent.value, store)
        );
});

</script>

<style scoped>
div.gamemap {
    height: 100%;
    width: 100%;
    display: flex;
    justify-content: center;
    align-content: center;
}
</style>