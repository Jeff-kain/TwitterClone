<style>
  body,
  html {
    overflow-y: scroll;
    background-color:  #e6e9ec;
  }
  .bg {
background-color:  #e6e9ec;
  }
</style>
<template>
  <div class="bg" id="app">
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: 'app',
};
</script>