<template>
  <div class="diet-management">
    <div class="common-layout">
      <el-container>
        <!-- 头部 -->
        <el-header class="header">
          <TopBar></TopBar>
        </el-header>

        <el-container>
          <el-main>
            <div style="height:60px"></div>
            <el-row :gutter="30" style="margin-left:118px; width: 83%;">
              <el-col :span="5">
                <el-button @click="goBack" plain color="#56a785" style="width:120px; height:34px; font-size:20px;">
                  上一步
                </el-button>
              </el-col>
              <el-col :span="14" style="text-align: center;">
                <p style="color:#56a785;font-size: 28px">查看饮食记录</p>
              </el-col>
              <el-col :span="5">
                <el-button @click="nextStep"
                           style="background-color:#56a785; float:right; color:white; width:120px; height:34px; font-size:20px;">
                  返回
                </el-button>
              </el-col>
            </el-row>
            <div style="height:60px;"></div>
            <div>
              <el-row :gutter="30">
                <el-col :span="5"/>
                <el-col :span="14">
                  <el-steps :space="400" :active="1" finish-status="success" align-center>
                    <el-step title="选择时间"/>
                    <el-step title="选择记录"/>
                    <el-step title="Step 3"/>
                  </el-steps>
                </el-col>
                <el-col :span="5"/>
              </el-row>
            </div>
            <div style="height:30px;"></div>
            <div>
              <el-row :gutter="2">
                <el-col :span="4"/>
                <el-col :span="18">
                  <table>
                    <thead>
                    <tr>
                      <th>饮食编号</th>
                      <th>用餐时间</th>
                      <th>食物名</th>
                      <th>热量</th>
                      <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="site in sites">
                      <td v-text="site.dietId"></td>
                      <td v-text="site.dietTime"></td>
                      <td v-text="site.foodName"></td>
                      <td v-text="site.foodCalories"></td>
                      <td>
                        <el-button @click="goEdit(site.dietId)" style="width:50%; height:30px; font-size:20px;"
                                   color="#56a785" plain>查看详情
                        </el-button>
                        <!-- 																																					<el-button
                                                                                                      type="danger"
                                                                                                      style="width:35%; height:30px; font-size:20px;"
                                                                                                      @click=""
                                                                                                      >删除</el-button
                                                                                                  > -->
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </el-col>
                <!-- 														<el-col :span="3"/> -->
              </el-row>
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
import axios from 'axios'
import TopBar from '../../components/TopBar.vue'

const router = useRouter()
const route = useRoute()

const input = ref('')

const userId = ref(route.query?.userId)
const datepick = ref(route.query?.dietdate)
let sites = ref([]);

function goBack() {
  router.push({name: 'searchrecord', query: {userId: userId.value}})
}

function nextStep() {
  router.push({name: 'dietindex', query: {userId: userId.value}})
}

function goEdit(value) {
  router.push({name: 'editrecord', query: {userId: userId.value, dietId: value}})
}

onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/getRecord', {params: {userId: userId.value, date: datepick.value}})
      .then(function (res) {
        console.log(res.data)
        const data = res.data.object
        sites.value = data;
      })
})


</script>

<style lang="scss" scoped>
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


/* 设置表格 */
table {
  /* 表格的宽度与top一样 */
  width: 90%;

  /* 设置表格边框 */
  border: 1px solid white;

}

/* 设置表头 */
th {

  width: 200px;
  height: 45px;

  background-color: #dfede6;

  border: 0px;
  font-size: 22px;

  /* 合并相邻的边框 */
  border-collapse: collapse;

  /* 字体居中对齐 */
  text-align: center;

}

/* 设置表格中每一个单元格 */
td {
  height: 40px;

  /* 设置无边框 */
  border: 0px;
  font-size: 20px;

  /* 字体水平居中 */
  text-align: center;

}
</style>