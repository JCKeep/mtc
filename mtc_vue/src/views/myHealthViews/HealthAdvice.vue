<template>
  <div class="common-layout">
    <el-container>
      <!--      头部-->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>

      <div class="user-por-box">
        <h class="user-por-title">
          健康建议
        </h>
        <div  style="padding-left: 1190px; margin-top: -55px" @click="goHealth">
          <el-button link><el-icon style="font-size: 25px"><Close /></el-icon></el-button>
        </div>
        <div class="user-text-box">
          <el-scrollbar>
          <h2 style="padding-left: 50px;margin-right: 50px;padding-top: 20px"> {{text.mainText}}</h2>
          </el-scrollbar>
        </div>

      </div>
    </el-container>
  </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import TopBar from "@/components/TopBar.vue"
import WordCloud from "@/components/WordCloud.vue"
// import avatarURL from '@/assets/img/headPicture.jpg'
import { reactive, toRefs } from 'vue'
import axios from "axios";
// import router from "@/router"
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

// const state = reactive({
//   avatarUrl:
//       '../assets/img/headPicture.jpg',
// })
// const { avatarUrl }= toRefs(state)

function goHealth() {
  // router.push({path:"/MyHealth"})
  router.go(-1);
}

const text = reactive({
    mainText:"1、饮食\n" +
        "\n" +
        "通过对饮食结构的调整达到辅助加强治疗效果，减轻糖尿病患者对药物的依赖性。不要吃含糖较高、高胆固醇以及含酒精等食物，少吃辛辣刺激性食物，可多吃五谷杂粮、豆类及豆制品。此外，苦瓜、桑叶、洋葱、香菇、柚子等可辅助降低血糖，是糖尿病人的理想食物。\n" +
        "\n" +
        "2、运动\n" +
        "\n" +
        "适当的运动可增强体质，提高胰岛素的敏感性，改善血脂代谢，调节体重，防止并发症的产生。运动后应有适意感且适当进食，避免出现低血糖。依照患者身体情况选择运动种类，如步行、慢跑、骑自行车、打太极拳、踢毽子、球类运动等有氧运动。坚持循序渐进，持之以恒的原则，运动应选择在餐后1h进行。\n" +
        "\n" +
        "3、用药\n" +
        "\n" +
        "糖尿病系终身性疾病，无论患者采用哪种降糖药物都会有相对不同的副作用和不良反应。患者应正确掌握用药的时间、用法及用量，如降糖药饭前、饭后服用的例外类型，用药要准确，凡在联合用药时更要小心谨慎，不要过量或重复使用。胰岛素在饭前半小时注射，注射后按时就餐。根据血糖情况，由医生来调整药物的剂量，避免患者乱用偏方或者盲目增减用药剂量。\n" +
        "\n" +
        "4、预防并发症\n" +
        "\n" +
        "让患者及家属严格执行糖尿病患者的饮食及运动方案，了解糖尿病并发症的相关知识。定期去医院进行血糖、尿糖监测，全面了解用药水平和控制水平。也可采用便携式血糖仪进行血糖的自我检测，经常测血压、血脂，积极控制高血压和治疗高血脂，定期检查眼底、眼压、防止视网膜病变等视力危机损害。鞋袜要合脚，卫生、透气，防止周围神经和血管病变致足损伤。症状严重者，要及时送往医院治疗，以免耽误病情。\n" +
        "\n" +
        "5、心理\n" +
        "\n" +
        "由于糖尿病是慢性、终身性疾病，患者又缺乏医学知识，加之环境因素的影响，可能会产生孤独、焦虑、抑郁、悲观失望、恐惧心理、甚至产生厌世心理。患病早期阶段应进行及时的心理疏导，帮助患者接受患病事实，尽快转变角色，接受治疗，有助于早期控制疾病的发展，同时鼓励家属共同参与，使他们增强战胜疾病的信心。",
  }
)


var user = reactive({
  email:"1",
})
const form = reactive({
  userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
})

function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo",{params:{
      email:user.email}
  }).then(function (res){
    const data = res.data.object
    form.userPortrait = data.userPortrait;
  })
}


function getSuggest() {
  axios.get("/mtc/health/healthsuggest",{params:{
      }
  }).then(function (res){
    const data = res.data.object
    console.log("hello")
    text.mainText =  data
    // form.userPortrait = data.userPortrait;
  })
}

onMounted(()=> {
  getBasicInfo(),
      getSuggest()
})


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

.user-por-box{
  margin-left: 90px;
  margin-top: 50px;
  width: 1260px;
  height: 769px;
  opacity: 1;
  border-radius: 30px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.user-text-box{
  margin-left: 39px;
  margin-top: 25px;
  width: 1182px;
  height: 645px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
}
.user-por-button{
  margin-top: 50px;
  margin-left: 1010px;
}
.record-cancel{
  left: 0px;
  top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgb(255, 255, 255);
  color: #414841;
}

.record-submit{
  left: 0px;
  top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}

.user-por-title{
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


.tag-title{
  margin-top: 60px;
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

.tag-one{
  margin-top: 45px;
  margin-left: 50px;
}

.left-box{
  margin-left: 130px;
  margin-top: 30px;
  width: 317px;
  height: 317px;
}

.right-box{
  margin-left: 476px;
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
</style>