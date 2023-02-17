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
        <h class="basic-form-title">医生认证</h>
        <el-row>
          <el-col :span="12">
            <div class="left-box">
              <img shape="square" style="width: 318px; height: 318px; border-radius: 15px;"
                   fit="cover" src="@/assets/img/doc.svg"
              />
            </div>
          </el-col>
          <el-col :span="12" style="margin-top: 25px">
            <div>
              <el-row>
                <el-col :span="6">
                  <p style="font-weight: bold;font-size: 25px;margin-left: 10px;">认证状态：</p>
                </el-col>
                <el-col :span="12" style="margin-left: -10px">
                  <p v-show="formLabelAlign.userIdentity==0"
                     style="font-weight: bold;font-size: 25px;margin-left: 10px;color: #56a785">未认证</p>
                  <p v-show="formLabelAlign.userIdentity==1"
                     style="font-weight: bold;font-size: 25px;margin-left: 10px;color: #f80606">已认证</p>
                  <p v-show="formLabelAlign.userIdentity==2"
                     style="font-weight: bold;font-size: 25px;margin-left: 10px;color: #a0a0a7">待认证</p>
                </el-col>
              </el-row>


            </div>
            <div class="info-modify">
              <el-form
                  :rules="rules"
                  ref="form0"
                  :model="formLabelAlign"
                  label-width="140px" size="large"
                  :label-position="'left'">
                <el-form-item label="真实姓名" prop="name">
                  <el-input v-model="formLabelAlign.name" style="width: 320px;" placeholder="请输入真实姓名"/>
                </el-form-item>

                <el-form-item label="身份证号" prop="idNumber">
                  <el-input v-model="formLabelAlign.idNumber" style="width: 320px" placeholder="请输入身份证号"/>
                </el-form-item>

                <el-form-item :required="true" label="性别" prop="sex">
                  <el-radio-group v-model="formLabelAlign.sex">
                    <el-radio label="男"/>
                    <el-radio label="女"/>
                  </el-radio-group>
                </el-form-item>

                <!--                <el-form-item label="手机号" prop="cellNumber">-->
                <!--                  <el-input v-model="formLabelAlign.cellNumber" style="width: 320px" placeholder="请输入手机号"/>-->
                <!--                </el-form-item>-->

                <el-form-item :required="true" label="所属医院" placeholder="请输入所在医院名称" prop="address">
                  <el-input v-model="formLabelAlign.address" style="width: 320px" placeholder="请输入所属医院名称">

                  </el-input>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
        </el-row>
        <!-- 资料修改 -->
        <div class="avator-box">
          <p style="margin-top: 25px;font-weight: bold;font-size: 25px;margin-left: 45px;margin-bottom: -25px">
            上传医师资格证图片[必填]</p>
          <el-upload
              ref="upload"
              class="upload-demo"
              drag
              action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
              :limit="1"
              :on-exceed="handleExceed"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              :auto-upload="false"
              :http-request="upload"
              v-model:file-list="r.fileList"
              list-type="picture"
          >
            <div class="el-upload__text">
              <div class="upload-title">
                拖拽图片到此处或点击下方按钮上传
              </div>
            </div>
            <el-button class="upload-button">选择文件</el-button>
            <template #tip>
              <div class="el-upload__tip">
                jpg 文件大小不超过 2MB
              </div>
            </template>
          </el-upload>
        </div>
      </div>
      <!-- 按钮 -->
      <div>
        <el-row class="basic-form-button">
          <el-col :span="8">

            <el-button v-show="formLabelAlign.userIdentity ==0" auto-insert-space class="record-submit"
                       @click="onSubmit">上传认证
            </el-button>

            <el-button v-show="formLabelAlign.userIdentity !=0"  disabled auto-insert-space class="record-submit"
                       @click="onSubmit">上传认证
            </el-button>
<!--            -->
<!--            <el-button v-show="formLabelAlign.userIdentity==1||formLabelAlign.userIdentity==2"-->
<!--                       auto-insert-space class="record-submit" @click="onSubmit1">重新认证-->
<!--            </el-button>-->
          </el-col>
<!--          <el-col :span="1">-->
<!--            <el-button v-show="formLabelAlign.userIdentity==1" @click="centerDialogVisible = true" auto-insert-space-->
<!--                       class="widthdraw-cancel">注销认证-->
<!--            </el-button>-->


<!--            <el-button v-show="formLabelAlign.userIdentity==0||formLabelAlign.userIdentity==2"-->
<!--                       @click="returnHealth" auto-insert-space class="record-cancel">取消-->
<!--            </el-button>-->

<!--            <el-button v-show="formLabelAlign.userIdentity ==0" auto-insert-space class="record-submit"-->
<!--                       @click="onSubmit">上传认证-->
<!--            </el-button>-->
<!--            <el-button v-show="formLabelAlign.userIdentity==1||formLabelAlign.userIdentity==2"-->
<!--                       auto-insert-space class="record-submit" @click="onSubmit1">重新认证-->
<!--            </el-button>-->
<!--          </el-col>-->
          <el-col :span="1">
<!--            <el-button v-show="formLabelAlign.userIdentity==1" @click="centerDialogVisible = true" auto-insert-space-->
<!--                       class="widthdraw-cancel">注销认证-->
<!--            </el-button>-->

            <el-button
                       @click="returnHealth" auto-insert-space class="record-cancel">取消
            </el-button>
<!--            <el-button v-show="formLabelAlign.userIdentity==0||formLabelAlign.userIdentity==2"-->
<!--                       @click="returnHealth" auto-insert-space class="record-cancel">取消-->
<!--            </el-button>-->
          </el-col>
        </el-row>
      </div>
    </el-container>
  </div>



  <div class="exit">
    <el-dialog center
               v-model="centerDialogVisible" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="deregister-text">
      您将注销医生身份！
    </span>
      <template #footer>
      <span class="dialog-footer">
        <el-button class="dialog-button" :icon="SwitchButton"
                   plain type="danger"
                   @click="changeState();centerDialogVisible = false">
          确认注销
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="success"
                   @click="centerDialogVisible = false">
          取消注销
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

</template>

<script lang="ts" setup>
import TopBar from "@/components/TopBar.vue"
import BasicInfo from "@/components/BasicInfo.vue"
import {ref, reactive, onMounted, onBeforeMount} from 'vue'
import axios from "axios";
import {ElMessage} from "element-plus";
// import router from "@/router";
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'

import { Back, SwitchButton } from '@element-plus/icons-vue'

// import router from "@/router";
import type {UploadProps, UploadUserFile} from 'element-plus'
import {genFileId} from 'element-plus'
import type {UploadInstance, UploadRawFile} from 'element-plus'


const router = useRouter()
const route = useRoute()

const value = ref('')

const centerDialogVisible = ref(false)

const user = reactive({
  email: sessionStorage.getItem("email"),
  avatarUrl: "",
  userId: sessionStorage.getItem("userId"),
})

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
    value: '其他',
    label: '其他',
  },
]

// do not use same name with ref
const formLabelAlign = reactive({
  userIdentity: '1',
  name: '',
  email: sessionStorage.getItem("email"),
  idNumber: '',
  sex: '男',
  // cellNumber: '',
  address: '',
})


onBeforeMount(() => {
  getFormLabelAlign()
})

function changeState() {
  axios.post('mtc/community/doctor/disagree', {
    userId: user.userId
  })
      .then(function (res) {
        ElMessage.success("注销医生认证成功")
      })
}

function getFormLabelAlign() {
  axios.get('/mtc/community/doctor/info', {params: {email: user.email}})
      .then(function (res) {
        var data = res.data.object;
        console.log(data);
        formLabelAlign.userIdentity = data.userIdentity;
        formLabelAlign.name = data.name;
        formLabelAlign.sex = data.sex;
        formLabelAlign.idNumber = data.idNumber;
        formLabelAlign.address = data.address;
      })
}


function onSubmit1() {
  axios.post('mtc/community/doctor/disagree', {
    userId: user.userId
  })
      .then(function (res) {
        ElMessage.success("正在重新校验您的身份信息")
      })
  submitUpload();
  // console.log(user.avatarUrl)
}



function onSubmit() {
  submitUpload();
  // console.log(user.avatarUrl)
}

const rules = reactive({
  email: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  // name: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  // idNumber: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}, {
  //   pattern: /^(?=.*[0-9])[0-9 _]{1,3}$/,
  //   message: "请输入1-3位数字",
  //   trigger: 'blur'
  // }],
  // idNumber: [{required: 'true', message: '必填项不能为空', trigger: 'blur'},],
  // cellNumber: [{required: 'true', message: '必填项不能为空', trigger: 'blur'},],
  name: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {
    pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]{1,15}$/,
    message: "请输入真实姓名，长度为1-15位，仅支持中文英文数字",
    trigger: 'blur'
  }],
  idNumber: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {
    pattern: /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/ || /^([1-6][1-9]|50)\d{4}\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}$/,
    message: "请输入正确的身份证号，当前格式不匹配",
    trigger: 'blur'
  }],


  sex: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],
  // address: [{required: 'true', message: '必填项不能为空', trigger: 'blur'}],

  address: [{
    required: true,
    message: '必填项不能为空',
    trigger: 'blur'
  }, {
    pattern: /^[\u4e00-\u9fa5a-zA-Z0-9]{5,30}$/,
    message: "请输入联系地址，长度为5-30位，仅支持中文英文数字",
    trigger: 'blur'
  }],
})
var form0 = ref("");
//
// const onSubmit = () => {
//   form0.value.validate((valid: string) => {
//     if (valid) {
//       axios.post("/mtc/health/base", {
//         email: formLabelAlign.email,
//         name: formLabelAlign.name,
//         sex: formLabelAlign.sex,
//         birthday: formLabelAlign.birthday,
//         bloodType: formLabelAlign.bloodType,
//         height: formLabelAlign.height
//       })
//           .then((Response) => {
//             if (Response.data.code === 200) {
//               ElMessage.success("修改成功")
//             } else {
//               ElMessage.error("修改失败")
//               // router.push({ path: '/user' })
//             }
//           })
//     } else {
//       ElMessage.error('数据类型错误或未填写必填项')
//       return false
//     }
//   })
// }

//
// function getBasicInfo() {
//   axios.get("/mtc/user/getuserinfo", {
//     params: {
//       email: formLabelAlign.email
//     }
//   }).then(function (res) {
//     const data = res.data.object
//     formLabelAlign.name = data.userName;
//     formLabelAlign.height = data.userHeight;
//     formLabelAlign.bloodType = data.userBloodtype;
//     formLabelAlign.sex = data.userSex;
//     formLabelAlign.birthday = data.userBirthday;
//   })
// }
//
// onMounted(() => {
//   getBasicInfo()
// })

function returnHealth() {
  router.go(-1);
}


// 上传图片的函数

// 判断图片格式是否符合要求的flag
var flag = ref("true")

const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response, uploadFile, uploadFiles
) => {
  ElMessage.success('图片上传成功')
}
const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('请上传jpg格式文件！')
    flag.value = "false"
    return false
  } else if (rawFile.size / 1024 / 1024 /20> 1) {
    flag.value = "false"
    ElMessage.error('图片大小不能超过4MB!')
    return false
  }
  flag.value = "true"
  return true
}


const upload = ref<UploadInstance>()
// 文件超出上传限制提醒
const handleExceed: UploadProps['onExceed'] = (files, uploadFiles) => {

  // ElMessage.warning("只能选择一张图片，请删除当前图片后重新上传")

  ElMessage.success("已重新上传图片")
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}


function goHome() {
  router.go(-1);
}

//上传图片的函数
var r = reactive({
  fileList: []
})

function submitUpload() {
  upload.value!.submit()
  if (flag.value == "true") {
    if (r.fileList.length > 0) {
      var data = new FormData();
      for (var i = 0; i < r.fileList.length; i++) {
        /* console.log(r.fileList[i].raw) */
        data.append("files", r.fileList[i].raw)
      }

      axios.post("/mtc/user/upload", data).then((res) => {
        if (res.data.code === 200) {
          user.avatarUrl = res.data.object;
          sendResset();
          ElMessage.success("认证资料上传成功")
        } else {
          ElMessage.error("认证资料上传失败，请检查网络环境")
        }
      })
    }
    else {
      ElMessage.error("请输入链接或上传图片再开始认证")
    }

  }
}

function sendResset() {
  // axios.post("/mtc/user/setPortrait", {
  //   email: user.email, address: user.avatarUrl
  // }).then(function (res) {
  //
  // })

  axios.post('/mtc/community/doctor/verify', {
    name: formLabelAlign.name,
    email: formLabelAlign.email,
    idNumber: formLabelAlign.idNumber,
    sex: formLabelAlign.sex,
    // cellNumber: formLabelAlign.cellNumber,
    address: formLabelAlign.address,
    qualificationsUrl: user.avatarUrl
  }).then(function (res) {
        // console.log(res.data.object)
        if(res.data.code == 200){
          ElMessage.success("发送申请成功")
        }else {
          ElMessage.error("发送申请失败，请检查当前网络环境")
        }
      })

  // if (res.data.code === 200) {
  //   // console.log("添加成功")
  //
  // } else {
  //   console.log("添加失败")
  // }
}

// const confirmExit = () => {
//   sessionStorage.removeItem("email")
//   ElMessage.success('您已退出登录！即将返回首页');
//   router.push({ path: "/login" });
// }

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

.basic-form-box {
  margin-left: 151px;
  margin-top: 50px;
  width: 1124px;
  height: 725px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}

.basic-form-button {
  margin-top: 50px;
  margin-left: 1010px;
}

.record-cancel {
  left: 0px;
  top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;
  background: rgb(255, 255, 255);
  color: #414841;
}

.widthdraw-cancel {
  left: 0px;
  top: 0px;
  width: 119.79px;
  height: 34px;
  opacity: 1;

  background: rgb(235, 110, 110);
  color: #ffffff;
}

.record-submit {
  left: 0px;
  top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}

.basic-form-title {
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

.info-modify {
  margin-top: 12px;
  width: 400px;
}

/*:deep(.info-bloodtype ){*/
/*  font-size: 15px;*/
/*}*/
/*:deep(.el-col-11 ){*/
/*  max-width: 280px;*/
/*  flex: 0%;*/
/*}*/
:deep(.el-form-item) {
  margin-bottom: 20px;
  width: 420px;
}

:deep(.el-form-item--large .el-form-item__label) {
  font-size: 20px;
}

:deep(.el-input__inner ) {
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

.left-box {
  margin-left: 150px;
}


/*上传框设置上*/

.avator-box {
  width: 1044px;
  height: 236px;
  margin-left: 40px;
  margin-top: -20px;
}


.el-upload__tip {
  font-size: 12px;
  color: var(--el-text-color-regular);
  margin-top: -20px;
  margin-left: 50px;
}

.upload-demo {
  height: 40px;
  margin-top: 20px;
}

.upload-button {
  margin-left: 3px;
  margin-top: 20px;
  width: 116px;
  height: 48px;
  opacity: 1;
  border-radius: 10px;
  background: rgb(86, 167, 133);

  /** 文本1 */
  font-size: 20px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(255, 255, 255, 1);
  text-align: left;
  vertical-align: top;
}

.el\-upload__text {
  margin-left: 29px;
  margin-top: 0px;
  width: 965.16px;
  height: 104px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  border: 1px dashed rgba(128, 128, 128, 1);
}

.upload-title {
  margin-left: 255px;
  margin-top: 50px;
  /** 文本1 */
  font-size: 24px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(128, 128, 128, 1);
  text-align: left;
  vertical-align: top;
}

:deep(.el-upload-dragger) {
  padding: var(--el-upload-dragger-padding-horizontal) var(--el-upload-dragger-padding-vertical);
  background-color: #dbece5;
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  box-sizing: border-box;
  text-align: center;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.el\-upload__text[data-v-e4c7cf3d] {
  margin-left: 35px;
  margin-top: 0px;
  width: 900.16px;
  height: 100px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  border: 1px dashed #414841;
}

:deep(.el-upload) {
  --el-upload-dragger-padding-horizontal: 30px;
  --el-upload-dragger-padding-vertical: 10px;
}

.deregister-text {
  justify-content: center;
  font-size: 28px;
  margin-left: 160px;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.dialog-button {
  margin-top: 10px;
  margin-left: 20px;
  margin-bottom: 20px;
  font-size: 20px;
  width: 200px;
  height: 50px;
}

/*上传框设置下*/

</style>


