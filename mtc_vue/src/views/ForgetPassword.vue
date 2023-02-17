<template>
  <div class="common-layout">
    <el-container>
      <el-aside class="left-reset-container">
        <el-image style="width: 650px; height: 830px" :src="avatarURL" :fit="fit" />
      </el-aside>

      <el-aside class="right-reset-container">
        <div class="right-reset-title">木糖醇重置密码</div>

        <div class="right-reset-farm">
          <el-form
              ref="ruleFormRef"
              :model="ruleForm"
              status-icon
              :rules="rules"
              :required="true"
              class="demo-ruleForm"
              :label-position="'left'"
              size="default"
              style="width: 480px"
          >
            <el-form-item  label=" " prop="email" >
              <el-input  :prefix-icon="User" class="farm-input" v-model="ruleForm.email" placeholder="请输入邮箱账户"/>
            </el-form-item>
            <el-form-item label=" " :required="true" prop="identify">
              <el-input style="width: 65%" :prefix-icon="User" class="farm-input" v-model="ruleForm.identify"  autocomplete="off" placeholder="请输入验证码"/>
              <el-button @click="sendCode" class="send-code" auto-insert-space style="margin-left: 1%">发送验证码</el-button>
            </el-form-item>
            <el-form-item label=" " :required="true" prop="passNew">
              <el-input label=" " :prefix-icon="Lock" class="farm-input" v-model="ruleForm.passNew" type="password" autocomplete="off" placeholder="请输入密码"/>
            </el-form-item>
            <el-form-item label=" " :required="true" prop="checkPass">
              <el-input
                  class="farm-input"
                  v-model="ruleForm.checkPass"
                  type="password"
                  autocomplete="off"
                  placeholder="请确认密码"
                  :prefix-icon="Lock"
              />
            </el-form-item>
            <el-form-item label-width="4.5%" >
              <el-button @click="submit" class="submit-btn" auto-insert-space>确认</el-button>
            </el-form-item>

            <el-link  @click="goLogin" type="success" class="jump-logn" >去登录</el-link>
          </el-form>
        </div>
      </el-aside>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import avatarURL from '../assets/img/ReSet.svg'
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import BasicInfo from "@/components/BasicInfo.vue"
import axios from "axios"
import router from "@/router";
import { User, Lock } from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";


function goLogin() {
  router.push({path:"/login"})
}

const ruleFormRef = ref<FormInstance>()

const state = reactive({
  avatarUrl:
      '../assets/img/ReSet.svg',
})


const validatePass = (rule:any, value:any, callback:any) => {
  //必须包含⼤⼩写字母、数字、特殊字符长度再9-16位之间
  var regex = new RegExp("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,19}");
  if (value === "") {
    callback(new Error("请输⼊密码"));
  } else if (value.length < 6 || value.length > 19) {
    callback(new Error("请输⼊6~19位密码"));
  } else if (!regex.test(value)) {
    callback(new Error("密码必须同时包含字母、数字和特殊字符其中三项且⾄少6位"));
  } else {
    callback();
  }
};
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入新密码'))
  } else if (value !== ruleForm.passNew) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}

const ruleForm = reactive({
  email:'',
  identify: '',
  passNew:'',
  checkPass: '',
})

const rules = reactive({
  email: [{required:'true', message:'邮箱账户不能为空',trigger: 'blur' },{type:'email', message:'邮箱格式错误',trigger: 'blur' }],
  identify: [{required:'true', message:'请输入验证码',trigger: 'blur' }],
  passNew: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
})

function sendCode() {
  axios.get("/mtc/user/getcode",
      {params:{email:ruleForm.email}}).then((res:any)=>{
    ElMessage.success("验证码发送成功")
  })
}

function submit() {
  axios.post("/mtc/user/update", {
      userEmail:ruleForm.email,
      code:ruleForm.identify,
      userPasswordNew:ruleForm.passNew,
  }).then((Response)=>{
    if(Response.data.code===200){
      ElMessage.success("密码修改成功")
      router.push({path:"/login"})
    }else{
      ElMessage.error("密码修改失败")
    }
  })
}
</script>

<style scoped>
.common-layout {
  position: relative;
  width: 1440px;
  /*height: 1024px;*/
  box-shadow: #55a684;
  /*height: 100%;*/
  background-color: rgb(221, 236, 229);
  min-height: 100vh;
  overflow: hidden;
  /*left: 5%;*/
}

.left-reset-container{
  margin-left: 70px;
  margin-top: 50px;
  width: 650px;
  height: 840px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(240, 245, 255, 1);

}

.right-reset-container{
  margin-left: 0px;
  margin-top: 50px;
  width: 650px;
  height: 840px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
}

.right-reset-title{
  margin-left: 103px;
  margin-top: 80px;

  /** 文本1 */
  font-size: 60px;
  font-weight: 700;
  letter-spacing: 4px;
  color: rgba(85, 166, 132, 1);
}

.farm-input{
  width: 454px;
  height: 70px;
  opacity: 0.4;
  /*border-radius: 15px;*/

  /*border: 1px solid rgba(50, 56, 81, 1);*/
  /** 文本1 */
  font-size: 24px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(176, 183, 208, 1);
  text-align: left;
  vertical-align: middle;
}

.right-reset-farm{
  margin-left: 103px;
  margin-top: 80px;
}

.el-icon svg {
  color: #1c7957;
  font-size: 50px;
}

/*输入框里面的字离icon的距离*/
.el-input__inner {
  margin-left: 20px;
}
.el-input__wrapper {
  padding: 0px 32px;
}

.send-code{
  border-radius: 10px;
  height: 70px;
  font-size: 24px;
  color: #f8f8f8;
  background-color: #55a684;
}

.submit-btn {
  width: 100%;
  color: #f8f8f8;
  height: 60px;
  background-color: #55a684;
  font-size: 28px;
}

.submit-btn[data-v-8e17b12f] {
  width: 100%;
  color: #f8f8f8;
  background-color: #55a684;
}

.jump-logn{
margin-left: 22px;
  height: 39px;
  opacity: 1;
  /** 文本1 */
  font-size: 28px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(85, 166, 132, 1);
  text-align: left;
  vertical-align: middle;
}

.common-layout[data-v-5192b08b] {
  position: relative;
  width: 1440px;
  /* height: 1024px; */
  box-shadow: #55a684;
  /* height: 100%; */
  background-color: rgb(221, 236, 229);
  min-height: 950px;
  overflow: hidden;
  /* left: 5%; */
}

</style>