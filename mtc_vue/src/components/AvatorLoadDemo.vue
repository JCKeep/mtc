<template>
  <div class="avator-box">
  <el-upload
      class="upload-demo"
      drag
      action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
      multiple
      :auto-upload="false"
      :http-request="upload"
      v-model:file-list="r.fileList"
  >
    <div class="el-upload__text">
      <div class="upload-title">
        拖拽图片到此处
      </div>
    </div>
    <el-button class="upload-button">选择文件</el-button>
    <template #tip>
      <div class="el-upload__tip">
        jpg/png 文件大小不超过 500kb
      </div>
    </template>
  </el-upload>
  </div>
</template>

<script setup lang="ts">
import { UploadFilled } from '@element-plus/icons-vue'
import axios from 'axios';
import {reactive, ref} from 'vue';
import {ElMessage} from "element-plus";

var r = reactive({
  fileList: []
})

function upload() {
  if (r.fileList.length > 0) {
    var data = new FormData();
    for (var i = 0; i < r.fileList.length; i++) {
      /* console.log(r.fileList[i].raw) */
      data.append("files", r.fileList[i].raw)
    }
    axios.post("/upload", data).then((res) => {
      if (res.data.code != 200) {
        ElMessage.error("上传失败")
      }
      else {
        ElMessage.success("上传成功")
      }
    })
  }
  // console.log("123")
}

</script>


<style scoped>
.avator-box{
  width: 1044px;
  height: 236px;
  margin-left: 40px;
  margin-top: -20px;
}
.el-upload.is-drag {
  display: block;
  top: 20px;
}

.el-upload__tip {
  font-size: 12px;
  color: var(--el-text-color-regular);
  margin-top: -20px;
  margin-left: 50px;
}
.upload-demo{
  height: 40px;
  margin-top: 20px;
}
.upload-button{
  margin-left: 3px;
  margin-top: 20px;
  width: 116px;
  height: 48px;
  opacity: 1;
  border-radius: 16px;
  background: rgba(254, 173, 84, 0.9);

  /** 文本1 */
  font-size: 20px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 0px;
  color: rgba(255, 255, 255, 1);
  text-align: left;
  vertical-align: top;
}
.el\-upload__text{
  margin-left: 29px;
  margin-top: 0px;
  width: 965.16px;
  height: 104px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  border: 1px dashed rgba(128, 128, 128, 1);
}
/*添加头像识别框*/
.el-upload-dragger {
  margin-top: 30px;
  width: 1044px;
  height: 236px;
  opacity: 1;
  background: rgba(255, 255, 255, 1);
  border: 1px dashed rgba(128, 128, 128, 1);
  padding: var(--el-upload-dragger-padding-horizontal) var(--el-upload-dragger-padding-vertical);

  border-radius: 30px;
  box-sizing: border-box;
  text-align: center;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.upload-title{
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
</style>