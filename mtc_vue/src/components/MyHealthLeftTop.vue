<template>
  <div class="left-top-box">
    <div>
      <el-row  class="left-main-container-top">
        <el-col :span="10">
            <HealthIndex></HealthIndex>
        </el-col>

        <el-col :span="14">
          <el-row style="margin-top: 45px">
            <div class="health-vision">
              <h>血压</h>
              <h>{{user.userLowBloodpressure}}-{{user.userHighBloodpressure}}mmHg</h>
            </div>
          </el-row>
          <el-row style="margin-top: 23px">
            <div class="health-vision">
              <h>心率</h>
              <h>{{user.userHeartrate}}</h>
              <h>次/分钟</h>
            </div>
          </el-row>
          <el-row style="margin-top: 23px">
            <div class="health-vision">
              <h>血糖</h>
              <h>{{user.userBloodsugar}}</h>
              <h>mmol/L</h>
            </div>
          </el-row>
        </el-col>
        <div class="left-box-view">
          <div>
            <el-row >
              <el-col :span="5" class="left-main-container-susgest " style="margin-left: 120px;">
                <h>血型</h>
                <h style="margin-left: 10px">{{user.userBloodType}}</h>
              </el-col>
              <el-col :span="6" class="left-main-container-susgest">
                <h>身高</h>
                <h style="margin-left: 10px">{{user.userHeight}}cm</h>
              </el-col>
              <el-col :span="6" class="left-main-container-susgest" >
                <h>年龄</h>
                <h style="margin-left: 10px">{{user.userAge}}岁</h>
              </el-col>
            </el-row>
          </div>
          <el-row>
            <el-col :span="7" style="margin-left: 70px">
              <el-button @click="Recording" round type="" class="left-main-container-button">记录数据</el-button>
            </el-col>
            <el-col :span="7" style="margin-left: 10px">
              <el-button @click="ExportData" round class="left-main-container-button">查看数据</el-button>
            </el-col>
            <el-col :span="1" style="margin-left: 10px">
              <el-button @click="prediction" round class="left-main-container-button">血糖预测</el-button>
            </el-col>

          </el-row>

        </div>
      </el-row>

    </div>
  </div>
</template>

<script setup lang="ts">
// import router from '../router';
import HealthIndex from "@/components/HealthIndex.vue";
import {onBeforeMount, onMounted, reactive} from "vue";
import axios from 'axios'
import {useRoute, useRouter} from "vue-router";
const router = useRouter()
const route = useRoute()
// const user = reactive({
//   email:'1',
//   userBloodType:'',
//   userHeight:'',
//   userAge:'',
// })


function doHandleMonth(month:any) {
  var m = month;
  if (month.toString().length == 1) {
    m = "0" + month;
  }
  return m;
}


function getDay(day:any) {
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

const user = reactive({
  email: sessionStorage.getItem("email"),
  today:getDay(0),
  userBloodType:'',
  userHeight:'',
  userAge:'',

  userHighBloodpressure: "",
  userLowBloodpressure: "",
  userHeartrate: "",
  userBloodsugar: "",
})
function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo",{params:{
      email:user.email}
  }).then(function (res){
    const data = res.data.object
    user.userAge = data.userAge;
    user.userHeight = data.userHeight;
    user.userBloodType = data.userBloodtype;
  })
}

function getInfo() {
  axios.post('/mtc/health/getRecord',{email: user.email,start: user.today, end: user.today})
      .then(function (res){
        // console.log(res.data.object[0])
        const data = res.data.object[0]
        user.userHighBloodpressure = data.userHighBloodpressure;
        user.userLowBloodpressure = data.userLowBloodpressure;
        user.userHeartrate = data.userHeartrate;
        user.userBloodsugar = data.userBloodsugar;

      })
}






onMounted(()=> {
  getBasicInfo(),
      getInfo()
})


function Recording() {
  router.push({ path: "/Myhealth/Recording" })
}

function ExportData() {
  router.push({ path: "/MyHealth/ExportData" })
}

function prediction() {
  router.push({ path: "/MyHealth/BloodsugarPrediction" })
}
function Myhealth() {
  router.push({ path: "/MyHealth/HealthAdvice" })
}

</script>

<style scoped>

.left-main-container-top {
  width: 496px;
  height: 360px;
  opacity: 1;
  background: rgba(255, 255, 255, 1);
  /*backdrop-filter: blur(85.14px);*/
  border-radius: 15px;
}


/*.health-number {*/
/*  margin-left: 35px;*/
/*  margin-top: 35px;*/
/*  width: 189.62px;*/
/*  height: 192.73px;*/
/*  opacity: 1;*/
/*  background: rgba(161, 199, 126, 1);*/
/*  border-radius: 15px;*/
/*  border: 0.63px solid rgba(164, 164, 164, 1);*/
/*}*/

.health-vision {
  margin-left: 35px;
  margin-top: 2px;
  width: 226.16px;
  height: 39.43px;
  opacity: 1;
  border-radius: 30.46px;
  background: rgba(85, 166, 132, 0.26);

  font-size: 20px;
  font-weight: 500;
  letter-spacing: 0px;
  line-height: 38px;
  text-align: left;
  vertical-align: top;
}
.health-vision h{
  margin-left: 15px;
}
.left-main-container-susgest {
  margin-left: 10px;
  width: 47px;
  height: 23px;
  opacity: 1;
  /** 文本1 */
  font-size: 20px;
  font-weight: 500;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(133, 165, 194, 1);
  text-align: left;
  vertical-align: top;
  margin-top: -35px;
}

.left-main-container-button {
  width: 131px;
  font-size: 20px;
  color: #4f7161;
}

/*.left-top-box{*/
/*  margin-left: 142px;*/
/*  margin-top: 221px;*/
/*  width: 496px;*/
/*  height: 360px;*/
/*  opacity: 1;*/
/*  backdrop-filter: blur(85.14px);*/
/*  border-radius: 15px;*/
/*}*/

.left-box-view{
  margin-top: -25px;
  margin-left: -38px;
}

</style>