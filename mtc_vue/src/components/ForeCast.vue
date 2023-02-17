<template>
  <div className="box">
    <v-chart className="chart" id="forecast"/>
  </div>
</template>

<script setup>
import {use} from 'echarts/core';
import {CanvasRenderer} from 'echarts/renderers';
import {LineChart} from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart, {THEME_KEY} from 'vue-echarts';
import {ref, defineComponent, reactive, onMounted} from 'vue';
import {GridComponent} from 'echarts/components';
import axios from "axios";
import * as echarts from "echarts/core";


const user = reactive({
  email: sessionStorage.getItem("email"),
  start: getDay(-6),
  end: getDay(0),
  userId:sessionStorage.getItem("userId")
  // userId:5
})


const echart = reactive({
  data: [0],
})

use([
  CanvasRenderer,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
]);

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

function getDayTime(time, day) {
  var today = new Date();
  var targetday_milliseconds = today.setTime(time) + 1000 * 60 * 60 * 24 * day;
  today.setTime(targetday_milliseconds); //注意，这行是关键代码

  var tYear = today.getFullYear();
  var tMonth = today.getMonth();
  var tDate = today.getDate();
  tMonth = doHandleMonth(tMonth + 1);
  tDate = doHandleMonth(tDate);
  // alert(tYear + "-" + tMonth + "-" + tDate)
  return tYear + "-" + tMonth + "-" + tDate;
}

// const tableData = ref([
//   {
//     userBloodsugar: "",
//   },
// ])

const tableData = ref([]);

function getRecordInfo() {

  // axios.get('/mtc/health/blood7day',{params:{userId:user.userId}})
  axios.get('/mtc/health/predict',{params:{userId:user.userId}})
      .then(function (res){
        // userDocList.value = res.data.object;

        echart.data = []
        tableData.value = res.data.object;
        console.log(res.data.object)
        for (let i = 0; i < 7; i++) {
          console.log(tableData.value[i]);
          echart.data.push(tableData.value[i]);
        }
        // echart.data.push(tableData.value[i]);

        // console.log(tableData.value);
        // console.log(tableData.value[0].userBloodsugar);

        // console.log(tableData.value.length);
        // for (let i = tableData.value.length; i < 7; i++) {
        //   echart.data.push(0);
        // }
        // for (let i = 0; i < tableData.value.length; i++) {
        //   // console.log('echarts data:', echart.data[i]);
        //   echart.data.push(tableData.value[i]);
        // }


        // console.log('echarts data:', echart.data)
        initChart()

      })


  //
  //   axios.post('/mtc/health/blood7day', {
  //     email: user.email, start: user.start, end: user.end
  //   }).then(function (res) {
  //     echart.data = []
  //     tableData.value = res.data.object;
  //     // console.log(tableData.value);
  //     // console.log(tableData.value[0].userBloodsugar);
  //
  //     // console.log(tableData.value.length);
  //     for (let i = tableData.value.length; i < 7; i++) {
  //       echart.data.push(0);
  //     }
  //     for (let i = 0; i < tableData.value.length; i++) {
  //       // console.log('echarts data:', echart.data[i]);
  //       echart.data.push(tableData.value[i].userBloodsugar);
  //     }
  //
  //
  //     // console.log('echarts data:', echart.data)
  //     initChart()
  // })
}


function initChart() {
  // var myChart = echarts.init(document.getElementById('blood'))
  var myChart = echarts.init(document.getElementById('forecast'));

  const option = {
    xAxis: {
      type: 'category',
      data: ['One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: echart.data,
        type: 'line'
      }
    ]
  };
  myChart.setOption(option);
}

onMounted(() => {
  getRecordInfo()
  // console.log("hello")
})
</script>

<style scoped>
.box {
  height: 280px;
  width: 525px;
  margin-left: -10px;
  padding-top: -15px;
}
</style>
