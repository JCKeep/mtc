<template>
  <el-container class="common-layout">
    <img class="not-found" src="../assets/404.svg" alt="" />
  </el-container>
</template>
<script>
export default {
  name: "404",
  components: {},
};
</script>
<style scoped>
.not-found {
  margin-left: 32%;
}
.not-found img {
  width: 100%;
  height: 100%;
}

.common-layout {
  position: relative;
  width: 1440px;
  /*height: 1024px;*/
  box-shadow: #55a684;
  /*height: 100%;*/
  background-color: #ffffff;
  min-height: 100vh;
  overflow: hidden;
  /*left: 5%;*/
}
</style>>