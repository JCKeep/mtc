<template>
  <div class="a">
    <div class="pagination-block">
      <div class="pagination" style="z-index: 1">
        <p v-if="user.flag === false" class="data-type" style="float: left">血糖数据</p>
        <p v-if="user.flag === true" class="data-type" style="float: left">体重数据</p>
        <el-pagination  layout="prev, next" :total="20"   @prev-click="prevclick" @next-click="nextclick" />
      </div>
    </div>
    <div >
      <BarChart v-if="user.flag === true"></BarChart>
      <LineChart v-if="user.flag === false"></LineChart>
    </div>
  </div>

</template>

<script lang="ts" setup>
  import { ref, reactive } from 'vue'
  // import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
  import type { TabsPaneContext } from 'element-plus'
  import BarChart from '@/components/BarChart.vue'
  import LineChart from '@/components/LineChart.vue'
  var user = reactive({
    flag: false
  })
  function prevclick() {
    user.flag = false
  }
  function nextclick() {
    user.flag = true
  }
</script>

<style scoped>
.a {
  width: 496px;
  height: 282px;
  opacity: 1;
  border-radius: 20px;
  background:white;
  /*box-shadow: 13.33px 13.33px 13.33px 13.33px rgba(0, 0, 0, 0.25);*/
}
.data-type {
  left: 30px;
  top: 40px;
  width: 95px;
  height: 30px;
  opacity: 1;
  /** 文本1 **/
  font-size: 23.74px;
  font-weight: 400;
  letter-spacing: -1.19px;
  line-height: 0px;
  color: rgba(29, 54, 80, 1);
  text-align: left;
  vertical-align: top;
}
.el-pagination {
  width: 280px;
  height: 30px;
  top: 27px;
  left: 40px;
}



</style>