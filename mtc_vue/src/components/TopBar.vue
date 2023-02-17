<template>
  <el-row >

    <el-col :span="1" style="margin-left: -15px"><img src="@/assets/img/topBarIcon.svg" class="image" alt="" sizes="100px"/></el-col>

    <el-col :span="3" style="margin-left: 25px">
      <div class="top-bar-title">
        <h>木糖纯</h>
      </div>
    </el-col>

    <!--          <div class="top-bar-button" style="justify-content: space-between; margin:8px 0px 0px 350px ">-->
    <el-col @click="Myhealth" :span="3"><el-button type="" link style="font-size: 20px; padding-top: 24px">健康日志</el-button></el-col>
    <el-col @click="dietView" :span="3"><el-button type="" link style="font-size: 20px; padding-top: 24px">饮食管家</el-button></el-col>
    <el-col @click="communityView" :span="10"><el-button type="" link style="font-size: 20px; padding-top: 24px">木糖社区</el-button></el-col>




    <!--          </div>-->
    <el-col :span="2" style="margin-right: -15px; padding-top: 10px ">
      <div class="top-bar-head">
        <div class="block">
          <el-image
              shape="square"
              style="width: 50px; height: 50px; border-radius: 5px;"
              :src="info.avatarUrl"
              fit="cover"
          />
        </div>
      </div>
    </el-col>
    <el-col :span="2" style="padding-top: 20px">
      <el-dropdown>
              <span class="el-dropdown-link" style="font-size: 20px">
                USER<el-icon class="el-icon--right"><arrow-down /></el-icon>
              </span>

        <template #dropdown class="top-bar-menu" style="margin:8px 0px 0px 350px;">
          <el-dropdown-menu>
            <el-dropdown-item @click="centerDialogVisible1 = true"><el-icon><Warning /></el-icon>注销账户</el-dropdown-item>
            <el-dropdown-item @click="UserPor"><el-icon><User /></el-icon>用户画像</el-dropdown-item>
            <el-dropdown-item @click="MyAvatar"><el-icon><PictureRounded /></el-icon>我的头像</el-dropdown-item>
            <el-dropdown-item @click="BasicInfor"><el-icon><PieChart /></el-icon>基本资料</el-dropdown-item>
            <el-dropdown-item @click="DoctorCertification"><el-icon><Avatar /></el-icon>医生认证</el-dropdown-item>
            <el-dropdown-item @click="ChangePas"><el-icon><EditPen /></el-icon>修改密码</el-dropdown-item>
            <el-dropdown-item @click="centerDialogVisible = true"><el-icon><SwitchButton /></el-icon>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </el-col>
  </el-row>

  <!--  注销对话框-->
  <div >
    <el-dialog center
               v-model="centerDialogVisible1" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您正在注销账户！
    </span>
      <template #footer>
      <span class="dialog-footer">
        <el-button class="dialog-button" :icon="User"
                   plain type="danger"
                   @click="centerDialogVisible2 = true"
        >
          确认注销
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="success"
                   @click="centerDialogVisible1 = false">
          取消注销
        </el-button>
      </span>
      </template>
    </el-dialog>

    <el-dialog center
               v-model="centerDialogVisible2" draggable
               align-center width="30%"
               style="--el-dialog-border-radius: 15px;"
    >
      <span class="logout-confirm-context">
        您正在注销账户，请确认您的信息！
      </span>
      <template #footer>
        <span class="dialog-footer">
          <!-- 信息确认 -->
          <div class="info-confirm">
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
                <el-input v-model="ruleForm.email" disabled :prefix-icon="Message" />
              </el-form-item>
              <el-form-item label="验证码" :required="true" prop="identify">
                <el-input style="width: 175px" :prefix-icon="User" class="farm-input" v-model="ruleForm.identify"  autocomplete="off" placeholder="请输入验证码"/>
                <el-button @click="sendCode" class="send-code" auto-insert-space>发送验证码</el-button>
              </el-form-item>
            </el-form>
          </div>
          <!-- 按钮 -->
          <div>
            <el-row class="change-psd-button">
            <el-col :span="10" >
              <el-button @click="confirmLogout" auto-insert-space class="record-submit">确认</el-button>
            </el-col>
            <el-col :span="1" >
              <el-button @click="centerDialogVisible2 = false" auto-insert-space class="record-cancel">取消</el-button>
            </el-col>
            </el-row>
          </div>

        </span>
      </template>
    </el-dialog>
  </div>

  <!--  退出登录 -->
  <div class="exit">
    <el-dialog center
               v-model="centerDialogVisible" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您将退出系统！
    </span>
      <template #footer>
      <span class="dialog-footer">
        <el-button class="dialog-button" :icon="SwitchButton"
                   plain type="danger"
                   @click="confirmExit">
          立即退出
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="success"
                   @click="centerDialogVisible = false">
          返回系统
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>

@click="confirmLogout"

<script lang="ts" setup>
// import avatarURL from '../assets/img/headPicture.jpg'
import { reactive, toRefs, onBeforeMount, ref } from 'vue'
import { Back, User, Message, SwitchButton, Warning,Avatar ,PieChart, EditPen, PictureRounded } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';
// import router from '../router';
import axios from 'axios'
import type { FormInstance } from 'element-plus'
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

const ruleFormRef = ref<FormInstance>()
const value = ref('')
var form0 = ref("");
const centerDialogVisible2 = ref(false)

// const state = reactive({
//   avatarUrl:
//       '../assets/img/headPicture.jpg',
// })
// const email = ref();
// const avatarUrl = ref();
// const { avatarUrl }= toRefs(avatarUrl)
// const { email }= toRefs(email)

const ruleForm = reactive({
  email:sessionStorage.getItem("email"),
  identify: '',
})

const rules = reactive({
  identify: [{required:'true', message:'请输入验证码', trigger: 'blur' }],
})

const confirmLogout = () => {
  axios.get("/mtc/user/delete",{params:{
      email:info.email}
  }).then(function (res){
    if (res.data.code === 200){
      sessionStorage.removeItem("email")
      ElMessage.success('您已注销账户！即将返回首页');
      router.push({ path: "/login" });
    }
    else {
      ElMessage.error('注销失败，请检查您的网络');
    }
  })

}

const confirmExit = () => {
  sessionStorage.removeItem("email")
  ElMessage.success('您已退出登录！即将返回首页');
  router.push({ path: "/login" });
}

function sendCode() {
  axios.get("/mtc/user/getcode2",
      {params:{email:ruleForm.email}}).then((res:any)=>{
    ElMessage.success("验证码发送成功")
  })
}

//注销对话框
const centerDialogVisible1 = ref(false)
const centerDialogVisible = ref(false)
const info = reactive({
  email: sessionStorage.getItem("email"),
  userId:'',
  avatarUrl: 'http://114.132.226.110/file/IMG_5267.JPG',
  userIdenty:'',
  doctorId:'',
})

// const { avatarUrl }= toRefs(info.avatarUrl)
function getAvator() {
  axios.get('/mtc/user/getuserinfo',{params:{email:info.email}})
      .then(function (res){
        info.avatarUrl = res.data.object.userPortrait;
        // console.log("hello")

        // console.log(res.data.object.userPortrait)
        // avatarUrl.value=res.data.object.userPortrait
      })
}

function getId() {
  axios.get('/mtc/user/id',{params:{email: info.email}})
      .then(function (res){
        // console.log(res.data.object)
        info.userId = res.data.object;
        sessionStorage.setItem("userId",info.userId);
        getUserIdenty();
      })


}
function getUserIdenty(){
  axios.get('/mtc/user/type',{params:{userId: info.userId}})
      .then(function (res){
        // console.log(res.data.object)
        info.userIdenty = res.data.object;
        sessionStorage.setItem("userIdenty",info.userIdenty)
        if(info.userIdenty=="1"){
          getDoctorId();
        }
      })
}

function getDoctorId(){
  axios.get('/mtc/community/binding/doctorId',{params:{userId: info.userId}})
      .then(function (res){
        // console.log(res.data.object)
        info.doctorId = res.data.object;
        sessionStorage.setItem("doctorId",info.doctorId)
      })
}

onBeforeMount(() => {
  getAvator(),
      getId()
})

function Myhealth() {
  router.push({ path: "/Myhealth" })
}

function ChangePas() {
  router.push({ path: "/PerCenter/ChangePas" })
}

function MyAvatar() {
  router.push({ path: "/PerCenter/MyAvatar" })
}

function UserPor() {
  router.push({ path: "/PerCenter/UserPor" })
}

function BasicInfor() {
  router.push({ path: "/PerCenter/BasicInfor" })
}

function DoctorCertification() {
  router.push({ path: "/PerCenter/DoctorCertification" })
}

function dietView() {
  router.push({ name: "dietindex" , query: {userId:info.userId}})
  //存userId
}

function communityView() {
  router.push({ path: "/Community" })
  //存userId
}


</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}

.logout-text {
  justify-content: center;
  font-size: 28px;
  margin-left: 160px;
}
.logout-confirm-context {
  justify-content: center;
  font-size: 28px;
  margin-left: 40px;
}

.dialog-button {
  margin-top: 10px;
  margin-left: 20px;
  margin-bottom: 20px;
  font-size: 20px;
  width: 200px;
  height: 50px;
}

.top-bar-title{
  /*width: 161.33px;*/
  /*height: 33.33px;*/
  /*margin: -13px -4px -51px 240px;*/
  padding: 0px 0px 0px 0px;
  opacity: 5;
  font-size: 23px;
  font-weight: 400;
  letter-spacing: 4px;
  line-height: 7px;
  color: rgba(0, 0, 0, 1);
  padding-top: 30px
  /*text-align: left;*/
  /*vertical-align: top;*/
}
.image {
  width: 34.67px;
  height: 34.67px;
  margin: 20px 2px 2px 28px;
  padding: 0px 0px 0px 0px;
  size: 30px;
}

.el-button.is-link:focus, .el-button.is-link:hover {
  color: #55a684;
}

.info-confirm{
  margin-left: 30px;
  margin-top: 5px;
  width: 450px;
}

:deep(.el-col-11) {
  max-width: 280px;
  flex: 0%;
}
:deep(.el-form-item){
  margin-bottom: 20px;
  width: 450px;
}
:deep(.el-form-item--large .el-form-item__label ){
  font-size: 20px;
}
:deep(.el-input__inner ){
  font-size: 18px;
}

:deep(.el-input--large .el-input__inner) {
  width: 0px;
}

.change-psd-button{
  margin-top: 45px;
  margin-left: 150px;
  margin-bottom: 25px;
}
.record-cancel{
  width: 155px;
  height: 42px;
  opacity: 1;
  background: rgb(255, 255, 255);
  color: #414841;
  font-size: 18px;
  margin-left: 12px;
}

.record-submit{
  width: 155px;
  height: 42px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
  font-size: 18px;
}
.send-code {
  font-size: 16px;
  margin-left: 5px;
}

</style>