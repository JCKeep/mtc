<template>
  <el-form
      ref="registerForm"
      :model="registerUser"
      :rules="registerRules"
      class="registerForm sign-up-form">

    <el-form-item  label="用户名" prop="name" label-width="30%">

      <el-input  v-model="registerUser.name" placeholder="请输入用户名"/>
    </el-form-item>
    <el-form-item label="邮箱" prop="email" label-width="30%">

      <el-input v-model="registerUser.email" placeholder="请输入邮箱"/>
    </el-form-item>
    <el-form-item label="密码" prop="password" label-width="30%">
      <el-input v-model="registerUser.password" type="password" placeholder="请输入密码"/>
    </el-form-item>
    <el-form-item label="确认密码" prop="password2" label-width="30%">
      <el-input
          v-model="registerUser.password2"
          type="password"
          placeholder="Enter Password"
      />
    </el-form-item>
    <el-form-item label="验证码" prop="identify" label-width="30%">
      <div style="width: 55%">
          <el-input
              v-model="registerUser.identify"
              type="text"
              placeholder="请输入验证码"
          />
      </div>
      <el-button style="margin-left: 5%" @click="sendCode">发送验证码</el-button>
    </el-form-item>


    <el-form-item label-width="30%">
      <el-button @click="handleRegister" class="submit-btn">注册</el-button>
<!--      <el-button @click="handleRegister('registerForm')" class="submit-btn">注册</el-button>-->
    </el-form-item>


  </el-form>
</template>

<!--<script setup>-->
<!--import {reactive} from "vue";-->
<!--import axios from "axios";-->
<!--import { ref,defineProps } from 'vue'-->
<!--const input = ref('')-->
<!--import router from "@/router";-->
<!--const props = defineProps({-->
<!--    //接收registerUser和registerRules-->
<!--    registerUser: {-->
<!--      type: Object,-->
<!--      required: true,-->
<!--    },-->
<!--    registerRules: {-->
<!--      type: Object,-->
<!--      required: true,-->
<!--    }-->
<!--})-->

<!--var registerUser = reactive({-->
<!--    name:"",-->
<!--    email:"",-->
<!--    password:"",-->
<!--    password2:"",-->
<!--    identify:"",-->
<!--    code:"",-->
<!--})-->

<!--function f(){-->
<!--  // axios.get("/api/mtc/user/getcode", {params:{name:registerUser.name, email:registerUser.email,passwd:registerUser.password,identify:registerUser.identify}})-->
<!--  axios.get("/mtc/user/getcode", {params:{email:registerUser.email}})-->
<!--      .then(function (response){-->
<!--        registerUser.code = response.data-->
<!--      })-->
<!--}-->

<!--function handleRegister() {-->
<!--  console.log(registerUser.password,registerUser.password2)-->
<!--  axios.post("/mtc/user/register",-->
<!--      {name:registerUser.name,email:registerUser.email,passwd:registerUser.password,code:registerUser.code})-->
<!--      .then(function (response){-->
<!--        alert(response.data)-->
<!--        // console.log(registerUser.name,registerUser.email,registerUser.password,registerUser.code)-->
<!--        registerUser.code = response.data-->
<!--        // router.push('/MyHealth')-->
<!--      })-->
<!--}-->
<!--</script>-->

<script lang="ts"  >
import {ref, getCurrentInstance, reactive} from "vue";
import {useRouter} from 'vue-router';
import axios from '@/http'
import { ElMessage } from "element-plus";

const formLabelAlign = reactive({
  name: '糖糖用户',
  sex: '男',
  birthday: '',
  bloodType: '',
  height: '',
})
var registerForm=ref("");
export default {
  props: {
    //接收registerUser和registerRules
    registerUser: {
      type: Object,
      required: true,
    },
    registerRules: {
      type: Object,
      required: true,
    }
  },
  setup(props: any) {

    const router = useRouter();
    const form = reactive({
      userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
    })


    //申请发送验证码的方法
    const sendCode = (formName:string) => {
      axios.get(
          "/mtc/user/getcode",
          {params:{email:props.registerUser.email}}
    ).then((res:any) => {
      if(res.data.code===201){
        ElMessage.error("验证码发送失败，请检查您的邮箱输入是否正确");
      }else {
        // console.log(res.data);
        ElMessage.success("验证码发送成功");
      }
    }
)}
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

    //提交表单的验证方法
    const handleRegister = (formName: string) => {
      // console.log(11111)
      //     console.log('submit!');

      registerForm.value.validate((valid:string) => {
        if (valid) {
          axios.post(
                  "/mtc/user/register",
                  {name: props.registerUser.name, email:props.registerUser.email, passwd: props.registerUser.password, code: props.registerUser.identify}
          ).then((res: any) => {
            //注册成功
            //路由跳转
            if(res.data == true) {
              ElMessage.success("注册成功");
              sessionStorage.setItem("email", props.registerUser.email);
              //设置默认信息
              axios.post("/mtc/health/base", {
                email: props.registerUser.email,
                // name: "糖糖用户",
                // sex: "男",
                birthday: getDay(0),
                // bloodType: "其他",
                // height: 123
              })
              axios.post("/mtc/user/setPortrait",{email:props.registerUser.email,address:form.userPortrait
              })
              router.push("/Myhealth")
            }
            else{
              // console.log(res.data);
              ElMessage.error("注册失败");
            }

          })
        // } else {
        //   console.log('error submit!')
        //   return false
        // }
      // });
    }else {
          ElMessage.error('数据类型错误或未填写必填项')
          return false
        }
      })
    };

    return {
      handleRegister,
      sendCode
    };
  }
}

</script>

<style scoped>

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
  width: 100%;
  color: #f8f8f8;
  height: 30px;
  background-color: #55a684;
  font-size: 15px;
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

</style>