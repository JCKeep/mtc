<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>
      <!-- 基本资料框 -->
      <div class="basic-form-box">
        <!-- 文字提示 -->
        <h class="basic-form-title">基本资料</h>
        <BasicInfo></BasicInfo>
        <!-- 资料修改 -->
        <div class="info-modify">
          <el-form
              :rules="rules"
              ref="form0"
              :model="formLabelAlign"
              label-width="140px" size="large"
              :label-position="'left'">
            <el-form-item label="邮箱账户" prop="email">
              <el-input v-model="formLabelAlign.email" style="width: 320px;" placeholder="请输入邮箱"
                        disabled/>
            </el-form-item>

            <el-form-item  label="用户名" prop="name">
              <el-input v-model="formLabelAlign.name" style="width: 320px" placeholder="请输入昵称"/>
            </el-form-item>

            <el-form-item :required="true" label="性别" prop="sex">
              <el-radio-group v-model="formLabelAlign.sex">
                <el-radio label="男"/>
                <el-radio label="女"/>
              </el-radio-group>
            </el-form-item>

            <el-form-item :required="true" label="出生日期" prop="birthday">
                <el-date-picker
                    v-model="formLabelAlign.birthday"
                    type="date"
                    placeholder="请选择生日"
                    style="width: 320px"
                    value-format="YYYY-MM-DD"
                />
            </el-form-item >
            <el-form-item :required="true" label="血型" prop="bloodType">
              <el-select v-model="formLabelAlign.bloodType" style="width: 320px" placeholder="请选择血型">
                <el-option class="info-bloodtype"
                           v-for="item in options"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item :required="true" label="身高" prop="height">
              <el-input v-model="formLabelAlign.height" style="width: 320px" placeholder="请输入身高"><template #append>cm</template></el-input>
            </el-form-item>
          </el-form>
        </div>
      </div>
      <!-- 按钮 -->
      <div>
        <el-row class="basic-form-button">
          <el-col :span="8">
            <el-button auto-insert-space class="record-submit" @click="onSubmit">修改</el-button>
          </el-col>
          <el-col :span="1">
            <el-button @click="returnHealth" auto-insert-space class="record-cancel">取消</el-button>
          </el-col>
        </el-row>
      </div>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import TopBar from "@/components/TopBar.vue"
import BasicInfo from "@/components/BasicInfo.vue"
import {ref, reactive, onMounted} from 'vue'
import axios from "axios";
import {ElMessage} from "element-plus";
// import router from "@/router";
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

const value = ref('')

const options = [
  {
    value: 'A型',
    label: 'A型',
  },
  {
    value: 'B型',
    label: 'B型',
  },
  {
    value: 'O型',
    label: 'O型',
  },
  {
    value: 'AB型',
    label: 'AB型',
  },
  {
    value: 'RH型',
    label: 'RH型',
  },
  {
    value: '其他',
    label: '其他',
  },
]
// do not use same name with ref
const formLabelAlign = reactive({
  name: '',
  email: sessionStorage.getItem("email"),
  sex: '',
  birthday: '',
  bloodType: '',
  height: '',
})

const validateNumber = (rule:any, value:any, callback:any) => {
  if (value === "") {
    callback(new Error("必填项不能为空"));
  } else if (value.length < 1 || value.length > 20) {
    callback(new Error("用户名长度为1-20位"));
  }else {
    callback();
  }
};

const validateHight = (rule:any, value:any, callback:any) => {
  if (value === "") {
    callback(new Error("必填项不能为空"));
  } else if (value.length < 1 || value.length > 3) {
    callback(new Error("有效长度为3位数字"));
  }else {
    callback();
  }
};

const checknumber = (rule: any, value:any, callback:any)=>{
  var regex = new RegExp("(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,19}");

  if (!value) {
    return callback(new Error('必填项不能为空'))
  }
  // if (regex.test(value)) {
  //   callback(new Error('请输入数字'))
  // }
  else {
    callback()
  }
}

const rules = reactive({
  email: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  // name: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  name: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {
    pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]{1,15}$/,
    message: "请输入昵称，长度为1-15位，仅支持中文英文数字",
    trigger: 'blur'
  }],
  height: [{required: 'true', message: '必填项不能为空', trigger: 'blur'},{ pattern: /^(?=.*[0-9])[0-9 _]{1,3}$/, message: "请输入1-3位数字", trigger: 'blur' }],
  sex: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  birthday: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  bloodType: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
})
var form0=ref("");

const onSubmit = () => {
  form0.value.validate((valid:string) => {
    if (valid) {
      axios.post("/mtc/health/base", {
        email: formLabelAlign.email,
        name: formLabelAlign.name,
        sex: formLabelAlign.sex,
        birthday: formLabelAlign.birthday,
        bloodType: formLabelAlign.bloodType,
        height: formLabelAlign.height
      })
          .then((Response) => {
            if (Response.data.code === 200) {
              ElMessage.success("修改成功")
            } else {
              ElMessage.error("修改失败")
              // router.push({ path: '/user' })
            }
          })
    } else {
      ElMessage.error('数据类型错误或未填写必填项')
      return false
    }
  })
}


function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo", {
    params: {
      email: formLabelAlign.email
    }
  }).then(function (res) {
    const data = res.data.object
    formLabelAlign.name = data.userName;
    formLabelAlign.height = data.userHeight;
    formLabelAlign.bloodType = data.userBloodtype;
    formLabelAlign.sex = data.userSex;
    formLabelAlign.birthday = data.userBirthday;
  })
}

onMounted(() => {
  getBasicInfo()
})

function returnHealth() {
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

.basic-form-box{
  margin-left: 151px;
  margin-top: 110px;
  width: 1124px;
  height: 619px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}
.basic-form-button{
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
  margin-left: 550px;
  margin-top: -110px;
  width: 400px;
}
/*:deep(.info-bloodtype ){*/
/*  font-size: 15px;*/
/*}*/
/*:deep(.el-col-11 ){*/
/*  max-width: 280px;*/
/*  flex: 0%;*/
/*}*/
:deep(.el-form-item){
  margin-bottom: 20px;
  width: 420px;
}
:deep(.el-form-item--large .el-form-item__label) {
  font-size: 20px;
}
:deep(.el-input__inner ){
  font-size: 18px;
}
/*:deep(.el-select .el-input__wrapper ){*/
/*  width: 300px;*/
/*}*/
:deep(.el-radio.el-radio--large .el-radio__label) {
  font-size: 18px;
}
/*:deep(.el-input--large .el-input__inner ){*/
/*  width: 248px;*/
/*}*/

/*:deep(.el-input--large) {*/
/*  --el-input-height: var(--el-component-size-large);*/
/*  font-size: 20px;*/
/*}*/


.el-select-dropdown__item.selected {
  color: #55a684;
  font-weight: 700;
}
</style>


