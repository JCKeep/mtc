<template>
  <div class="diet-management">
    <div class="common-layout">
      <el-container>
        <el-header class="header">
          <TopBar></TopBar>
        </el-header>
        <el-container>
          <el-main>
            <div style="height:60px"></div>
            <el-row :gutter="30" style="margin-left:118px; width: 83%;">
              <el-col :span="5">
                <el-button @click="goBack" plain color="#56a785" style="width:120px; height:34px; font-size:20px;">
                  返回
                </el-button>
              </el-col>
              <el-col :span="14" style="text-align: center;">
                <p style="color:#56a785;font-size: 28px">查看饮食记录</p>
              </el-col>
              <el-col :span="5">
                <el-button @click="nextStep"
                           style="background-color:#56a785; color:white; float:right; width:120px; height:34px; font-size:20px;">
                  下一步
                </el-button>
              </el-col>
            </el-row>
            <div style="height:60px;"></div>
            <div>
              <el-row :gutter="30">
                <el-col :span="5"/>
                <el-col :span="14">
                  <el-steps :space="400" :active="0" finish-status="success" align-center>
                    <el-step title="选择时间"/>
                    <el-step title="Step 2"/>
                    <el-step title="Step 3"/>
                  </el-steps>
                </el-col>
                <el-col :span="5"/>
              </el-row>
            </div>
            <div style="height:30px;"></div>
            <div style="margin-left: 377px;">
              <!-- 														<el-form
                                            ref="dietformRef"

                                            :model="dietform"
                                            hide-required-asterisk>
                                             <el-form-item label="请选择用餐日期" prop="dietdate" class="item"
                                                :rules="[{
                                                   required: true,
                                                   message: '请选择用餐日期',
                                                   trigger: 'change',
                                                 }]">
                                                 <el-date-picker
                                                   v-model="dietform.dietdate"
                                                   type="date"
                                                   placeholder="Pick a date"
                                                   style="width: 100%"
                                                            value-format="YYYY-MM-DD"
                                                 />
                                             </el-form-item>
                                              </el-form> -->
              <p style="font-size: 20px; ">请选择用餐日期：</p>
              <el-form
                  ref="dietformRef"
                  :model="dietform"
                  hide-required-asterisk>
                <el-form-item prop="dietdate" class="error_style"
                              :rules="[{
																							required: true,
																							message: '请选择用餐日期',
																							trigger: 'change',
																					}]">
                  <!-- 	<p style="font-size: 20px; ">请选择用餐日期：</p> -->
                  <el-row class="data-calendar">
                    <DataCalendar
                        type="day"
                        value-format="YYYY-MM-DD"
                        v-model="dietform.dietdate"
                    ></DataCalendar>
                  </el-row>
                </el-form-item>
              </el-form>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script setup>
import {
  Edit,
  Plus
} from '@element-plus/icons-vue'
import {ref, reactive, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'
import {ElMessage} from 'element-plus';
import axios from 'axios'
import TopBar from '../../components/TopBar.vue'
import DataCalendar from "@/components/DataCalendar.vue"

const router = useRouter()
const route = useRoute()

const input = ref('')

const userId = ref(route.query?.userId)

const dietform = reactive({
  dietdate: ''
})
const dietformRef = ref(null);

function goBack() {
  router.push({name: 'dietindex', query: {userId: userId.value}})
}

function getSimpleDate(date) {
  if (date != null) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var s = date.getSeconds();
    s = s < 10 ? '0' + s : s;
    return y + '-' + m + '-' + d;
  }
}

const nextStep = () => {
  dietformRef.value.validate((valid) => {
    if (valid) {
      router.push({name: 'choosediet', query: {userId: userId.value, dietdate: getSimpleDate(dietform.dietdate)}})
    } else {
      ElMessage.error('无可提交数据！')
      return false
    }
  })
}

</script>

<style lang="scss">
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

.item .el-form-item__label {
  font-size: 22px;
}

.data-calendar {
  margin-top: 60px;
  width: 650px;
}

.error_style .el-form-item__error {
  font-size: 20px;
}

</style>
