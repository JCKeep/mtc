<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>

      <div class="my-actor-box">
        <h class="my-actor-title">
          我的头像
        </h>

        <!--        <div>-->
        <!--          <AvatorUpload></AvatorUpload>-->
        <!--        </div>-->
        <div>
          <!--          <UploadDemo ref="child"></UploadDemo>-->
          <!--          取消封装该组件，便于上传数据-->
          <div class="avator-box">
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
                  拖拽图片到此处
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

      </div>

      <el-row class="my-actor-button">
        <el-col :span="8">
          <el-button @click="submitUpload" auto-insert-space class="record-submit">保存头像</el-button>
        </el-col>
        <el-col :span="1">
          <el-button @click="goHome" auto-insert-space class="record-cancel">取消</el-button>
        </el-col>
      </el-row>

    </el-container>
  </div>
</template>

<script setup lang="ts">
import TopBar from "@/components/TopBar.vue"

import axios from 'axios';
import {reactive, ref} from 'vue';
// import router from "@/router";
import {ElMessage} from "element-plus";
import type {UploadProps, UploadUserFile} from 'element-plus'
import {genFileId} from 'element-plus'
import type {UploadInstance, UploadRawFile} from 'element-plus'
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'

const router = useRouter()
const route = useRoute()

// 判断图片格式是否符合要求的flag
var flag = ref("true")

const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response, uploadFile, uploadFiles
) => {
  ElMessage.success('图片上传成功，请确认是否设置头像')
}
const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('请上传jpg格式文件！')
    flag.value = "false"
    return false
  } else if (rawFile.size / 1024 / 1024 > 1) {
    flag.value = "false"
    ElMessage.error('图片大小不能超过1MB!')
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

const user = reactive({
  email: sessionStorage.getItem("email"),
  avatarUrl: "",
})

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
          ElMessage.success("头像设置成功")
        } else {
          ElMessage.error("头像设置失败，请检查网络环境")
        }
      })
    } else {
      ElMessage.error("请上传图片再点击保存头像按钮")
    }
  }
}

function sendResset() {
  axios.post("/mtc/user/setPortrait", {
    email: user.email, address: user.avatarUrl
  }).then(function (res) {
    if (res.data.code === 200) {
      console.log("添加成功")
    } else {
      console.log("添加失败")
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

.my-actor-box {
  margin-left: 151px;
  margin-top: 160px;
  width: 1124px;
  height: 490px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(85, 166, 132, 0.21);
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.25);
}

.my-actor-button {
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

.record-submit {
  left: 0px;
  top: 0px;
  width: 118px;
  height: 34px;
  opacity: 1;
  background: rgba(85, 166, 132, 1);
  color: #f8f8f8;
}

.my-actor-title {
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

/*上传框设置*/

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
  margin-left: 398px;
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

/*添加头像识别框*/

</style>