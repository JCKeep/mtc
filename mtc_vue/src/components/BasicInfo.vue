<template>
<!-- 基本信息预览 -->
  <div class="basic-box">
    <div class="info-overlook" :src="form.userPortrait">
      <p class="info-overlook-name">{{form.userName}}</p>
      <p class="info-overlook-basic">{{form.userAge}}岁长沙</p>
      <el-row class="info-overlook-tag">
        <el-col :span="5">
          <p>身高</p>
        </el-col>
        <el-col :span="5">
          <p>体重</p>
        </el-col>
        <el-col :span="5">
          <p>血糖</p>
        </el-col>
      </el-row>
      <el-row class="info-overlook-data">
        <el-col :span="5">
          <p style="font-weight: 600; ">{{form.userHeight}}cm</p>
        </el-col>
        <el-col :span="5">
          <p style="font-weight: 600; ">{{form.userWeight}}kg</p>
        </el-col>
        <el-col :span="5">
          <p style="font-weight: 600;">{{form.userBlood}}mmol/L</p>
        </el-col>
      </el-row>
      </div>
      <!-- 头像 -->
      <div class="info-avatar">
        <el-image fit="cover" style="width: 106px; height: 106px; border-radius: 50%;" :src="form.userPortrait" />
      </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, toRefs} from 'vue'
import axios from "axios";

// 加载本地头像
// import avatarURL from '@/assets/img/headPicture.jpg'
// const state = reactive({
//   avatarUrl:
//       '../assets/img/headPicture.jpg',
// })
// const { avatarUrl }= toRefs(state)


var user = reactive({
  email:sessionStorage.getItem("email"),
})
const form = reactive({
  userName: "AnGD Taylor",
  userHeight: 180,
  userWeight: 60,
  userBlood: 10.1,
  userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
  userAge: 18,
})

function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo",{params:{
    email:user.email}
    }).then(function (res){
    const data = res.data.object
    form.userName = data.userName;
    form.userHeight = data.userHeight;
    form.userWeight = data.userWeight;
    form.userBlood = data.userBlood;
    form.userPortrait = data.userPortrait;
    form.userAge = data.userAge;
  })
}

onMounted(()=> {
    getBasicInfo()
  })



</script>

<style scoped>
.info-overlook{
  width: 325px;
  height: 276.57px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  margin-top: 140px;
  margin-left: 140px;
}
.info-overlook {
  font-size: 24px;
  text-align: center;
  padding-top: 75px;
}
.info-overlook-name {
  font-family: 'Microsoft';
  font-weight: bold;
}
.info-overlook-basic {
  font-size: 22px;
  margin-top: 5px;
  color: rgba(136, 140, 150, 1);
}
.info-overlook-tag {
  margin-left: 20px;
  margin-top: 22px;
  width: 450px;
  font-size: 22px;
  color: rgba(86, 167, 133, 1);
}
.info-overlook-data {
  margin-left: 4px;
  margin-top: 5px;
  width: 450px;
}
.info-avatar {
  margin-left: 250px;
  margin-top: -332px;
  width: 107px;
  height: 108.4px;
  opacity: 1;
  border-radius: 50%;
}
</style>