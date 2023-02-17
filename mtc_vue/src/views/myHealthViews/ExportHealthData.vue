<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>


      <el-container>

        <div class="record-box">
          <el-row class="data-show-farm">
            <ExportDataFarm ></ExportDataFarm>
          </el-row>
          <el-row class="record-button">
              <el-col :span="5" >
                <el-button @click="submit" auto-insert-space class="record-submit">导出</el-button>
              </el-col>
              <el-col :span="1" >
                <el-button @click="returnHealth" auto-insert-space class="record-cancel">返回</el-button>
              </el-col>
          </el-row>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>

import { reactive, ref } from 'vue'
import TopBar from "@/components/TopBar.vue"
import ExportDataFarm from "@/components/ExportDataFarm.vue"
import axios from "axios";
// import router from "@/router";
import {ElMessage} from "element-plus";
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()


const formLabelAlign = reactive({
  email:sessionStorage.getItem("email"),
  start:'',
  end: '',
})
//获取当前时间
// day为number，getDay(-1):昨天的日期;
// getDay(0):今天的日期;getDay(1):明天的日期;【以此类推】
function doHandleMonth(month) {
  var m = month;
  if (month.toString().length == 1) {
    m = "0" + month;
  }
  return m;
}


function getDay(day) {
  var today = new Date();
  var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
  today.setTime(targetday_milliseconds); //注意，这行是关键代码

  var tYear = today.getFullYear();
  var tMonth = today.getMonth();
  var tDate = today.getDate();
  tMonth = doHandleMonth(tMonth + 1);
  tDate = doHandleMonth(tDate);
  // alert(tYear + "-" + tMonth + "-" + tDate)
  return tYear + "-" + tMonth + "-" + tDate;
}

function submit() {
  formLabelAlign.start = getDay(-6)
  formLabelAlign.end = getDay(0)
  console.log(formLabelAlign.email)
  console.log(formLabelAlign.start)
  console.log(formLabelAlign.end)
  axios.post("/mtc/health/export", { email: formLabelAlign.email,
      start: formLabelAlign.start, end: formLabelAlign.end })
      .then((Response) => {
        if (Response.data.code === 200) {
          // alert("获取成功")
          // const tempPath = Response.data.object;
          ElMessage.success("导出成功")
          window.location.href = Response.data.object;
          // router.push({path: Response.data.object})
          // console.log(Response.data.object)
        } else {
          ElMessage.error("导出失败，请检查网络环境")
          console.log(Response.data.code)

          // router.push({ path: '/user' })
        }
      })
}

function returnHealth() {
  router.push({ path: '/MyHealth' })
}

</script>

<style  scoped>

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

.header {
  margin-left: 157px;
  margin-top: 26px;
  width: 1125px;
  height: 72px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}


.record-box{
  margin-left: 156px;
  margin-top: 50px;
  width: 1127px;
  height: 800px;
  opacity: 1;
  border-radius: 30px;
  background: rgba(255, 255, 255, 1);
  border: 1px solid rgba(8, 155, 171, 1);
}


.record-cancel{
  margin-left: 0px;
  margin-top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgba(219, 236, 229, 1);
  color: #414841;
}

.record-submit{
  margin-left: 0px;
  margin-top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}
.data-show-farm{
  margin-left: 52.5px;
  margin-top: 70px;
}
.record-button{
  margin-left: 430px;
  margin-top: 49px;
}

.record-cancel{
  left: 0px;
  top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgb(255, 255, 255);
  color: #414841;
}

.record-submit{
  left: 0px;
  top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}
</style>