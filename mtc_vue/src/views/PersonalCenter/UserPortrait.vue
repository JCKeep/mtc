<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>

      <div class="user-por-box">
        <h class="user-por-title">用户画像</h>
        <el-row>
          <el-col :span="11">
            <div class="left-box">
              <el-image shape="square" style="width: 318px; height: 318px; border-radius: 15px;"
                        fit="cover"
                        :src="form.userPortrait"
                        :preview-src-list="form.srcList"
              />
            </div>
          </el-col>
          <el-col :span="8">
            <div class="right-box">
              <div id="wordCloud" style="width: 450px;height: 317px;margin: 10px"></div>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <h class="tag-title">标签：</h>
        </el-row>

        <div class="selected-tags-box">
          <el-scrollbar>
            <div class="selected-tags-content">
              <el-tag
                  v-for="tag in dynamicTags"
                  :key="tag"
                  class="selected-tags"
                  size="large"
                  closable
                  :disable-transitions="false"
                  @close="handleClose(tag)"
              >
                {{ tag }}
              </el-tag>
            </div>
          </el-scrollbar>
        </div>

        <el-row>
          <h class="tag-add-title">添加标签</h>
        </el-row>

        <div class="all-tags-box">
          <el-scrollbar>
            <div class="scrollbar-flex-content">
              <!-- <el-row class="all-tags-box"> -->
              <el-button
                  class="all-tags"
                  v-for="btn in Btns"
                  plain
                  :key="btn"
                  @click="addTags(btn)"
                  size="large"
              >
                {{ btn }}
              </el-button>
              <!-- </el-row> -->
            </div>
          </el-scrollbar>
        </div>

        <el-row>
          <el-input
              class="new-tag-input"
              v-if="inputVisible"
              ref="InputRef"
              v-model="inputValue"
              size="large"
              @keyup.enter="handleInputConfirm(inputValue)"
              @blur="handleInputConfirm"
              maxlength="10"
              show-word-limit
          />
          <el-button type="success" plain class="new-tag" size="large" v-else @click="showInput">
            + 自定义标签
          </el-button>
        </el-row>

      </div>

      <el-row class="user-por-button">
        <el-col :span="8">
          <!--          <el-button auto-insert-space class="record-submit">添加</el-button>-->
        </el-col>
        <el-col :span="1">
          <el-button @click="goOff" auto-insert-space class="record-cancel">返回</el-button>
        </el-col>
      </el-row>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref, nextTick} from 'vue'
import TopBar from "@/components/TopBar.vue"
import WordCloud from "@/components/WordCloud.vue"
// import avatarURL from '@/assets/img/headPicture.jpg'
import {reactive, toRefs} from 'vue'
import axios from "axios";
// const state = reactive({
//   avatarUrl:
//       '../assets/img/headPicture.jpg',
// })
// const { avatarUrl }= toRefs(state)

import {ElInput} from 'element-plus'
import type {TagProps} from 'element-plus'
import {useRouter} from "vue-router";

import {useRoute} from 'vue-router'

const router = useRouter()
const route = useRoute()


import * as echarts from 'echarts/core';
import 'echarts-wordcloud';

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
    echart.data = []
    // temps = res.data.object;
    for (let i = 0; i < 100; i++) {
      // echart.data[i].value = 30;
      // echart.data[i].name = res.data.object[i];
      echart.data.push({value: 30, name: res.data.object[i]});

    }
    console.log('echarts data:', echart.data)
    initChart()
  })
}

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
    console.log('myChart----click---:', params, '------', params.data)
  });
}


type Item = { type: TagProps['type']; label: string }

const inputValue = ref('')
const inputVisible = ref(false)
const InputRef = ref<InstanceType<typeof ElInput>>()
const dynamicTags = ref(['Tag 1', 'Tag 2', 'Tag 3'])
const Btns = ref(['糖糖用户', 'Taylor', '999', '金牌糖友', 'YYDS'])


function getDynamicTags() {
  axios.get('/mtc/user/keywords', {
    params:
        {
          email: user.email
        }
  }).then(function (res) {
    dynamicTags.value = res.data.object;
  })
}

var user = reactive({
  email: sessionStorage.getItem("email"),
})

const form = reactive({
  userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
  srcList: [
    'http://114.132.226.110/file/IMG_5267.JPG',]
})

function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo", {
    params: {
      email: user.email
    }
  }).then(function (res) {
    const data = res.data.object
    form.userPortrait = data.userPortrait;
    form.srcList[0] = data.userPortrait;
  })
  // router.go(0)
}

onMounted(() => {
  getBasicInfo(),
      getDynamicTags(),
      getKeyWords()
})

const addTags = (tag: any) => {
  dynamicTags.value.push(tag)
  // if (inputValue.value) {
  //
  // }
  inputVisible.value = false
  inputValue.value = ''
  // dynamicTags.value.push(dynamicTags.value.indexOf(tag), tag)
  // dynamicTags.value.push(inputValue.value)

  axios.post("/mtc/user/keywords", {
    email: user.email, keywords: [tag]
  }).then(function (res) {
    getDynamicTags();
    getKeyWords()
  })
}

const handleClose = (tag: any) => {
  dynamicTags.value.splice(dynamicTags.value.indexOf(tag), 1)

  axios.post("/mtc/user/delKeywords", {
    email: user.email, keywords: [tag]
  }).then(function (res) {
    getKeyWords()
  })
}

const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value!.input!.focus()
  })
}

const handleInputConfirm = (tag: any) => {
  if (inputValue.value) {
    dynamicTags.value.push(inputValue.value)
  }
  inputVisible.value = false
  inputValue.value = ''

  axios.post("/mtc/user/keywords", {
    email: user.email, keywords: [tag]
  }).then(function (res) {
    getKeyWords()
  })
}

function goOff() {
  router.go(-1);
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
  margin-left: 151px;
  margin-top: 80px;
  width: 1125px;
  height: 723px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
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
  margin-top: 65px;
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

.left-box {
  margin-left: 130px;
  margin-top: 30px;
  width: 317px;
  height: 317px;
}

.right-box {
  margin-left: 5px;
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

.selected-tags-box {
  margin-top: -20px;
  margin-left: 220px;
  margin-right: 130px;
}

.selected-tags-content {
  display: flex;
}

.selected-tags {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: auto;
  height: 40px;
  font-size: 16px;
  text-align: center;
  border-radius: 10px;
}

.tag-add-title {
  margin-top: 55px;
  margin-left: 130px;
  width: 115px;
  height: 37px;
  opacity: 1;
  /** 文本1 */
  font-size: 26px;
  font-weight: 400;
  letter-spacing: 0.7px;
  line-height: 0px;
  color: rgba(56, 56, 56, 1);
  text-align: left;
  vertical-align: top;
}

.scrollbar-flex-content {
  display: flex;
  /* margin-top: -4px; */
}

.all-tags-box {
  /* width: 900px; */
  margin-left: 290px;
  margin-right: 130px;
  margin-top: 5px;
}

.all-tags {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 40px;
  margin: 5px;
  text-align: center;
  font-size: 16px;
  border-radius: 10px;
}

.new-tag {
  width: 150px;
  height: 40px;
  margin-top: -46px;
  margin-left: 130px;
  font-size: 16px;
  border-radius: 10px;
}

.new-tag-input {
  width: 150px;
  height: 40px;
  margin-top: -46px;
  margin-left: 130px;
  font-size: 16px;
  border-radius: 10px;
}

</style>