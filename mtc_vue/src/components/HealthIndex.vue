<template>
  <div class="box">
    <v-chart id="Index" class="chart"/>
  </div>
</template>

<script lang="ts" setup>
import {use} from 'echarts/core';
import {CanvasRenderer} from 'echarts/renderers';
import {BarChart} from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart, {THEME_KEY} from 'vue-echarts';
import {ref, defineComponent, reactive, onMounted} from 'vue';
import {GridComponent} from 'echarts/components';
import axios from 'axios';
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
  email: sessionStorage.getItem("email"),
  rating: "",
})
// export default defineComponent({
//   name: 'GaugeChart',
//   components: {
//     VChart,
//   },


function getIndex() {
  axios.get("/mtc/health/rating", {params: {email: user.email}})
      .then((res) => {
        user.rating = res.data.object;
        initChart();
      })
}

onMounted(() => {
  getIndex()
})

// async function test() {
//   let res = await axios.get("/mtc/health/rating", {params: {email: user.email}})
//   rating = res.data.object;
//   return rating;
// }
//
//
// test().then((x) => {
//   console.log(x);
//   return x;
// })

// let tmp = await test();

// .then((res)=>{
//   rating = res.data.object;
//   console.log(res.data.object);
// })

function initChart() {
  var myChart = echarts.init(document.getElementById('Index'));
  const option = reactive({

    tooltip: {
      formatter: '{a} <br/>{b} : {c}%'
    },
    series: [
      {
        name: '健康指数',
        radius: '56%',
        type: 'gauge',
        itemStyle: {
          color: '#55A684',
        },
        progress: {
          show: true,
          width: 12,
        },
        axisLine: {
          lineStyle: {
            width: 12,
          }
        },
        detail: {
          valueAnimation: true,
          formatter: '{value}',
          fontSize: 40,
          offsetCenter: [0, '80%']
        },
        data: [
          {
            value: user.rating,
          }
        ]
      }
    ]
  });
  myChart.setOption(option);
}

//
// const getIndex = () =>{
//   axios.get("",{params:{email:user.email}})
//       .then((res)=>{
//         option.value.series[4][0].value = res.data.healthIndex;
//       })
// }


</script>

<style scoped>

.box {
  height: 350px;
  width: 350px;
  margin-top: -45px;
  margin-left: -45px;
}

</style>