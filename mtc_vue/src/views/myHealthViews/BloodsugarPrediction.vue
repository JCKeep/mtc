<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>

      <div class="user-por-box">
        <h class="user-por-title">
          血糖预测
        </h>

        <div style="padding-left: 1190px; margin-top: -55px" @click="goHealth">
          <el-button link>
            <el-icon style="font-size: 25px">
              <Close/>
            </el-icon>
          </el-button>
        </div>

        <div class="user-text-box">

          <!-- <div class="tips-box"> -->
          <h2 style="padding-top: 30px; margin-left: 50px;">温馨提示：请输入七次血糖数据进行预测</h2>
          <div style="margin-left: 750px; margin-top: -20px;">
            <el-row style="margin-top: -28px;">
              <el-col :span="7" style="margin-left: 80px; margin-top: -6px;">
                <el-button @click="adderVisible = true" style="font-size: 20px; height:35px;" color="#56A785" plain>
                  添加数据
                </el-button>

                <el-dialog
                    class="adder-dialog"
                    v-model="adderVisible"
                    top="37vh" draggable
                    width="770px"
                    title="血糖预测-添加数据"
                    style="--el-dialog-border-radius: 15px;margin-top: 60px"
                    append-to-body>
                  <!--            <el-input class="drug-add-search" v-model="input" placeholder="Search here" />-->
                  <!--            <el-button class="drug-add-search-btn" type="primary" :icon="Search" circle />-->
                  <el-table :data="BloodsugarData" class="drug-table-item">
                    <el-table-column type="index" label="序号" width="60" align="center"/>
                    <el-table-column property="time" label="时间" width="270" align="center">
                    </el-table-column>
                    <el-table-column property="bloodsugar" label="血糖" width="300" align="center">
                      <template #default="scope">
                        <el-form-item :error="scope.row.error" >
                          <p style="color: rgb(235,110,110);font-size: 15px;margin-top: 20px">*&nbsp;&nbsp;</p>

                          <el-input
                              style="width: 240px;margin-top: 20px"
                            v-model="scope.row.bloodsugar"
                            @input="tableSugarTest(scope.row)"
                          >
                          <template #append>mmol/L</template>
                          </el-input>
                        </el-form-item>
<!--                        <div>-->
<!--                          <span style="color: rgb(235,110,110);font-size: 15px">*&nbsp;&nbsp;</span>-->
<!--                          <el-input-->
<!--                              v-model="scope.row.bloodsugar"-->
<!--&lt;!&ndash;                              oninput="value=value.replace(/[^\d]/g,'')"&ndash;&gt;-->

<!--                              style="width: 250px"-->
<!--                              placeholder="请输入血糖">-->
<!--                            <template #append>mmol/L</template>-->
<!--                          </el-input>-->
<!--                        </div>-->
                      </template>
                    </el-table-column>
                    <el-table-column label="操作" width="100" align="center">
                      <template v-slot="scope">
                        <el-button link type="primary" @click.prevent="onSubmit(scope.row.time,scope.row.bloodsugar)">
                          提交
                        </el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                  <el-button @click="allSubmit" type="success" style="margin-left: 635px;margin-top: 20px">一键提交
                  </el-button>
                </el-dialog>

              </el-col>
              <el-col :span="10">
                <el-button @click="healthAdvice"
                           style="margin-left: 15px; font-size: 20px; height:35px; margin-top: -6px;"
                           color="#56A785" plain>健康建议
                </el-button>
              </el-col>
            </el-row>

          </div>

          <div
              style="width:1100px; margin: auto; margin-top: 25px; background: rgba(255, 255, 255, 1); border-radius: 15px; box-shadow: 2px 2px 1px 0px rgba(0, 0, 0, 0.25);">
            <!--            <div style="">-->

            <el-row>
              <el-col :span="12">
                <LineChart style="width: 600px; height: 500px; margin: auto;"></LineChart>

                <h1 style="margin-top: -40px; margin-left: 210px;">近七日血糖数据</h1>
              </el-col>

              <el-col :span="12">
                <!--                <ForeCast></ForeCast>-->
                <ForeCast style="width: 600px; height: 500px; margin: auto;"></ForeCast>
                <h1 style="margin-top: -40px; margin-left: 180px;">预测未来七日血糖数据</h1>
              </el-col>
            </el-row>

          </div>
        </div>

      </div>

    </el-container>
  </div>

</template>

<script lang="ts" setup>
import {ref, reactive, onMounted, toRefs} from 'vue'
import type {TabsPaneContext} from 'element-plus'
import TopBar from "@/components/TopBar.vue"
import LineChart from '@/components/LineChart.vue'
// import avatarURL from '@/assets/img/headPicture.jpg'
import axios from "axios";
// import router from "@/router"
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'


import {ElMessage} from "element-plus";
import ForeCast from "@/components/ForeCast.vue";

const router = useRouter()

const adderVisible = ref(false)


var user = reactive({
  email: sessionStorage.getItem("email"),
  flag: false,
  types: "晨间",
  userId: sessionStorage.getItem("userId")
})

function doHandleMonth(month: any) {
  var m = month;
  if (month.toString().length == 1) {
    m = "0" + month;
  }
  return m;
}


function getDay(day: any) {
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

const BloodsugarData = ref([
  {
    time: getDay(-6),
    bloodsugar: '12',
  },
  {
    time: getDay(-5),
    bloodsugar: '',
  },
  {
    time: getDay(-4),
    bloodsugar: '',
  },
  {
    time: getDay(-3),
    bloodsugar: '',
  },
  {
    time: getDay(-2),
    bloodsugar: '',
  },
  {
    time: getDay(-1),
    bloodsugar: '',
  },
  {
    time: getDay(0),
    bloodsugar: '',
  },


])

function tableSugarTest(row) {
  if (row.bloodsugar === '') {
    row.error = '必填项不能为空'
  } else if (!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(row.bloodsugar))) {
    row.error = '请输入0-100以内的数字,至多包含小数点两位'
  } else {
    row.error = ''
  }
}

// function rulesMatch(sugar: any) {
//   var regx = "/^0\.0*[1-9]\d*$|^[1-9](\.\d+)?$|^[1-9]\d(\.\d+)?$/";
//   console.log(sugar.toString().matches(regx))
//   if (!sugar.matches(regx)) {
//     console.log(sugar. matches(regx))
//     return false;
//   }
//   return true;
// }

function onSubmit(time: any, sugar: any) {
  if (!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(sugar)) ){
    ElMessage.error("请输入0-100内的数字")
  } else {
    axios.post("/mtc/health/addRecord", {
      email: user.email,
      date: time,
      bloodSugar: sugar
    }).then((Response) => {
      if (Response.data.code === 200) {
        ElMessage.success("提交成功")
        // router.push({path: '/MyHealth'})
      } else {
        ElMessage.error("提交失败")
        // console.log(Response.data.code)
        // router.push({ path: '/user' })
      }
    })
  }


}


function allSubmit(time: any, sugar: any) {
  if (!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[0].bloodsugar)) ){
    ElMessage.error("第一行数据有误，请输入0-100内的数字")
  }else if(!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[1].bloodsugar)) ){
    ElMessage.error("第二行数据有误，请输入0-100内的数字")
  }else if(!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[2].bloodsugar)) ){
    ElMessage.error("第三行数据有误，请输入0-100内的数字")
  }else if(!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[3].bloodsugar)) ){
    ElMessage.error("第四行数据有误，请输入0-100内的数字")
  }else if(!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[4].bloodsugar)) ){
    ElMessage.error("第五行数据有误，请输入0-100内的数字")
  }else if(!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[5].bloodsugar)) ){
    ElMessage.error("第六行数据有误，请输入0-100内的数字")
  }else if(!(/^(([1-9]?\d(\.\d{1,2})?)|99|99.00)$/.test(BloodsugarData.value[6].bloodsugar)) ){
    ElMessage.error("第七行数据有误，请输入0-100内的数字")
  }

  else{
    axios.post("/mtc/health/blood7day", {
      userId: user.userId,
      bloodSugar: [BloodsugarData.value[0].bloodsugar,
        BloodsugarData.value[1].bloodsugar,
        BloodsugarData.value[2].bloodsugar,
        BloodsugarData.value[3].bloodsugar,
        BloodsugarData.value[4].bloodsugar,
        BloodsugarData.value[5].bloodsugar,
        BloodsugarData.value[6].bloodsugar,
      ]
    }).then((Response) => {
      if (Response.data.code === 200) {
        ElMessage.success("提交成功")
        // router.push({path: '/MyHealth'})
      } else {
        ElMessage.error("提交失败")
        // console.log(Response.data.code)
        // router.push({ path: '/user' })
      }
    })
  }
}


function getInfo() {
  axios.post('/mtc/community/getDrug', {option: true, left: 1, right: 5})
      .then(function (res) {
        // console.log(res.data.object)
        BloodsugarData.value = res.data.object;
      })
}

function defaultInfo() {
  // axios.get('/mtc/medication/get',{params:{type: user.types ,email: user.email}})
  axios.post('/mtc/medication/get', {type: user.types, email: user.email})
      .then(function (res) {
        // console.log(res.data.object)
        BloodsugarData.value = res.data.object;
      })
}


function goHealth() {
  router.push({path: "/MyHealth"})
}

function healthAdvice() {
  router.push({path: "/MyHealth/HealthAdvice"})
}

const tableData = ref([]);

const form = reactive({
  userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
})

function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo", {
    params: {
      email: user.email
    }
  }).then(function (res) {
    const data = res.data.object
    form.userPortrait = data.userPortrait;
  })

  axios.get('/mtc/health/blood7day', {params: {userId: user.userId}})
      .then(function (res) {
        // userDocList.value = res.data.object;

        tableData.value = res.data.object;
        for (let i = 0; i < 7; i++) {
          BloodsugarData.value[i].bloodsugar = tableData.value[i];
          // console.log("hello"+tableData.value[i])
        }


      })
}

onMounted(() => {
  getBasicInfo()
})


</script>


<style scoped>
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

.user-por-box {
  margin-left: 90px;
  margin-top: 50px;
  width: 1260px;
  height: 769px;
  opacity: 1;
  border-radius: 30px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.user-text-box {
  margin-left: 39px;
  margin-top: 35px;
  width: 1182px;
  height: 625px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
}

.tips-box {
  height: 60px;
  margin-left: 58px;
  padding-left: 30px;
  background: rgba(255, 255, 255, 1);
  width: 90%;
  background-color: #fff;
  border-radius: 15px;
  box-shadow: 2px 2px 1px 0px rgba(0, 0, 0, 0.25);
}

.user-por-button {
  margin-top: 50px;
  margin-left: 1010px;
}

.record-cancel {
  left: 0px;
  top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgb(255, 255, 255);
  color: #414841;
}

.record-submit {
  left: 0px;
  top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}

.user-por-title {
  margin-left: 40px;
  width: 115px;
  height: 40px;
  opacity: 1;
  /** 文本1 */
  font-size: 28px;
  font-weight: 400;
  letter-spacing: 0.7px;
  line-height: 87px;
  color: rgba(86, 167, 133, 1);
  text-align: left;
  /*vertical-align: top;*/
}


.tag-title {
  margin-top: 60px;
  margin-left: 130px;

  /** 文本1 */
  font-size: 24px;
  font-weight: 500;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(85, 166, 132, 1);
  text-align: left;
  vertical-align: top;
  /*vertical-align: top;*/
}

.tag-one {
  margin-top: 45px;
  margin-left: 50px;
}

.left-box {
  margin-left: 130px;
  margin-top: 30px;
  width: 317px;
  height: 317px;
}

.right-box {
  margin-left: 476px;
  margin-top: 30px;
  width: 469px;
  height: 317px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.el-tag--large.is-closable {
  padding-right: 20px;
  margin-right: 33px;
  padding-left: 20px;
}

.el-tag--large {
  --el-icon-size: 23px;
}

.drug-table-item {
  margin-top: -25px;
  height: auto;
  width: auto;
}

.drug-add-search {
  width: 110px;
  margin-left: 90px;
  margin-top: -128px;
}

.el-button.is-circle {
  margin-top: -130px;
  margin-left: -15px;
}

.abc {
  margin-left: 10px;
  height: 10px;
}

.choose-time {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
  margin-left: 20px;
  margin-top: 23px;
  width: 40px;
}

.arrow-down {
  color: #55A684;
  width: 35px;
  height: 33px;
  opacity: 1;

  border-radius: 8.77px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

/*批量添加按钮*/
:deep(.el-button.is-circle[data-v-27185a17] ) {
  margin-top: 23px;
  margin-left: 15px;
  /*border-radius: 8.77px;*/
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}
</style>