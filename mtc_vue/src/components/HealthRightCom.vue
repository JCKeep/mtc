<template>
    <div class="head">
        <Date></Date>
        <Weather style="margin-left: 132px; margin-top: -114px"></Weather>
    </div>
    <div class="slid">
        <Mydrug style="margin-left: 17px; padding-top: 6px;"></Mydrug>
    </div>
  
</template>
  
<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import Date from '../components/Date.vue'
import Weather from '../components/Weather.vue'
import Mydrug from '../components/MyDrug.vue';

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
.head {
    margin-left: 21px;
    margin-top: 53px;
    width: 496px;
    height: 150px;
    opacity: 1;
    border-radius: 15px;
    background: rgba(255, 255, 255, 1);
    /*box-shadow: 13.33px 13.33px 13.33px 13.33px rgba(0, 0, 0, 0.25);*/
}
.slid {
    z-index: 1; 
    margin-left: 21px;
    margin-top: 20px;
    width: 496px;
    height: 497px;
    opacity: 1;
    border-radius: 15px;
    background: rgba(255, 255, 255, 1);
    /*box-shadow: 13.33px 13.33px 13.33px 13.33px rgba(0, 0, 0, 0.25);*/
}
</style>