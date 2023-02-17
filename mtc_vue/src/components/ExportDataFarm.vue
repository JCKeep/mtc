<template>
  <el-table
      :data="tableData"
      style="width: 1020px;border-radius: 15px;"
      border
      stripe
      size="large"
      class="export-form"
  >
    <el-table-column style="font-size: 40px" align="center" prop="recordDate" label="日期" width="170" />
    <el-table-column align="center" prop="userWeight" label="体重-KG" width="170" />
    <el-table-column align="center" prop="userBloodsugar" label="血糖-mmol/L" width="170" />
    <el-table-column align="center" label="血压-mmHg" width="340">
      <el-table-column align="center" prop="userHighBloodpressure" label="高压" width="170" />
      <el-table-column align="center" prop="userLowBloodpressure" label="低压" width="170" />
    </el-table-column>
    <el-table-column align="center" prop="userHeartrate" label="心率-次/分钟" width="170" />
  </el-table>
</template>

<script  setup>
import moment_tz from "moment-timezone";

import {onBeforeMount, reactive, ref} from "vue";
import axios from "axios";

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

function getDayTime(time,day) {
  var today = new Date();
  var targetday_milliseconds =  today.setTime(time) + 1000 * 60 * 60 * 24 * day;
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
  email:sessionStorage.getItem("email"),
  start:getDay(-30),
  end:getDay(0),
})

const tableData = ref([
  {
    recordDate: "",
    userBloodsugar: "",
    userHealthCondition: "",
    userHeartrate: "",
    userHighBloodpressure: "",
    userLowBloodpressure: "",
    userWeight: "",

  },
])

function getInfo() {
  axios.post('/mtc/health/getRecord',{email: user.email,start: user.start, end: user.end})
      .then(function (res){
        // console.log(res.data.object)
        tableData.value = res.data.object;
        for (let i = 0; tableData.value[i].recordDate!=""; i++) {
          let tmp = moment_tz(res.data.object[i].recordDate).tz( 'Asia/Shanghai').format('YYYY-MM-DD');
          // console.log(tmp);
          tableData.value[i].recordDate = tmp.toString();
          // tableData.value[i].userBloodsugar = res.data.object[i].userBloodsugar;
          // tableData.value[i].userHighBloodpressure = res.data.object[i].userHighBloodpressure;
          // tableData.value[i].userLowBloodpressure = res.data.object[i].userLowBloodpressure;
          // tableData.value[i].userHeartrate = res.data.object[i].userHeartrate;
          // tableData.value[i].userWeight = res.data.object[i].userWeight;
          // tableData.value[i].userHealthCondition = res.data.object[i].userHealthCondition;
        }
      })
}

onBeforeMount(() => {
  getInfo()
})

</script>

<style >
.export-form{
  height: 600px;
}

/*.el-table--large {*/
/*  !* font-size: var(--el-font-size-base); *!*/
/*  font-size: 22px;*/
/*}*/

/*.el-table .cell {*/
/*  box-sizing: border-box;*/
/*  overflow: hidden;*/
/*  text-overflow: ellipsis;*/
/*  white-space: normal;*/
/*  word-break: break-all;*/
/*  line-height: 45px;*/
/*  padding: 0 12px;*/
/*}*/
/*.el-table thead.is-group th.el-table__cell {*/
/*  background: rgba(85, 166, 132, 0.44);*/
/*}*/
/*.el-table--striped .el-table__body tr.el-table__row--striped td.el-table__cell {*/
/*  background: rgba(221, 236, 229, 0.94);*/
/*}*/
/* .el-table thead {*/
/*  color: #f8f8f8;*/
/*  font-weight: 500;*/
/*}*/
</style>
