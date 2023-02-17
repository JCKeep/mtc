<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>
      <!-- 基本资料框 -->
      <div class="change-psd-box">
        <!-- 文字提示 -->
        <h class="basic-form-title">修改密码</h>
        <div class="basic-form-box">

          <BasicInfo></BasicInfo>
          <!-- 资料修改 -->
          <div class="info-modify">
            <el-form

                :model="ruleForm"
                status-icon
                :rules="rules"
                label-width="120px"
                size="large"
                :label-position="'left'"
                ref="form0"
            >
              <el-form-item :required="true" label="邮箱"  prop="email" >
                <el-input v-model="ruleForm.email" disabled />
              </el-form-item>
              <el-form-item :required="true" label="原密码" prop="passOld">
                <el-input v-model="ruleForm.passOld"  placeholder="请输入原密码" type="password" autocomplete="off" />
              </el-form-item>
              <el-form-item :required="true" label="新密码" prop="passNew">
                <el-input v-model="ruleForm.passNew" type="password" placeholder="请输入新密码"  autocomplete="off" />
              </el-form-item>
              <el-form-item  :required="true" label="确认密码"  prop="checkPass">
                <el-input
                    v-model="ruleForm.checkPass"
                    type="password"
                    autocomplete="off"
                    placeholder="请确认新密码"
                />
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
      <!-- 按钮 -->
      <div>
        <el-row class="change-psd-button">
          <el-col :span="8" >
            <el-button @click="submit" auto-insert-space class="record-submit">确认</el-button>
          </el-col>
          <el-col :span="1" >
            <el-button @click="cancel" auto-insert-space class="record-cancel">取消</el-button>
          </el-col>
        </el-row>
      </div>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import TopBar from "@/components/TopBar.vue"
import BasicInfo from "@/components/BasicInfo.vue"
import { ref, reactive } from 'vue'
import axios from "axios";
import type { FormInstance } from 'element-plus'
// import router from "@/router";
import {ElMessage} from "element-plus";
const ruleFormRef = ref<FormInstance>()
const value = ref('')
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

const validatePass = (rule:any, value:any, callback:any) => {
  //必须包含⼤⼩写字母、数字、特殊字符长度再9-16位之间
  var regex = new RegExp("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,19}");
  if (value === "") {
    callback(new Error("请输⼊密码"));
  } else if (value.length < 6 || value.length > 19) {
    callback(new Error("请输⼊6~19位密码"));
  } else if (!regex.test(value)) {
    callback(new Error("必须同时包含字母、数字和特殊字符其中三项"));
  } else {
    callback();
  }
};
//
// const validatePass = (rule: any, value: any, callback: any) => {
//   if (value === '') {
//     callback(new Error('请输入密码'))
//   } else {
//     if (ruleForm.checkPass !== '') {
//       if (!ruleFormRef.value) return
//       ruleFormRef.value.validateField('checkPass', () => null)
//     }
//     callback()
//   }
// }
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
  email:sessionStorage.getItem("email"),
  passOld: '',
  passNew:'',
  checkPass: '',
})

const rules = reactive({
  passOld: [{required:'true', message:"必填项不能为空",trigger: 'blur' },{ validator: validatePass, trigger: 'blur' }],
  passNew: [{required:'true', message:"必填项不能为空",trigger: 'blur' },{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{required:'true', message:"必填项不能为空",trigger: 'blur' },{ validator: validatePass2, trigger: 'blur' }],
})
var form0=ref("");

function submit() {
  form0.value.validate((valid) => {
    if (valid) {
      axios.post("/mtc/user/changePassword", {
        userEmail: ruleForm.email,
        userPasswordOld: ruleForm.passOld,
        userPasswordNew: ruleForm.passNew,
      }).then((Response) => {
        if (Response.data.code === 200) {
          ElMessage.success("修改成功")
        } else {
          ElMessage.error("修改失败")
        }
      })
    } else {
      ElMessage.error('数据类型错误')
      return false
    }
  })
}

function cancel() {
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

.change-psd-box{
  margin-left: 151px;
  margin-top: 151px;
  width: 1124px;
  height: 471px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}
:deep(.basic-form-button){
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
.basic-form-title{
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

.info-modify{
  margin-left: 570px;
  margin-top: -20px;
  width: 400px;
}
:deep(.info-bloodtype) {
  font-size: 15px;
}
:deep(.el-col-11) {
  max-width: 280px;
  flex: 0%;
}
:deep(.el-form-item){
  margin-bottom: 20px;
  width: 420px;
}
:deep(.el-form-item--large .el-form-item__label ){
  font-size: 20px;
}
:deep(.el-input__inner ){
  font-size: 18px;
}
:deep(.el-select .el-input__wrapper ){
  width: 300px;
}
:deep(.el-radio.el-radio--large .el-radio__label) {
  font-size: 18px;
}
:deep(.el-input--large .el-input__inner) {
  width: 248px;
}


.change-psd-button{
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
  left: 1px;
  top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}

.change-psd-title{
  margin-left: 40px;
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
.basic-form-box {
  margin-top: -100px;
}
</style>


