<template>
  <h1>bot名称:{{ botinfo.name }}</h1>
  <h1>bot战力:{{ botinfo.rating }}</h1>
  <RouterView></RouterView>
</template>

<script setup>
import { RouterView } from 'vue-router';
import { ref, onMounted } from 'vue';
import { getBotInfo} from './api/botinfoApi';

//定义假数据
let botinfo = ref({
  name:'',
  rating:''
})

//展示数据
let showdata = () => {
  getBotInfo().then(
    response=>{
      botinfo.value=response.data;
      console.log(botinfo);
      console.log(botinfo.value)
    }
  )
}

onMounted(()=>{
  showdata()
})
</script>

<style scoped>
</style>
