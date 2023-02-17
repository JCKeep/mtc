<template>
<scale-box :width="1920" :height="1080">
  <div class="test">
    <div class="box">
      <router-view />
    </div>
  </div>
</scale-box>


</template>

<script lang="ts" setup>
import ScaleBox from 'scale-box'
import 'scale-box/dist/style.css'
</script>

<style>

#app {
  max-width: 1440px;
  margin: 0 auto;
  padding: 0rem;
  font-weight: normal;
  background: rgb(248, 6, 6);
}
.test {
  padding-left: 240px;
  padding-right: 240px;
  margin-top: 3%;
}
</style>
