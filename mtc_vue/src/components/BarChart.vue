<template>
  <div class="box">
    <v-chart class="chart" id="weight"/>
  </div>
</template>

<script setup>
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { BarChart } from 'echarts/charts';
import {
    TitleComponent,
    TooltipComponent,
    LegendComponent,
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import {ref, defineComponent, reactive, onMounted} from 'vue';
import { GridComponent } from 'echarts/components';
import axios from "axios";
import * as echarts from "echarts/core";

use([
    CanvasRenderer,
    BarChart,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    GridComponent
]);

const user = reactive({
  email:sessionStorage.getItem("email"),
  start:getDay(-6),
  end:getDay(0),
})

const label = [
  'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven'
]

const echart = reactive({
  data: [{product: 'One', '测量': 132, '目标': 120}]
})

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

function getRecordInfo() {
  axios.post('/mtc/health/getRecord', {
    email: user.email,start: user.start, end: user.end
  }).then(function (res) {
    echart.data = []
    tableData.value = res.data.object;
    // console.log(tableData.value);
    // console.log(tableData.value[0].userBloodsugar);
    //
    // console.log(tableData.value.length);
    for (let  i = tableData.value.length; i < 7; i++) {
      echart.data.push({product: label[i], '测量': 0, '目标': 0 });
    }
    for (let i = 0; i < tableData.value.length; i++) {
      // console.log('echarts data:', echart.data[i]);
      let a = tableData.value[i].userWeight;
      if (a > 120) {
        a -= 10;
      }else if (a < 121 && a>89) {
        a += 8;
      }  else if (a < 90 && a>30) {
        a += 10;
      } else {
        a = 0;
      }
      let tmp = {product: label[i], '测量': tableData.value[i].userWeight, '目标': a};
      echart.data.push(tmp);
    }



    // echart.data.reverse();

    // console.log('echarts data:', echart.data)
    initChart()
  })
}


function initChart() {
  // var myChart = echarts.init(document.getElementById('blood'))
  var myChart = echarts.init(document.getElementById('weight'));

  const option = {
    legend: {
      x: '340px',
    },
    tooltip: {},
    dataset: {
      dimensions: ['product', '测量', '目标'],
      source: echart.data
    },
    xAxis: {type: 'category'},
    yAxis: {},
    // Declare several bar series, each will be mapped
    // to a column of dataset.source by default.
    series: [{type: 'bar'}, {type: 'bar'}]
  };
  myChart.setOption(option);
}

onMounted(()=>{
  getRecordInfo()
  // console.log("hello")
})
</script>

<style scoped>
.box{
  height: 280px;
  width: 525px;
  margin-left: -10px;
  padding-top: -15px;
}
</style>
