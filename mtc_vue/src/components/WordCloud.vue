<!--  -->
<template>
  <div>
    <div id="wordCloud" style="width: 450px;height: 317px;margin: 10px"></div>
  </div>
</template>

<script setup>
import * as echarts from 'echarts/core';
import 'echarts-wordcloud';
import axios from "axios";
import {onMounted, reactive} from "vue";

const user = reactive({
  email:sessionStorage.getItem("email")
})

const echart = reactive({
  data: [{
    value: 0, name: ''
  }],

})

const emit = defineEmits(['getKeyWords']) // 组件间传值
emit('getKeyWords')

function getKeyWords() {
  axios.get('/mtc/user/keywords', {
    params:
        {
          email: user.email
        }
  }).then(function (res) {
    // temps = res.data.object;
    for (let i = 0; i < 100; i++) {
      // echart.data[i].value = 30;
      // echart.data[i].name = res.data.object[i];
      echart.data.push({value: 30, name: res.data.object[i]});

    }
    // console.log('echarts data:', echart.data)
    initChart()
  })
}

onMounted(() => {
  getKeyWords()
    }
)

function initChart() {
  var myChart = echarts.init(document.getElementById('wordCloud'));
  const option = {
    title: {
      text: '',
      x: "center"
    },
    backgroundColor: "#fff",
    // tooltip: {
    //   pointFormat: "{series.name}: <b>{point.percentage:.1f}%</b>"
    // },
    series: [
      {
        type: "wordCloud",
        //用来调整词之间的距离
        gridSize: 10,
        //用来调整字的大小范围
        // Text size range which the value in data will be mapped to.
        // Default to have minimum 12px and maximum 60px size.
        sizeRange: [14, 40],
        // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
        //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
        rotationRange: [-45, 0, 45, 90],
        // rotationRange: [ 0,90],
        // rotationRange: [0, 0],
        //随机生成字体颜色
        // maskImage: maskImage,
        textStyle: {
          color:
              function () {
                return (
                    "rgb(" +
                    Math.round(Math.random() * 199) +
                    ", " +
                    Math.round(Math.random() * 166) +
                    ", " +
                    Math.round(Math.random() * 132) +
                    ")"

                );
              }
        },
        //位置相关设置
        // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
        // Default to be put in the center and has 75% x 80% size.
        left: "center",
        top: "center",
        right: null,
        bottom: null,
        width: "100%",
        height: "100%",
        //数据
        data: echart.data
      }
    ]
  };
  myChart.setOption(option);
  // 点击某个字
  myChart.on('click', function (params) {
    // console.log('myChart----click---:', params, '------', params.data)
  });
}

</script>
