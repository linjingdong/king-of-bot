<template>
  <NavBar />
  <RouterView></RouterView>
</template>

<script setup>
import { RouterView } from 'vue-router';
import NavBar from './components/NavBar.vue';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
</script>

<style>
body {
  background-image: url("./asssets/images/background.png");
  background-size: cover;
}
</style>
