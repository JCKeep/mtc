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
            <div style="height:80px;"></div>
            <p style="color:#56a785;font-size: 28px">饮食推荐</p>
            <div style="height: 65px;"></div>
            <div
                style="width: 90%; height: 480px;border-radius: 15px; padding: 35px;box-shadow: 15px 15px 15px #d3d3d3;">
              <el-row :gutter="20" style="margin-bottom: -20%;">
                <el-col :span="10">
                  <el-image
                      :src="foodImage"
                      class="foodimage"
                      :preview-src-list="[foodImage]"
                      :initial-index="0"
                      fit="cover"
                      preview-teleported="true"
                  />
                </el-col>
                <el-col :span="10">
                  <p style="color:#56a785; font-size: 24px; margin-left: 30px;">食物名称：{{ foodName }}</p>
                  <p style="color:#56a785; font-size: 24px; margin-left: 30px;">营养成分表：</p>
                </el-col>
              </el-row>

              <el-row style="margin-top: 80px">
                <el-col :span="11">
                  <p style="margin: 0px 20px 0px 30px;line-height: 2;font-size:20px;">
                    {{ foodInfo }}
                  </p>
                </el-col>
                <el-col :span="13" style="margin-top: -50px">
                  <el-table :data="foodData" style="width: 100%;font-size:20px;" :row-style="{height:'50px'}"
                            :cell-style="{padding:'5px'}">
                    <el-table-column prop="typename" align="center" label="含量参考" width="160"/>
                    <el-table-column prop="typevalue" align="center" label="约每100克食物中的含量" width="240"/>
                  </el-table>
                </el-col>
              </el-row>
            </div>
            <div style="height: 20px;"></div>
            <el-row :gutter="30" style="width: 90%; ">
              <el-col :span="3"/>
              <el-col :span="7">
                <el-button @click="goRecord"
                           style="background-color:#56a785; color:white; width:100%; height:60px; font-size:24px;">
                  添加至饮食记录
                </el-button>
              </el-col>
              <el-col :span="4"/>
              <el-col :span="7">
                <el-button plain @click="goBack" style="width:100%;height:60px; font-size:24px;">返回</el-button>
              </el-col>
            </el-row>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script setup>
import {ref, reactive, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'
import axios from 'axios'
import dietaside from '../../components/dietaside.vue'
import TopBar from '../../components/TopBar.vue'

const router = useRouter()
const route = useRoute()
const foodId = ref(route.query?.foodId)
const userId = ref(route.query?.userId)

const foodName = ref();
const foodInfo = ref();
const foodImage = ref();
const foodCalories = ref();
const foodProtein = ref();
const foodSugar = ref();
const foodFat = ref();

const foodData = [
  {
    typename: '能量/kal',
    typevalue: foodCalories
  },
  {
    typename: '蛋白质/g',
    typevalue: foodProtein
  },
  {
    typename: '脂肪/g',
    typevalue: foodFat
  },
  {
    typename: '碳水化合物/g',
    typevalue: foodSugar
  }
]

onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/foodInfo', {params: {foodId: foodId.value}})
      .then(function (res) {
        console.log(res.data)
        const data = res.data.object
        foodName.value = data.foodName
        foodInfo.value = data.foodInfo
        foodImage.value = data.foodImage
        foodCalories.value = data.foodCalories
        foodProtein.value = data.foodProtein
        foodSugar.value = data.foodSugar
        foodFat.value = data.foodFat
      })
})

function goRecord() {
  router.push({name: 'dietrecord',
    query: {
      userId: userId.value,
      foodId: foodId.value,
      foodName: foodName.value,
      foodCalories: foodCalories.value,
      foodSugar: foodSugar.value,
      foodFat: foodFat.value,
      foodProtein: foodProtein.value
    }
  })
}

function goBack() {
  router.push({name: 'dietindex', query: {userId: userId.value}})
}
</script>

<style lang="scss" scoped>
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

.button {
  padding: 0;
  min-height: auto;
}

.foodimage {
  border-radius: 15%;
  transform: translateY(-80px);
  width: 300px;
  height: 220px;
  display: block;
  margin: 0 auto;
  object-fit: cover;
}

</style>