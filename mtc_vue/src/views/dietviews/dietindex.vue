<template>
  <div class="diet-management">
    <div class="common-layout">
      <el-container>
        <el-header class="header">
          <TopBar></TopBar>
        </el-header>
        <el-container>
          <el-aside width="480px">
            <dietaside style="margin-left: 100px;"></dietaside>
          </el-aside>
          <el-main>
            <div style="height:40px"></div>
            <div id="today_diet">
              <p style="color:#56a785;font-size: 28px; float:left; ">今日饮食</p>
              <el-button type="primary" plain style="width:30%;height:50px; margin-left:450px;font-size: 20px;"
                         color="#56a785" round @click="searchRecord">查看饮食记录
              </el-button>
              <el-row :gutter="15">
                <el-col :span="1"></el-col>
                <el-col :span="7">
                  <div id="breakfast">
                    <p style="margin:10px 0;font-size:24px;">早餐</p>
                    <div style="float:left;">
                      <p class="textstyle" style="color:#888c96; text-align:left; font-size:20px;">摄入</p>
                      <p class="textstyle" style="color:#121a2d; text-align:left; font-size:20px;">{{ breakfastCalo }}
                        kcal</p>
                    </div>
                    <div style="margin-left: 100px;margin-top: 15px;">
                      <el-button id="breakfastedit" @click="goRecord1" type="primary" plain :icon="Edit" color="#56a785"
                                 size="large" circle/>
                    </div>
                  </div>
                </el-col>
                <el-col :span="7">
                  <div id="lunch">
                    <p style="margin:10px 0;font-size:24px;">午餐</p>
                    <div style="float:left;">
                      <p class="textstyle" style="color:#888c96; text-align:left; font-size:20px;">摄入</p>
                      <p class="textstyle" style="color:#121a2d; text-align:left; font-size:20px;">{{ lunchCalo }}
                        kcal</p>
                    </div>
                    <div style="margin-left: 100px;margin-top: 15px;">
                      <el-button id="lunchedit" @click="goRecord2" type="primary" plain :icon="Edit" color="#56a785"
                                 size="large" circle/>
                    </div>
                  </div>
                </el-col>
                <el-col :span="7">
                  <div id="dinner">
                    <p style="margin:10px 0;font-size:24px;">晚餐</p>
                    <div style="float:left;">
                      <p class="textstyle" style="color:#888c96; text-align:left; font-size:20px;">摄入</p>
                      <p class="textstyle" style="color:#121a2d; text-align:left; font-size:20px;">{{ dinnerCalo }}
                        kcal</p>
                    </div>
                    <div style="margin-left: 100px;margin-top: 15px;">
                      <el-button id="dinneredit" @click="goRecord3" type="primary" plain :icon="Edit" color="#56a785"
                                 size="large" circle/>
                    </div>
                  </div>
                </el-col>
                <el-col :span="1"></el-col>
              </el-row>
            </div>
            <br>
            <div>
              <el-row>
                <p style="color:#56a785; font-size: 28px; ">传图识糖</p>
                <el-popover
                    placement="right"
                    :width="200"
                    trigger="click"
                    content="上传图片大小不得超过5Mb"
                    popper-class="note_style"
                >
                  <template #reference>
                    <el-button :icon="More" plain size="small" color="#56a785" circle
                               style="margin-left: 25px; margin-top: 17px;"/>
                  </template>
                </el-popover>
                <!--              	<p style="font-size: 20px; margin-left: 40px; margin-top: 10px;">上传图片大小不得超过5Mb</p>	 -->
              </el-row>
              <br>
              <div
                  style="width: 91%; padding-top: 10px; padding-bottom: 5px; background-color: #dfede6; border-radius: 15px;">
                <div class="input" style="width:97%; margin-left:1%;">
                  <el-upload
                      ref="upload"
                      class="upload-demo"
                      action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                      :limit="1"
                      :on-exceed="handleExceed"
                      :before-upload="beforeAvatarUpload"
                      :auto-upload="false"
                      :http-request="upload"
                      v-model:file-list="r.fileList"
                  >
                    <el-input v-model="image_URL" style="width: 583px; float:left; height:35px;" maxlength="100"
                              show-word-limit :disabled="isInput()" placeholder="粘贴图片网址或者本地上传" clearable>
                    </el-input>
                    <template #trigger>
                      <el-button style="margin-left: 2px; font-size: 20px; height:35px;" color="#fead54" plain>
                        本地上传
                      </el-button>
                    </template>
                    <el-button class="ml-3" style="margin-left: 0px; font-size: 20px; height:35px; margin-top: -6px;"
                               color="#fead54" plain @click="submitUpload">
                      开始识图
                    </el-button>
                    <!-- 																							</template> -->
                  </el-upload>
                </div>
              </div>
            </div>
            <br>
            <div>
              <div>
                <!-- 														<div> -->
                <!-- 															<p style="color:#56a785; font-size: 28px; ">饮食推荐</p>
                                            </div>
                                            <div style="margin-left: 40px;">
                                            <p style="font-size: 20px; margin-left: 100px;">对推荐食物不满意？<el-button text style="color: #56a785; font-size: 20px;">点击此处</el-button
                                            >更换</p>	 -->
                <!-- 														<p style="color:#56a785; font-size: 28px; ">饮食推荐</p>
                                            <p style="font-size: 20px; margin-left: 100px;">对推荐食物不满意？<el-button text style="color: #56a785; font-size: 20px;">点击此处</el-button
                                            >更换</p>
                                            </div> -->
                <!-- 													</div> -->
                <el-row>
                  <p style="color:#56a785; font-size: 28px; ">饮食推荐</p>
                  <p style="font-size: 20px; margin-left: 40px; margin-top: 10px;">对推荐食物不满意？
                    <el-button text style="color: #56a785; font-size: 20px; margin-top: -8px;" @click="getRecommand()">
                      点击此处
                    </el-button
                    >
                    更换
                  </p>
                </el-row>
                <div style="height:20px;"></div>
                <div>
                  <el-row style="width:95%;">
                    <el-col :span="6">
                      <el-card class="foodcard" style="width:180px;">
                        <el-image
                            :src="foodImage1"
                            style="width: 138px; height: 138px; "
                            class="foodimage"
                            fit="cover"
                        />
                        <div style="padding: 5px">
                          <p class="textstyle" style="color:#121a2d; text-align:left; font-size:24px">{{
                              foodName1
                            }}</p>
                          <p class="textstyle" style="color:#888c96; font-size:20px">{{ foodCalories1 }} kcal</p>
                          <div style="float: right;">
                            <el-button id="istaken" style="margin-right: 0px; margin-bottom: 15px;"
                                       @click="goRecommand1"
                                       type="primary" plain :icon="Plus" color="#56a785" size="large" circle/>
                          </div>
                        </div>
                      </el-card>
                    </el-col>
                    <el-col :span="6">
                      <el-card class="foodcard" style="width:180px;">
                        <el-image
                            :src="foodImage2"
                            style="width: 138px; height: 138px; "
                            class="foodimage"
                            fit="cover"
                        />
                        <div style="padding: 5px">
                          <p class="textstyle" style="color:#121a2d; text-align:left;font-size:24px">{{ foodName2 }}</p>
                          <p class="textstyle" style="color:#888c96; text-align:left; font-size:20px">{{
                              foodCalories2
                            }}
                            kcal</p>
                          <div style="float: right;">
                            <el-button id="istaken" style="margin-right: 0px; margin-bottom: 15px;"
                                       @click="goRecommand2"
                                       type="primary" plain :icon="Plus" color="#56a785" size="large" circle/>
                          </div>
                        </div>
                      </el-card>
                    </el-col>
                    <el-col :span="6">
                      <el-card class="foodcard" style="width:180px;">
                        <el-image
                            :src="foodImage3"
                            style="width: 138px; height: 138px; "
                            class="foodimage"
                            fit="cover"
                        />
                        <div style="padding: 5px">
                          <p class="textstyle" style="color:#121a2d; text-align:left;font-size:24px">{{ foodName3 }}</p>
                          <p class="textstyle" style="color:#888c96; text-align:left; font-size:20px">{{
                              foodCalories3
                            }}
                            kcal</p>
                          <div style="float: right;">
                            <el-button id="istaken" style="margin-right: 0px; margin-bottom: 15px;"
                                       @click="goRecommand3"
                                       type="primary" plain :icon="Plus" color="#56a785" size="large" circle/>
                          </div>
                        </div>
                      </el-card>
                    </el-col>
                    <el-col :span="6">
                      <el-card class="foodcard" style="width: 180px;">
                        <el-image
                            :src="foodImage4"
                            style="width: 138px; height: 138px; "
                            class="foodimage"
                            fit="cover"
                        />
                        <div style="padding: 5px;">
                          <p class="textstyle" style="color:#121a2d; text-align:left;font-size:24px">{{ foodName4 }}</p>
                          <p class="textstyle" style="color:#888c96; text-align:left; font-size:20px">{{
                              foodCalories4
                            }}
                            kcal</p>
                          <div style="float: right;">
                            <el-button id="istaken" style="margin-right: 0px; margin-bottom: 15px;"
                                       @click="goRecommand4"
                                       type="primary" plain :icon="Plus" color="#56a785" size="large" circle/>
                          </div>
                        </div>
                      </el-card>
                    </el-col>
                  </el-row>
                </div>
              </div>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script setup lang="ts">
// import * as echarts from 'echarts'
// import { onMounted, nextTick, ref } from 'vue'
import {
  Edit,
  Plus,
  More
} from '@element-plus/icons-vue'

import {ref, reactive, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'
import axios from 'axios'
import dietaside from '../../components/dietaside.vue'
import TopBar from '../../components/TopBar.vue'
import {ElMessage, genFileId} from 'element-plus'
import type {UploadInstance, UploadProps, UploadRawFile} from 'element-plus'

const router = useRouter()
const route = useRoute()

const image_URL = ref('')
const userId = ref(route.query?.userId)
const upload = ref<UploadInstance>()
const foodId = ref()
var flag = ref("true")

//let userId = ref('1')

function goRecommand1() {
  router.push({name: 'recommand', query: {userId: userId.value, foodId: foodId1.value}})
}

function goRecommand2() {
  router.push({name: 'recommand', query: {userId: userId.value, foodId: foodId2.value}})
}

function goRecommand3() {
  router.push({name: 'recommand', query: {userId: userId.value, foodId: foodId3.value}})
}

function goRecommand4() {
  router.push({name: 'recommand', query: {userId: userId.value, foodId: foodId4.value}})
}

function goRecord1() {
  router.push({name: 'dietrecord', query: {userId: userId.value, chosen: 1}})
}

function goRecord2() {
  router.push({name: 'dietrecord', query: {userId: userId.value, chosen: 2}})
}

function goRecord3() {
  router.push({name: 'dietrecord', query: {userId: userId.value, chosen: 3}})
}

function searchRecord() {
  router.push({name: 'searchrecord', query: {userId: userId.value}})
}

const breakfastCalo = ref();
const lunchCalo = ref();
const dinnerCalo = ref();

let aData = new Date();
let dateValue = aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();

onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/dietInf', {params: {userId: userId.value, date: dateValue}})
      .then(function (res) {
        console.log(res.data)
        const data = res.data.object
        breakfastCalo.value = data.breakfastCalo
        lunchCalo.value = data.lunchCalo
        dinnerCalo.value = data.dinnerCalo
      })
})

const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('请上传jpg格式文件！')
    flag.value = "false"
    return false
  } else if (false) {
    flag.value = "false"
    ElMessage.error('图片大小不能超过2MB!')
    return false
  }
  flag.value = "true"
  return true
}

var r = reactive({
  fileList: []
})

function isInput() {
  if (r.fileList.length > 0) {
    return true
  } else return false
}

const submitUpload = () => {
  upload.value!.submit()
  if (flag.value == "true") {
    if (r.fileList.length > 0) {
      var data = new FormData();
      for (var i = 0; i < r.fileList.length; i++) {
        /* console.log(r.fileList[i].raw) */
        //data.append("files", r.fileList[i].raw)
        data.append("files", r.fileList[i].raw)
      }
      axios.post('http://114.132.226.110/api/mtc/community/food/uploadByImage', data).then((res) => {
        if (res.data.code === 200) {
          foodId.value = res.data.object;
          ElMessage.success("以下为系统识别出的结果")
          router.push({name: 'imageresult', query: {userId: userId.value, foodId: foodId.value}})

        } else {
          ElMessage.error("没有识别出相应结果")
        }
      })
    } else if (image_URL.value != "") {
      axios.get('http://114.132.226.110/api/mtc/community/food/uploadByLink', {params: {link: image_URL.value}})
          .then((res) => {
            if (res.data.code === 200) {
              foodId.value = res.data.object;
              router.push({name: 'imageresult', query: {userId: userId.value, foodId: foodId.value}})
            } else {
              ElMessage.error("没有识别出相应结果")
            }
          })
    } else {
      ElMessage.error("请输入链接或上传图片再开始识图")
    }
  }
  // if (r.fileList.length > 0) {
  //   var data = new FormData();
  // 		for (var i = 0; i < r.fileList.length; i++) {
  // 		  /* console.log(r.fileList[i].raw) */
  // 		  //data.append("files", r.fileList[i].raw)
  // 				data.append("files", r.fileList[i].raw)
  // 		}
  //   axios.post('http://114.132.226.110/api/mtc/community/food/uploadByImage', data).then((res) => {
  //     if (res.data.code === 200) {
  //       foodId.value = res.data.object;
  //       router.push({name: 'imageresult', query: {userId: userId.value, foodId: foodId.value}})
  //     }
  //     else {
  //       ElMessage.error("没有识别出相应结果")
  //     }
  //   })
  // }
  // else if(image_URL.value !=""||image_URL.value != null) {
  // 	axios.get('http://114.132.226.110/api/mtc/diet/uploadByLink', {params: {link: image_URL.value}})
  // 	.then((res) => {
  // 	  if (res.data.code === 200) {
  // 	    foodId.value = res.data.object;
  // 	    router.push({name: 'imageresult', query: {userId: userId.value, foodId: foodId.value}})
  // 	  }
  // 	  else {
  // 	    ElMessage.error("没有识别出相应结果")
  // 	  }
  // 	})
  // }
  // else {
  // 	ElMessage.error("请输入链接或上传图片，才能开始识图哦！")
  // }
}


// function submitUpload() {
//   if (r.fileList.length > 0) {
//     var data = new FormData();
// 				for (var i = 0; i < r.fileList.length; i++) {
// 				  /* console.log(r.fileList[i].raw) */
// 				  //data.append("files", r.fileList[i].raw)
// 						data.append("files", r.fileList[i].raw)
// 				}
//     axios.post('http://114.132.226.110/api/mtc/community/food/uploadByImage', data).then((res) => {
//       if (res.data.code === 200) {
//         foodId.value = res.data.object;
//         router.push({name: 'imageresult', query: {userId: userId.value, foodId: foodId.value}})
//       }
//       else {
//         ElMessage.error("没有识别出相应结果")
//       }
//     })
//   }
// 		else if(image_URL.value !=""||image_URL.value != null) {
// 			axios.get('http://114.132.226.110/api/mtc/diet/uploadByLink', {params: {link: image_URL.value}})
// 			.then((res) => {
// 			  if (res.data.code === 200) {
// 			    foodId.value = res.data.object;
// 			    router.push({name: 'imageresult', query: {userId: userId.value, foodId: foodId.value}})
// 			  }
// 			  else {
// 			    ElMessage.error("没有识别出相应结果")
// 			  }
// 			})
// 		}
// }

const foodImage1 = ref();
const foodId1 = ref();
const foodName1 = ref();
const foodCalories1 = ref();
const foodImage2 = ref();
const foodId2 = ref();
const foodName2 = ref();
const foodCalories2 = ref();
const foodImage3 = ref();
const foodId3 = ref();
const foodName3 = ref();
const foodCalories3 = ref();
const foodImage4 = ref();
const foodId4 = ref();
const foodName4 = ref();
const foodCalories4 = ref();

function getRecommand() {
  axios.get('http://114.132.226.110/api/mtc/diet/recommand', {params: {userId: userId.value}})
      .then(function (res) {
        console.log(res.data)
        const data = res.data.object
        foodId1.value = data[0].foodId
        foodCalories1.value = data[0].foodCalories
        foodName1.value = data[0].foodName
        foodImage1.value = data[0].foodImage
        foodId2.value = data[1].foodId
        foodCalories2.value = data[1].foodCalories
        foodName2.value = data[1].foodName
        foodImage2.value = data[1].foodImage
        foodId3.value = data[2].foodId
        foodCalories3.value = data[2].foodCalories
        foodName3.value = data[2].foodName
        foodImage3.value = data[2].foodImage
        foodId4.value = data[3].foodId
        foodCalories4.value = data[3].foodCalories
        foodName4.value = data[3].foodName
        foodImage4.value = data[3].foodImage
      })
}

onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/recommand', {params: {userId: userId.value}})
      .then(function (res) {
        console.log(res.data)
        const data = res.data.object
        foodId1.value = data[0].foodId
        foodCalories1.value = data[0].foodCalories
        foodName1.value = data[0].foodName
        foodImage1.value = data[0].foodImage
        foodId2.value = data[1].foodId
        foodCalories2.value = data[1].foodCalories
        foodName2.value = data[1].foodName
        foodImage2.value = data[1].foodImage
        foodId3.value = data[2].foodId
        foodCalories3.value = data[2].foodCalories
        foodName3.value = data[2].foodName
        foodImage3.value = data[2].foodImage
        foodId4.value = data[3].foodId
        foodCalories4.value = data[3].foodCalories
        foodName4.value = data[3].foodName
        foodImage4.value = data[3].foodImage
        /* 						foodId1.value = data.food1.foodId
                    foodId2.value = data.food2.foodId
                    foodId3.value = data.food3.foodId
                    foodId4.value = data.food4.foodId
                    foodName1.value = data.food1.foodName
                    foodName2.value = data.food2.foodName
                    foodName3.value = data.food3.foodName
                    foodName4.value = data.food4.foodName
                    foodCalories1.value = data.food1.foodCalories
                    foodCalories2.value = data.food2.foodCalories
                    foodCalories3.value = data.food3.foodCalories
                    foodCalories4.value = data.food4.foodCalories
                    foodImage1.value = data.food1.foodImage
                    foodImage2.value = data.food2.foodImage
                    foodImage3.value = data.food3.foodImage
                    foodImage4.value = data.food4.foodImage */
      })
})

</script>

<style>
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

.foodcard {
  width: 160px;
  background-color: white;
  border-radius: 15px;
}


.foodimage {
  border-radius: 50%;
  display: block;
  /*    margin:0 auto; */
}


.input >>> .el-input__inner {
  font-size: 20px;
}

.el-popover.note_style {
  font-size: 12px;
  height: 40px;
}

</style>
