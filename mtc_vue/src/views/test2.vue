<template>
  <!--  <scale-box :width="1920" :height="1080">-->
  <div class="container" :class="{'sign-up-mode':signUpMode}">
    <!-- form表单容器 -->
    <div class="forms-container">
      <div class="signin-signup">
        <!-- 登录 -->
        <!--        <login-form :loginUser="loginUser" :rules="rules"></login-form>-->
        <el-form
            :model="loginForm"
            :rules="loginRules"
            label-width="200px"
            size="large"
            class="loginForm sign-in-form"
        >
          <el-form-item  label="邮箱" prop="email" label-width="25%">

            <el-input class="item-font-size" v-model="loginForm.email" placeholder="请输入邮箱"/>
          </el-form-item>
          <el-form-item label="密码" prop="password" label-width="25%">
            <el-input
                v-model="loginForm.password"
                type="password"
                placeholder="请输入密码"
            />
          </el-form-item>
          <!-- 找回密码 -->

          <el-form-item label-width="9%" >
            <div class="tiparea flex justify-space-between mb-4 flex-wrap gap-4">
              <p style="font-size: 20px">忘记密码？ <el-button style="font-size: 20px;margin-top: -3px" type="success" @click="goForgetPs" link>立即找回</el-button></p>
            </div>
            <el-button  style="width: 120px" @click="handleLogin()" class="submit-btn">登录</el-button>
          </el-form-item>
        </el-form>
        <!-- 注册 -->
        <el-form
            :model="registerForm"
            :rules="registerRules"
            class="registerForm sign-up-form">

          <el-form-item  label="用户名" prop="name" label-width="30%">

            <el-input  v-model="registerForm.name" placeholder="请输入用户名"/>
          </el-form-item>
          <el-form-item label="邮箱" prop="email" label-width="30%">

            <el-input v-model="registerForm.email" placeholder="请输入邮箱"/>
          </el-form-item>
          <el-form-item label="密码" prop="password" label-width="30%">
            <el-input v-model="registerForm.password" type="password" placeholder="请输入密码"/>
          </el-form-item>
          <el-form-item label="确认密码" prop="password2" label-width="30%">
            <el-input
                v-model="registerForm.password2"
                type="password"
                placeholder="Enter Password"
            />
          </el-form-item>
          <el-form-item label="验证码" prop="identify" label-width="30%">
            <div style="width: 65%">
              <el-input
                  v-model="registerForm.identify"
                  type="text"
                  placeholder="请输入验证码"
              />
            </div>
            <el-button style="margin-left: 10px" @click="sendCodeRegister">发送验证码</el-button>
          </el-form-item>


          <el-form-item >
            <el-button @click="handleRegister" style="margin-left: 135px;width: 327px" class="submit-btn">注册</el-button>
            <!--      <el-button @click="handleRegister('registerForm')" class="submit-btn">注册</el-button>-->
          </el-form-item>


        </el-form>
      </div>
    </div>
    <!-- 左右切换动画 -->
    <div class="panels-container">
      <div class="panel left-panel">
        <div class="content">
          <h3>木糖醇</h3>
          <p>专为血糖病人打造的健康管理平台。</p>
          <p>在木糖纯，你可以记录你的健康指标、用药规格和饮食方案，</p>
          <p>还可以和医生、糖友交流，互相见证健康生活。</p>
          <button @click="signUpMode = !signUpMode" class="btn transparent">
            注册
          </button>
        </div>
        <img src="@/assets/img/log1.svg" class="image" alt=""/>
      </div>

      <div class="panel right-panel">
        <div class="content">
          <h3>木糖醇</h3>
          <p>专为血糖病人打造的健康管理平台。</p>
          <p>在木糖纯，你可以记录你的健康指标、用药规格和饮食方案，</p>
          <p>还可以和医生、糖友交流，互相见证健康生活。</p>
          <button @click="signUpMode = !signUpMode" class="btn transparent">
            登录
          </button>
        </div>
        <img src="@/assets/img/register1.svg" class="image" alt=""/>
      </div>
    </div>
  </div>
  <!--  </scale-box>-->
</template>


<script setup lang="ts">
// import ScaleBox from 'scale-box'
// import 'scale-box/dist/style.css'
import {ref, getCurrentInstance, reactive} from "vue";
import {loginUser, rules} from "@/utils/loginValidators";
import axios from "@/http";
import {ElMessage, ElNotification} from "element-plus";
// import router from "@/router";
// import {registerUser, registerRules} from "@/utils/registerValidators";
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

const signUpMode = ref<boolean>(false);

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

const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value !== registerForm.password2) {
    callback(new Error("两次输入的密码不一致"))
  } else {
    callback()
  }
}

const goForgetPs = () => {
  router.push({path:"/login/ForgetPasswd"})
}

//登录信息
const loginForm = reactive({
  email:"",
  password:"",
})

const loginRules = reactive({
  email:[{required: true, message: '请输入邮箱', trigger: 'blur'},
    {type: 'email', message: '邮箱格式有误，请重新输入', trigger: 'blur'}],
  password: [{required:true, message:"请输入密码",trigger: 'blur' },
    { validator: validatePass, trigger: 'blur' }],
})

//注册信息
const registerForm = reactive({
  name:"",
  email:"",
  password:"",
  password2:"",
  identify:"",
  code:"",
})

const registerRules = reactive({
  name:[{required: true, message: '必填项不能为空', trigger: 'blur'}],
  email:[{required: true, message: '请输入邮箱', trigger: 'blur'},
    {type: 'email', message: '邮箱格式有误，请重新输入', trigger: 'blur'}],
  password: [{required:true, message:"请输入密码",trigger: 'blur' },
    { validator: validatePass, trigger: 'blur' }],
  password2: [{required:true, message:"请再次输入密码",trigger: 'blur' },
    { validator: validatePass, trigger: 'blur' },
    { validator: validatePass2, trigger: 'blur' }],
  identify:[{required:true, message:"请输入验证码",trigger: 'blur' },
    { validator: checkCode, trigger: 'blur' }]
})

function checkCode(rule: any, value: any, callback: any){
  if (registerForm.identify != registerForm.code) {
    callback(new Error("验证码有误，请重新输入"))
  } else {
    callback()
  }
}
//获取今日天数
function doHandleMonth(month:any) {
  var m = month;
  if (month.toString().length == 1) {
    m = "0" + month;
  }
  return m;
}


function getDay(day:any) {
  var today = new Date();
  var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
  today.setTime(targetday_milliseconds); //注意，这行是关键代码

  var tYear = today.getFullYear();
  var tMonth = today.getMonth();
  var tDate = today.getDate();
  tMonth = doHandleMonth(tMonth + 1);
  tDate = doHandleMonth(tDate);
  // alert(tYear + "-" + tMonth + "-" + tDate)
  return tYear + "-" + tMonth + "-" + tDate;
}


const portrait = reactive({
  userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
})


function sendCodeRegister() {
  axios.get(
      "/mtc/user/getcode",
      {params:{email:registerForm.email}}
  ).then((res:any) => {
    if(res.data.code===201){
      ElMessage.error("验证码发送失败，请检查您的邮箱输入是否正确");
    }else {
      console.log(registerForm.code);
      registerForm.code = res.data;
      console.log(registerForm.code);
      ElMessage.success("验证码发送成功");
    }
  })
}


function handleLogin() {
  axios.get("/mtc/user/login",{params:{email: loginForm.email, passwd: loginForm.password }})
      .then((res:any) => {
        console.log(res.data);
        //页面跳转
        if (res.data === true){
          sessionStorage.setItem("email",loginForm.email)
          // ElMessage.success("登录成功");
          ElNotification.success({
            title: '登陆成功',
            message: '糖糖助手提醒您记录今日的健康数据',
            offset: 100,
            showClose: false,
          })
          router.push("/MyHealth");
        }
        else {
          ElMessage.error("账户或密码错误，请检查您的输入");
        }
      })
}

function handleRegister() {
  axios.post(
      "/mtc/user/register",
      {name: registerForm.name, email:registerForm.email, passwd: registerForm.password, code: registerForm.identify}
  ).then((res: any) => {
    //注册成功
    //路由跳转
    if (res.data == true) {
      // ElMessage.success("注册成功");
      ElNotification.success({
        title: '注册并登陆成功',
        message: '糖糖助手提醒您记录今日的健康数据',
        offset: 100,
        showClose: false,
      })
      sessionStorage.setItem("email", registerForm.email);
      //设置默认信息
      axios.post("/mtc/health/base", {
        email: registerForm.email,
        // name: "糖糖用户",
        // sex: "男",
        birthday: getDay(0),
        // bloodType: "其他",
        // height: 123
      })
      axios.post("/mtc/user/setPortrait", {
        email: registerForm.email, address: portrait.userPortrait
      })
      router.push("/Myhealth")
    } else {
      console.log(res.data);
      ElMessage.error("注册失败");
    }
  })
}

</script>


<style scoped>
.container {
  position: relative;
  /*width: 1920px;*/
  /*width: 100%;*/
  height: 100vh;
  background-color: #ffffff;
  min-height: 100vh;
  overflow: hidden;
}

.forms-container {
  position: absolute;
  width: 70%;
  height: 100%;
  top: 0;
  left: 0;
}

.signin-signup {
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  left: 75%;
  width: 44%;
  transition: 1s 0.7s ease-in-out;
  display: grid;
  grid-template-columns: 1fr;
  z-index: 5;
}

/* 左右切换动画 */
.social-text {
  padding: 0.7rem 0;
  font-size: 1rem;
}

.social-media {
  display: flex;
  justify-content: center;
}

.social-icon {
  height: 46px;
  width: 46px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 0.45rem;
  color: #333;
  border-radius: 50%;
  border: 1px solid #333;
  text-decoration: none;
  font-size: 1.1rem;
  transition: 0.3s;
}

.social-icon:hover {
  color: #55a684;
  border-color: #55a684;
}

.btn {
  width: 150px;
  background-color: #55a684;
  border: none;
  outline: none;
  height: 49px;
  border-radius: 49px;
  color: #fff;
  text-transform: uppercase;
  font-weight: 600;
  margin: 10px 0;
  cursor: pointer;
  transition: 0.5s;
}

.btn:hover {
  background-color: #dbece5;
}

.panels-container {
  position: absolute;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
}

.container:before {
  content: "";
  position: absolute;
  height: 2000px;
  width: 2000px;
  top: -10%;
  right: 48%;
  transform: translateY(-50%);
  background-image: linear-gradient(-45deg, #55a684 0%, #dbece5 100%);
  transition: 1.8s ease-in-out;
  border-radius: 50%;
  z-index: 6;
}

.image {
  width: 100%;
  top: 10%;
  transition: transform 1.1s ease-in-out;
  transition-delay: 0.4s;
}


.panel {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  text-align: center;
  z-index: 6;
}

.left-panel {
  /*align-items: flex-end;*/
  pointer-events: all;
  padding: 3rem 17% 2rem 12%;
}

.right-panel {
  /*align-items: flex-start;*/
  pointer-events: none;
  padding: 3rem 12% 2rem 17%;
}

.panel .content {
  color: #fff;
  transition: transform 0.9s ease-in-out;
  transition-delay: 0.6s;
}

.panel h3 {
  font-weight: 600;
  line-height: 1;
  font-size: 1.5rem;
}

.panel p {
  font-size: 0.95rem;
  padding: 0.7rem 0;
}

.btn.transparent {
  margin: 0;
  background: none;
  border: 2px solid #fff;
  width: 130px;
  height: 41px;
  font-weight: 600;
  font-size: 0.8rem;
}

.right-panel .image,
.right-panel .content {
  transform: translateX(800px);
}

/* ANIMATION */

.container.sign-up-mode:before {
  transform: translate(100%, -50%);
  right: 52%;
}

.container.sign-up-mode .left-panel .image,
.container.sign-up-mode .left-panel .content {
  transform: translateX(-800px);
}

.container.sign-up-mode .signin-signup {
  left: 25%;
}

.container.sign-up-mode form.sign-up-form {
  opacity: 1;
  z-index: 2;
}

.container.sign-up-mode form.sign-in-form {
  opacity: 0;
  z-index: 1;
}


.container.sign-up-mode .right-panel .image,
.container.sign-up-mode .right-panel .content {
  transform: translateX(0%);
}

.container.sign-up-mode .left-panel {
  pointer-events: none;
}

.container.sign-up-mode .right-panel {
  pointer-events: all;
}

@media (max-width: 870px) {
  .container {
    min-height: 800px;
    height: 100vh;
  }

  .signin-signup {
    width: 100%;
    top: 95%;
    transform: translate(-50%, -100%);
    transition: 1s 0.8s ease-in-out;
  }

  .signin-signup,
  .container.sign-up-mode .signin-signup {
    left: 50%;
  }

  .panels-container {
    grid-template-columns: 1fr;
    grid-template-rows: 1fr 2fr 1fr;
  }

  .panel {
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    padding: 2.5rem 8%;
    grid-column: 1 / 2;
  }

  .right-panel {
    grid-row: 3 / 4;
  }

  .left-panel {
    grid-row: 1 / 2;
  }

  .image {
    width: 200px;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.6s;
  }

  .panel .content {
    padding-right: 15%;
    transition: transform 0.9s ease-in-out;
    transition-delay: 0.8s;
  }

  .panel h3 {
    font-size: 1.2rem;
  }

  .panel p {
    font-size: 0.7rem;
    padding: 0.5rem 0;
  }

  .btn.transparent {
    width: 110px;
    height: 35px;
    font-size: 0.7rem;
  }

  .container:before {
    width: 1500px;
    height: 1500px;
    transform: translateX(-50%);
    left: 30%;
    bottom: 68%;
    right: initial;
    top: initial;
    transition: 2s ease-in-out;
  }

  .container.sign-up-mode:before {
    transform: translate(-50%, 100%);
    bottom: 32%;
    right: initial;
  }

  .container.sign-up-mode .left-panel .image,
  .container.sign-up-mode .left-panel .content {
    transform: translateY(-300px);
  }

  .container.sign-up-mode .right-panel .image,
  .container.sign-up-mode .right-panel .content {
    transform: translateY(0px);
  }

  .right-panel .image,
  .right-panel .content {
    transform: translateY(300px);
  }

  .container.sign-up-mode .signin-signup {
    top: 5%;
    transform: translate(-50%, 0);
  }
}

@media (max-width: 570px) {
  form {
    padding: 0 1.5rem;
  }

  .image {
    display: none;
  }

  .panel .content {
    padding: 0.5rem 1rem;
  }

  .container {
    padding: 1.5rem;
  }

  .container:before {
    bottom: 72%;
    left: 50%;
  }
}

/* 控制login & register显示 */
form {
  padding: 0rem 5rem;
  transition: all 0.2s 0.7s;
  overflow: hidden;
}

form.sign-in-form {
  z-index: 2;
}

form.sign-up-form {
  opacity: 0;
  z-index: 1;
}


/*loginForm*/
.loginForm {
  top: 300px;
  left: 550px;
  padding: 40px 40px 20px 20px;
  border-radius: 15px;
  /*box-shadow: #ccc;*/
  /*top: 10%;*/
  background: #FFFFFF;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
  font-size: 200px;
}

/*登录表单字体大小修改*/



.submit-btn[data-v-ba684ca4] {
  width: 30%;
  margin-left: 11%;
  height: 25px;
  border-radius: 5px;
  margin-top: 0px;
  font-size: 15px;
  color: #f8f8f8;
  background-color: #55a684;
}

/*输入框里面的字离icon的距离*/
.el-input__inner {
  margin-left: 1px;
}



/*register*/
.registerForm {
  margin-top: 20px;
  margin-right: -80px;
  /*background-color: #f8f8f8;*/
  padding: 40px 40px 20px 20px;
  border-radius: 15px;
  /*box-shadow: #ccc;*/
  /*top: 10%;*/
  background: #FFFFFF;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.submit-btn {
  /*width: 30%;*/
  color: #f8f8f8;
  height: 30px;
  background-color: #55a684;
  font-size: 15px;
  margin-left: 10%;
}

.submit-btn[data-v-8e17b12f] {
  width: 100%;
  color: #f8f8f8;
  background-color: #55a684;
}

/*输入框里面的字离icon的距离*/
.el-input__inner {
  margin-left: 1px;
}
/*.el-input__wrapper {*/
/*  padding: 0px 32px;*/
/*}*/


:deep(.el-form-item--large .el-form-item__label ){
  font-size: 20px;
  margin-top: 0px;
}
:deep(.el-input__inner ){
  font-size: 20px;
}

:deep(.el-form-item__label ){
  display: inline-flex;
  justify-content: flex-end;
  align-items: flex-start;
  flex: 0 0 auto;
  font-size: 20px;

  color: var(--el-text-color-regular);
  height: 32px;
  line-height: 32px;
  padding: 0 12px 0 0;
  box-sizing: border-box;
}

:deep(.el-input--large .el-input__inner) {
  height: 40px;
}

:deep(.submit-btn[data-v-6e3cab97] ){
  width: 46%;
  color: #f8f8f8;
  height: 40px;
  background-color: #55a684;
  font-size: 20px;
}

.loginForm[data-v-6e3cab97] {
  top: 200px;
  left: 400px;
  padding: 40px 40px 20px 20px;
  border-radius: 15px;
  /* top: 10%; */
  background: #FFFFFF;
  box-shadow: 0px 2px 4px 0px rgb(0 0 0 / 25%);
  font-size: 200px;
}
</style>>