<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>

      <el-container>
        <el-aside class="left-main-container">
          <h class="left-main-container-title">
            我的健康
          </h>
          <el-row class="left-main-container-top ">
            <el-row>
              <HealthLeftCom style="margin-left: 43px; margin-top: 52px"></HealthLeftCom>
            </el-row>
          </el-row>
          <el-row>
            <LinChart style="margin-left: 43px; margin-top: 25px"></LinChart>
          </el-row>
        </el-aside>

        <el-aside class="right-main-container">
          <h class="right-main-container-title">
            我的药物
          </h>
          <el-row class="right-main-container-top">
            <el-row>
              <HealthRightom></HealthRightom>
            </el-row>
          </el-row>
        </el-aside>

      </el-container>

    </el-container>
  </div>


</template>

<script setup lang="ts">

import TopBar from "@/components/TopBar.vue"
import LinChart from "@/components/Pagination.vue"
import HealthRightom from "@/components/HealthRightCom.vue"
import HealthLeftCom from "@/components/MyHealthLeftTop.vue"

import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

const buttons = [
  {type: '', text: '健康日志'},
  {type: '', text: '饮食管家'},
  {type: '', text: '木糖社区'},
] as const


</script>


<style>
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


/*.common-layout {*/
/*  !* position: relative; *!*/

/*  !*加了可以在缩放的时候不让页面消失*!*/
/*  width: 1440px;*/


/*   height: 1024px;*/
/*  !*height: 100vh;*!*/
/*  box-shadow: #55a684;*/
/*  !* height: 100%; *!*/
/*  !* background-color: #ffffff; *!*/
/*  !*min-height: 100vh;*!*/
/*  overflow: hidden;*/
/*  margin-left: -6%;*/
/*  !*margin-top: -2%;*!*/
/*}*/

.header {
  /*margin-left: 90px;*/
  margin-left: 157px;
  margin-top: 26px;
  width: 1125px;
  /*width: 1280px;*/
  height: 72px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}


.left-right-main-container {
  justify-content: space-between;
  width: 100%;
}

.left-main-container {
  margin-left: 103px;
  margin-top: 35px;
  width: 578px;
  height: 775px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(219, 236, 229, 1);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}



.right-main-container {
  margin-left: 78px;
  margin-top: 35px;
  width: 578px;
  height: 775px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(219, 236, 229, 1);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}

.block-col-2 .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}



.left-main-container-title {
  left: 43px;
  top: 43px;
  width: 96px;
  height: 35px;
  opacity: 1;
  /** 文本1 */
  font-size: 24px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(85, 166, 132, 1);
  text-align: left;
  vertical-align: top;
}

.right-main-container-title {
  left: 43px;
  top: 43px;
  width: 96px;
  height: 35px;
  opacity: 1;
  /** 文本1 */
  font-size: 24px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(85, 166, 132, 1);
  text-align: left;
  vertical-align: top;
}

.right-main-container-top {
  margin-left: 20px;
  margin-top: 2px;
}




</style>