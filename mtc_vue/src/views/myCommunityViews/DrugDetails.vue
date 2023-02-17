<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>

      <el-container>
        <el-aside class="aside-box">
          <CommunityAside></CommunityAside>
        </el-aside>

        <el-main class="main-box">

          <el-row>
            <el-col :span="5">
              <div class="label-text">药物详情</div>
            </el-col>
            <el-col :span="10">
              <!--            <el-button @click="returnHome" auto-insert-space class="record-cancel">取消</el-button>-->
            </el-col>
          </el-row>
          <div style="height: 70px;"></div>
          <div style="border-radius: 15px;width: 900px; padding: 35px;box-shadow: 15px 15px 15px #d3d3d3;">
            <!-- <div style="width: 90%; height: 480px; border-radius: 15px; padding: 35px;box-shadow: 15px 15px 15px #d3d3d3;"> -->
            <el-row :gutter="20" style="margin-bottom: -20%;">
              <el-col :span="10">
                <el-image
                    :src="drugInfomation.drugpic"
                    class="drugimage"
                    :preview-src-list="[drugInfomation.drugpic]"
                    :initial-index="0"
                    fit="cover"
                    preview-teleported="true"
                />
              </el-col>
              <el-col :span="10">
                <p style="color:#56a785; font-size: 24px; margin-left: 30px;">药物名称：{{drugInfomation.drugname}}</p>
                <p style="color:#56a785; font-size: 24px; margin-left: 30px;">药物说明：</p>
              </el-col>
            </el-row>

            <el-row style="margin-top: 120px;">
<!--              <el-col :span="11">-->
                <p style="margin: 0px 20px 20px 30px;line-height: 2;font-size:20px;">
                  {{drugInfomation.drugdetail}}
                </p>
<!--              </el-col>-->
              <!-- <el-col :span="13" style="margin-top: -70px">
                <el-table :data="foodData" style="width: 100%;font-size:20px;" :row-style="{height:'50px'}" :cell-style="{padding:'5px'}">
                  <el-table-column prop="typename" align="center" label="含量参考" width="160" />
                  <el-table-column prop="typevalue" align="center" label="约每100克食物中的含量" width="240" />
                </el-table>
              </el-col> -->
            </el-row>
            <el-button @click="DrugHome()" auto-insert-space class="record-cancel">返回</el-button>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, reactive, toRefs, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
import axios from "axios";
import TopBar from "@/components/TopBar.vue"
import CommunityAside from "@/components/CommunityAside.vue"

const router = useRouter()
const route = useRoute()

// import avatarURL from '@/assets/img/headPicture.jpg'
// const state = reactive({
//   avatarUrl:
//       '../assets/img/headPicture.jpg',
// })
// const { avatarUrl }= toRefs(state)

//const userId = ref(route.query?.userId)
const input = ref('')
// const userId = ref('1')
// var user = reactive({
//   email: sessionStorage.getItem("email"),
//   flag: false,
// })
const drugId = ref(route.query?.drugId)

// const tableDiet = ref([
//   {
//     foodName: '',
//     foodImage: '',
//     foodInfo: '',
//     foodCalories:'',
//     foodProtein:'',
//     foodFat: '',
//     foodSugar: '',
//   },
// ])
// const drugName = ref();
// const drugInfo = ref();
// const drugImage = ref();
// const foodCalories = ref();
// const foodProtein = ref();
// const foodSugar = ref();
// const foodFat = ref();

// const foodData = ref([
//   {
//     typename: '能量/kal',
//     typevalue: foodCalories
//   },
//   {
//     typename: '蛋白质/g',
//     typevalue: foodProtein
//   },
//   {
//     typename: '脂肪/g',
//     typevalue: foodFat
//   },
//   {
//     typename: '碳水化合物/g',
//     typevalue: foodSugar
//   }
// ])

const drugInfomation = reactive({
  drugid: '',
  drugname: '',
  drugpic: '',
  drugdetail: '',
})


function DrugHome() {
  router.push({ path: "/Community/DrugHome" })
}

// function getInfo() {
//   axios.get('/mtc/diet/foodInfo',{ params: { foodId:foodId.value } })
//       .then(function (res){
//         // console.log(res.data.object)
//         tableDiet.value = res.data.object;
//         foodData.value[0].typevalue = tableDiet.value.foodCalories;
//         foodData.value[1].typevalue = tableDiet.value.foodProtein;
//         foodData.value[2].typevalue = tableDiet.value.foodFat;
//         foodData.value[3].typevalue = tableDiet.value.foodSugar;

//       })
// }

function getInfo() {
  console.log(drugId.value)
  axios.get('/mtc/community/drug/information', { params: { drugid:sessionStorage.getItem("drugId") } })
    .then(function (res) {
      // console.log("hello")
      // console.log(res.data)
      const data = res.data.object
      console.log(data)
      // drugInfomation.drugid = data.drugId;
      drugInfomation.drugdetail = data.drugdetail;
      drugInfomation.drugname = data.drugname;
      drugInfomation.drugpic = data.drugpic;
      // drugName.value = data.drugName
      // drugInfo.value = data.drugInfo
      // drugImage.value = data.drugImage
    })
}

onBeforeMount(() => {
  getInfo()
})

</script>

<style scoped>
.common-layout {
  position: relative;
  width: 1440px;
  /*height: 1024px;*/
  box-shadow: #55a684;
  /*height: 100%;*/
  /* background-color: #55a684; */
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

.aside-box {
  margin-left: 100px;
  width: 300px;
  height: 650px;
}

.main-box {
  width: 930px;
  margin-left: 30px;
  margin-top: 5px;
  /*margin-left: 430px;*/
  /*margin-top: -650px;*/
  /* margin-top: -220px; */
}

.aside-btn {
  color: #55a684;
  font-size: 20px;
  text-align: center;
  margin-top: 35px;
  width: 280px;
  height: 65px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.search-text {
  font-size: 20px;
  letter-spacing: 0.4px;
  color: black;
}
.label-text {
  font-size: 24px;
  letter-spacing: 0.4px;
  color: #55a684;
  margin-top: 35px;
}

.record-cancel{
  margin-left: 680px;
  margin-top: 30px;
  font-size: 20px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgba(219, 236, 229, 1);
  color: #414841;
}

.input-box {
  margin-top: 8px;
  width: 890px;
  height: 60px;
  opacity: 1;
  border-radius: 15px;
  font-size: 20px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
}
.input-box-btn {
  width: 80px;
  font-size: 22px;
  margin-top: -5px;
  text-align: center;
}
.table-box {

  margin-top: 80px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

/*.foodimage {*/
/*//border-radius: 5%;*/
/*  transform: translateY(-80px);*/
/*  width: 85%;*/
/*  height: 75%;*/
/*  display: block;*/
/*  margin:0 auto;*/
/*  object-fit: cover;*/
/*  border-radius: 20px;*/
/*  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);*/
/*}*/

.drugimage {
  border-radius: 15%;
  /*border-radius: 30px;*/
  transform: translateY(-80px);
  width: 300px;
  height: 220px;
  display: block;
  margin:0 auto;
  object-fit: cover;
}
</style>