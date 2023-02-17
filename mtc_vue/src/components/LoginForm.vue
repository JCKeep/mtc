<template>
        <el-form
            ref="loginForm"
            :model="loginUser"
            :rules="rules"
            label-width="120px"
            class="loginForm">
          <el-form-item label="邮箱" prop="email" label-width="20%">

            <el-input v-model="loginUser.email" placeholder="Enter Email"/>
          </el-form-item>
          <el-form-item label="密码" prop="password" label-width="20%">

            <el-input
                v-model="loginUser.password"
                type="password"
                placeholder="Enter Password"
            />
          </el-form-item>
          <!-- 找回密码 -->

          <el-form-item label-width="30%" >
            <div class="tiparea flex justify-space-between mb-4 flex-wrap gap-4">
              <p>忘记密码？ <el-button  type="success" @click="goForgetPs" link>立即找回</el-button></p>
            </div>
            <el-button  @click="handleLogin('loginForm')" class="submit-btn">登录</el-button>
<!--            <el-button type="primary" @click="f" class="submit-btn">提交</el-button>-->
          </el-form-item>
        </el-form>

</template>
<!--<script setup>-->

<!--import {reactive} from "vue";-->
<!--import axios from "axios";-->
<!--import router from "@/router";-->
<!--import {useRouter} from "vue-router"-->
<!--var r=reactive({-->
<!--  loginUser:{-->
<!--    email:"",-->
<!--    password:""-->
<!--  }-->
<!--})-->

<!--function f(){-->
<!--  axios.get("/mtc/user/login", {params:{email:r.loginUser.email,passwd:r.loginUser.password}})-->
<!--}-->

<!--</script>-->

<script lang="ts">
import {ref,getCurrentInstance} from "vue";
import {loginUser, rules} from "@/utils/loginValidators";
import router from "@/router";
import {useRouter} from "vue-router";
import axios from '@/http'
import {ElMessage} from "element-plus";


export default {
  props: {
    loginUser:{
      type:Object,
      require:true
    },
    rules:{
      type:Object,
      require: true,
    }
  },


setup(props:any){
    //@ts-ignore忽略类型检测
    const{ctx}=getCurrentInstance();
    const router = useRouter();
    // 触发登录方法

    const goForgetPs = () => {
      router.push({path:"/login/ForgetPasswd"})
    }
    const handleLogin = (formName:string) =>{
      // console.log(formName)
      // console.log(props.loginUser.email);
      // ctx.$refs[formName].validate((valid:boolean) => {
      //   if (valid) {
      //     console.log('here')
          axios.get("/mtc/user/login",{params:{email: props.loginUser.email, passwd: props.loginUser.password }})
              .then((res:any) => {
                console.log(res.data);
                //页面跳转
                if (res.data === true){
                  sessionStorage.setItem("email",props.loginUser.email)
                  ElMessage.success("登录成功");
                  router.push("/MyHealth");
                }
                else {
                  ElMessage.error("账户或密码错误，请检查您的输入");
                }
              })

        // }
        // else {
        //   ElMessage.error("账户或密码错误，请检查您的输入");
        //   return false
        // }
      // });
    };
    return {
      handleLogin,
      goForgetPs
    };
  },
}

</script>

<style scoped>

/*form*/
.loginForm {
  top: 300px;
  left: 550px;
  padding: 40px 40px 20px 20px;
  border-radius: 15px;
  /*box-shadow: #ccc;*/
  /*top: 10%;*/
  background: #FFFFFF;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.submit-btn {
  width: 30%;
  margin-left: 11%;
  /*height: 70px;*/
  border-radius: 5px;
  margin-top: 0px;
  /*font-size: 24px;*/
  color: #f8f8f8;
  background-color: #55a684;
}
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

</style>