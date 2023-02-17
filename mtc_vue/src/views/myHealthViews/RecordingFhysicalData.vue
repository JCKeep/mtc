<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>


      <el-container>
        <div class="record-box">

          <el-row>
            <el-col :span="10" style="margin-left: 35px;margin-top: 40px">
              <el-button @click="returnHealth" auto-insert-space class="record-cancel">取消</el-button>
            </el-col>
            <el-col :span="10" style="margin-top: 40px">
              <h class="record-title">记录身体数据</h>
            </el-col>
            <el-col :span="1" style="margin-top: 40px">
              <el-button @click="submit" auto-insert-space class="record-submit">提交</el-button>
            </el-col>

          </el-row>

          <el-row class="data-calendar">
            <el-form
                ref="formDate"
                :model="formLabelAlign"
                hide-required-asterisk
            >
              <el-form-item prop="date"
                            :rules="[{required: true,
                           message: '请选择记录的日期',
                           trigger: 'change',
                           }]">
                <DataCalendar
                    type="day"
                    value-format="YYYY-MM-DD"
                    v-model="formLabelAlign.date"
                    :disabled-date="disabledDate"
                ></DataCalendar>
                <!--            <div>{{formLabelAlign.date}}</div>-->
              </el-form-item>
            </el-form>
          </el-row>

          <el-row class="record-input">
            <el-form
                label-position="margin-left"
                label-width="60px"
                :model="formLabelAlign"
                style="max-width: 800px;"
                class="record-input-farm"
                :rules="rules"
                ref="form0"
            >
              <el-form-item label="体重" prop="weight">
                <el-input style="width: 530px" v-model="formLabelAlign.weight" placeholder="请输入体重">
                  <template #append>&nbsp; &nbsp; &nbsp; &nbsp; KG</template>
                </el-input>
              </el-form-item>
              <el-form-item label="血糖" prop="bloodSugar">
                <el-input style="width: 530px" v-model="formLabelAlign.bloodSugar" placeholder="请输入血糖">
                  <template #append>mmol/L</template>
                </el-input>
              </el-form-item>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="血压" prop="bloodPressureL">
                    <el-input style="width: 260px" v-model="formLabelAlign.bloodPressureL" placeholder="请输入低压">
                      <template #append>&nbsp; mmHg</template>
                    </el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="bloodPressureH">
                    <el-input style="width: 260px; margin-left: -41px" prop="formLabelAlign.bloodPressureH"
                              v-model="formLabelAlign.bloodPressureH" placeholder="请输入高压" clearable>
                      <template #append>&nbsp; mmHg</template>
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="心率" prop="heartRate">
                <el-input style="width: 530px" v-model="formLabelAlign.heartRate" placeholder="请输入心率">
                  <template #append>次/分钟</template>
                </el-input>
              </el-form-item>
            </el-form>
          </el-row>

        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import axios from 'axios';
import {onBeforeMount, onMounted, reactive, ref} from 'vue';
import TopBar from "@/components/TopBar.vue"
import DataCalendar from "@/components/DataCalendar.vue"
// import router from '@/router';
import {ElMessage, ElNotification} from "element-plus";
import moment_tz from "moment-timezone";
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'

const router = useRouter()
const route = useRoute()

const formDate = ref(null);

const formLabelAlign = reactive({
  email: sessionStorage.getItem("email"),
  date: '',
  weight: '',
  bloodSugar: '',
  bloodPressureH: '',
  bloodPressureL: '',
  heartRate: '',
})


const validateNumber = (rule, value, callback) => {
  //必须包含⼤⼩写字母、数字、特殊字符长度再9-16位之间
  if (value.length < 1 || value.length > 3) {
    callback(new Error("请输⼊1-3位有效数字"));
  } else {
    callback();
  }
};


// function getInfo() {
//   axios.post('/mtc/health/getRecord',{email: formLabelAlign.email,start: formLabelAlign.date, end: formLabelAlign.date})
//       .then(function (res) {
//         // console.log(res.data.object)
//         formLabelAlign.weight = res.data.object[0].userWeight;
//         formLabelAlign.bloodSugar = res.data.object[0].userBloodsugar;
//         formLabelAlign.bloodPressureH = res.data.object[0].userHighBloodpressure;
//         formLabelAlign.bloodPressureL = res.data.object[0].userLowBloodpressure;
//         formLabelAlign.heartRate = res.data.object[0].userHeartrate;
//       })
// }
//
// onBeforeMount(() => {
//   getInfo()
// })
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

function getSimpleDate(date) {
  if (date != null) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var s = date.getSeconds();
    s = s < 10 ? '0' + s : s;
    return y + '-' + m + '-' + d;
  }
}

const rules = reactive({
  // date: [{required: true, message: '必填项不能为空', trigger: 'blur'}],
  weight: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {pattern: /^0\.0*[1-9]\d*$|^[1-9](\.\d+)?$|^[1-9]\d(\.\d+)?$|^[1-4]\d\d(\.\d+)?$/, message: "请输入0-500内的数字", trigger: 'blur'}],

  bloodSugar: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {pattern: /^0\.0*[1-9]\d*$|^[1-9](\.\d+)?$|^[1-9]\d(\.\d+)?$/, message: "请输入0-100内的数字", trigger: 'blur'}],
  bloodPressureH: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {pattern: /^0\.0*[1-9]\d*$|^[1-9](\.\d+)?$|^[1-9]\d(\.\d+)?$|^[1-1]\d\d(\.\d+)?$/, message: "请输入0-200内的数字", trigger: 'blur'}],
  bloodPressureL: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {pattern: /^0\.0*[1-9]\d*$|^[1-9](\.\d+)?$|^[1-9]\d(\.\d+)?$|^[1-1]\d\d(\.\d+)?$/, message: "请输入0-200内的数字", trigger: 'blur'}],
  heartRate: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {pattern: /^0\.0*[1-9]\d*$|^[1-9](\.\d+)?$|^[1-9]\d(\.\d+)?$|^[1-1]\d\d(\.\d+)?$/, message: "请输入0-200内的数字", trigger: 'blur'}],
})
var form0 = ref("");

function submit() {
  formDate.value.validate((valid) => {
    if (valid) {
      form0.value.validate((valid) => {
        if (valid) {

          if (formLabelAlign.date > Date.now()) {
            ElMessage.error("不可选择今日之后的日期，请重新选择")
          } else {
            axios.post("/mtc/health/addRecord", {
              email: formLabelAlign.email,
              date: getSimpleDate(formLabelAlign.date),
              lowBloodpressure: formLabelAlign.bloodPressureL,
              highBloodpressure: formLabelAlign.bloodPressureH,
              heartRate: formLabelAlign.heartRate,
              weight: formLabelAlign.heartRate,
              bloodSugar: formLabelAlign.bloodSugar
            })
                .then((Response) => {
                  if (Response.data.code === 200) {
                    ElMessage.success("提交成功")
                    router.push({path: '/MyHealth'})
                  } else {
                    ElMessage.error("提交失败")
                    // console.log(Response.data.code)
                    // router.push({ path: '/user' })
                  }
                })
          }
        } else {
          ElMessage.error('数据类型错误或存在未填写的项目')

          return false
        }
      })
    } else {
      ElMessage.error('请选择要录入的日期')
    }
  })
}

function returnHealth() {
  router.push({path: '/MyHealth'})
}

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
  /*margin-left: 5%;*/
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


.record-box {
  margin-left: 156px;
  margin-top: 50px;
  width: 1127px;
  height: 800px;
  opacity: 1;
  border-radius: 30px;
  background: rgba(255, 255, 255, 1);
  border: 1px solid rgba(8, 155, 171, 1);
}

.record-title {
  /*margin-left: 625.17px;*/
  /*top: 172px;*/
  width: 173px;
  height: 40px;
  opacity: 1;
  /** 文本1 */
  font-size: 28px;
  font-weight: 400;
  letter-spacing: 0.7px;
  line-height: 30px;
  color: rgba(0, 0, 0, 1);
  text-align: left;
  vertical-align: top;
}

.record-cancel {
  margin-left: 0px;
  margin-top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgba(219, 236, 229, 1);
  color: #414841;
}

.record-submit {
  margin-left: 0px;
  margin-top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}

.record-input {
  margin-left: 234px;
  margin-top: 35px;
  width: 652px;
  height: 230px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(219, 236, 229, 1);
}

.record-input-farm {
  margin-left: 30px;
  margin-top: 40px;
  width: 748px;
  height: 262px;
  opacity: 1;
}

.record-input-farm[data-v-0d3ae6b0] {
  margin-left: 30px;
  margin-top: 25px;
  width: 748px;
  height: 262px;
  opacity: 1;
}

.data-calendar {
  margin-left: 219px;
  margin-top: 60px;
  width: 650px;
}

.record-input-farm[data-v-3a599d39] {
  margin-left: 30px;
  margin-top: 24px;
  width: 748px;
  height: 262px;
  opacity: 1;
}
</style>