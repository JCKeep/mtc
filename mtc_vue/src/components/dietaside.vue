<template>
  <el-card class="diet_aside_box" style="padding: 5px 5px;">
    <div>
      <!--	        <el-card class="diet_aside_box1">-->
      <!--										<el-image-->
      <!--										  :src="userPortrait"-->
      <!--										  class="image"-->
      <!--												style="width: 107px; height:107px;"-->
      <!--										/>-->
      <!--	              <div>-->
      <!--	                <p class="text1">{{userName}}</p>-->
      <!--	                <p class="text2">{{userAge}}岁</p>-->
      <!--	              </div>-->
      <!--	            <div>-->
      <!--	              <el-row :gutter="20">-->
      <!--	                <el-col :span="8">-->
      <!--	                  <div>-->
      <!--	                    <p class="text3">身高</p>-->
      <!--	                    <p class="text1">{{userHeight}}cm</p>-->
      <!--	                  </div>-->
      <!--	                </el-col>-->
      <!--	                <el-col :span="8">-->
      <!--	                  <div>-->
      <!--	                    <p class="text3">体重</p>-->
      <!--	                    <p class="text1">{{userWeight}}kg</p>-->
      <!--	                  </div>-->
      <!--	                </el-col>-->
      <!--	                <el-col :span="8">-->
      <!--	                  <div>-->
      <!--	                    <p class="text3">血糖</p>-->
      <!--	                    <p class="text1">{{userBlood}}mmol/L</p>-->
      <!--	                  </div>-->
      <!--	                </el-col>-->
      <!--	              </el-row>-->
      <!--	            </div>-->
      <!--	        </el-card>-->
      <BasicInfo style="margin-left: -135px;margin-top: -95px"></BasicInfo>
    </div>
    <div style="margin-top:250px;">
      <el-card  class="diet_aside_box2">
        <p style="color:#121a2d;font-size:24px">营养图谱</p>
        <br>
        <div>
          <el-row :gutter="15">
            <el-col :span="10">
              <div>
                <p class="text5">推荐摄入</p>
                <p class="text4">{{recommandIn}} kcal</p>
                <br>
                <p class="text5">今日摄入</p>
                <p class="text4">{{todayCalories}} kcal</p>
              </div>
            </el-col>
            <el-col :span="6">
              <br>
              <el-progress type="circle" :percentage="todayIn" :stroke-width="12" color="#70bd9b">
                <template #default="{ percentage }">
                  <span class="percentage-value">{{ (todayCalories/recommandIn*100).toString().substring(0,4) }}%</span>
                  <span class="percentage-label">今日摄入</span>
                </template>
              </el-progress>
            </el-col>
          </el-row>
        </div>
        <div>
          <br>
          <el-row :gutter="15">
            <el-col :span="9">
              <div>
                <p class="text3">碳水</p>
                <div style="width:78%; margin-left: 10px;">
                  <el-progress :percentage="todaySuger" color="#ff738f" :show-text="false"/>
                </div>
                <p class="text1">{{todaySuger}}g</p>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <p class="text3">蛋白质</p>
                <el-progress :percentage="todayProtein" color="#738fff" :show-text="false" width="78%"/>
                <p class="text1">{{todayProtein}}g</p>
              </div>
            </el-col>
            <el-col :span="7">
              <div>
                <p class="text3">脂肪</p>
                <el-progress :percentage="todayFat" color="#b8eea4" :show-text="false"/>
                <p class="text1">{{todayFat}}g</p>
              </div>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </div>
    <!-- 	      <div>
              <el-button type="primary" plain style="width:100%;height:50px; margin-top:15px;font-size: 20px;" color="#56a785" round @click="searchRecord">查看饮食记录</el-button>
            </div> -->
  </el-card>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
import { useRoute } from 'vue-router'
import BasicInfo from "@/components/BasicInfo.vue";
const route = useRoute()

const userId = ref(route.query?.userId)

const userName=ref();
const userAge = ref();
const userHeight = ref();
const userWeight = ref();
const userBlood = ref();
const userPortrait = ref();
const todayCalories = ref();
const todayFat = ref();
const todayProtein = ref();
const todaySuger = ref();
const caloPercent = ref();
const recommandIn = ref();
onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/userInformation', { params: { userId: userId.value} })
      .then(function (res) {
        // console.log(res.data)
        const data = res.data.object
        userName.value = data.userName
        userAge.value = data.userAge
        userHeight.value = data.userHeight
        userWeight.value = data.userWeight
        userBlood.value = data.userBlood
        userPortrait.value = data.userPortrait})
})

let aData = new Date();
let dateValue = aData.getFullYear() + "-" + (aData.getMonth() + 1) + "-" + aData.getDate();

onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/dietInf', { params: { userId: userId.value ,date: dateValue} })
      .then(function (res) {
        // console.log(res.data)
        const data = res.data.object
        todayCalories.value = data.todayCalories
        todayFat.value = data.todayFat
        todayProtein.value = data.todayProtein
        todaySuger.value = data.todaySuger
        caloPercent.value = data.caloPercent
        recommandIn.value = data.recomdIn
      })
})
const todayIn = caloPercent;
// const todayIn1 = todayIn1*1 + 20;
// 		const todayIn2 = todayIn1*1 - 20;
// if(todayIn1>100) {
// 	todayIn1 = 100;
// }
// if(todayIn2<0) {
// 	todayIn2 = 100;
// }

// function searchRecord() {
//   router.push({ name: 'searchrecord', query: {userId:userId.value}})
// }
</script>

<style >
.text1 {
  color: #121a2d;
  font-size: 20px;
  text-align: center;
  margin: 5px 0;
}

.text2 {
  color: #888c96;
  font-size: 20px;
  text-align: center;
  margin: 5px 0;
}

.text3 {
  color: #56a785;
  font-size: 20px;
  text-align: center;
  margin: 5px 0;
}

.text4 {
  color: #121a2d;
  font-size: 20px;
  text-align: left;
  margin: 5px 0;
}

.text5 {
  color: #888c96;
  font-size: 20px;
  text-align: left;
  margin: 5px 0;
}

.item {
  margin-bottom: 18px;
}

.diet_aside_box {
  margin-top:40px;
  width: 370px;
  height: 797px;
  background-color: #dfede6;
  border-radius: 15px;
  box-shadow: 10px 10px 10px 0 #d3d3d3;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.diet_aside_box1 {
  margin-top: 32px;
  height: 310px;
  width: 335px;
  background-color: white;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.diet_aside_box2 {
  margin-top:20px;
  width: 335px;
  background-color: white;
  border-radius: 15px;
  display: flex;
  flex-direction: column;
}

.image {
  border-radius: 50%;
  display: block;
  margin:0 auto;
}

.percentage-value {
  color: #56a785;
  display: block;
  font-size: 28px;
}

.el-progress-circle__track {
  stroke: 231,238,235
}

</style>