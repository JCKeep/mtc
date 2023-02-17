<template>
  <div class="common-layout">
    <el-container>
      <!-- 头部 -->
      <el-header class="header">
        <TopBar></TopBar>
      </el-header>


      <el-container>
      <el-aside class="aside-box">
        <CommunityAside></CommunityAside>
      </el-aside>

      <el-main class="main-box">
        <div class="search-text">搜索</div>
        <el-input
            v-model="search"
            class="input-box"
            size="large"
            placeholder="输入搜索内容"
            clearable
            :prefix-icon="Search"
        >
          <!--            <template #append>-->
          <!--              <el-button @click="getDietList" class="input-box-btn">-->
          <!--                <el-icon><Search /></el-icon>-->
          <!--              </el-button>-->
          <!--            </template>-->
        </el-input>


        <div class="label-text">药物指南</div>
        <div class="table-box">
          <el-scrollbar>
            <el-table :data="tableDrug" class="drug-table">
              <el-table-column type="index" label="序号" width="80" align="center" />

<!--              <el-table-column property="drugId" label="ID" width="120" align="center" />-->

              <el-table-column property="drugImage"  label="图片" width="200" align="center" >

               <template #default="scope">
                 <el-image
                     style="width: 160px; height: 100px; border-radius: 15px;"
                     :src="scope.row.drugImage"
                     :preview-src-list="[scope.row.drugImage]"
                     :initial-index="0"
                     fit="cover"
                     preview-teleported="true"
                 />
  <!--                  <img :src="scope.row.drugPic" />-->
                </template>
  <!--              <div class="left-box">-->
  <!--                <el-avatar shape="square" :size="318" style="border-radius: 15px" :src="property" />-->
  <!--              </div>-->

              </el-table-column>

              <el-table-column property="drugName" label="名称" width="160" align="center" />

              <el-table-column property="drugDetail"  label="详情" width="240" align="center" />

              <el-table-column label="操作" width="120" align="center">
                <template v-slot="scope">
                  <el-button link type="primary"
                    style="font-size: 20px;"
                    @click.prevent="ViewDetails(scope.row.drugId)">
                    查看
                  </el-button>
                </template>
              </el-table-column>

          </el-table>
            
          </el-scrollbar>
        </div>
      </el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, ref, reactive, toRefs, onBeforeMount, computed } from 'vue'
import TopBar from "@/components/TopBar.vue"
import CommunityAside from "@/components/CommunityAside.vue"
import axios from "axios";
// import router from "@/router";
import { ElMessage } from "element-plus";
import { Memo, Collection, Search } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
const router = useRouter()
const route = useRoute()

// import avatarURL from '@/assets/img/headPicture.jpg'
// const state = reactive({
//   avatarUrl:
//       '../assets/img/headPicture.jpg',
// })
// const { avatarUrl }= toRefs(state)

//const userId = ref(route.query?.userId)
const input = ref('')
// const userId = ref('1')
// var user = reactive({
//   email: sessionStorage.getItem("email"),
//   flag: false,
// })

const search = ref('')
const tableDrug = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.drugName.toLowerCase().includes(search.value.toLowerCase())
    )
)

const tableData = ref([
  {
    drugId:'',
    drugPic: '',
    drugName: '',
    drugDetail: '',
    drugImage:'',
  },
])

function DrugHome() {
  router.push({ path: "/Community/DrugHome" })
}

function FoodHome() {
  router.push({ path: "/Community/FoodHome" })
}


function getInfo() {
  axios.post('/mtc/community/getDrug',{option: true,left: 0,right: 300})
      .then(function (res){
        // console.log(res.data.object)
        tableData.value = res.data.object;
      })
}

function ViewDetails(id:any) {
  sessionStorage.setItem("drugId",id)
  router.push({ path: "/Community/drugDetails"})
}

onBeforeMount(() => {
  getInfo()
})



</script>

<style scoped>
.common-layout {
  position: relative;
  width: 1440px;
  /*height: 1024px;*/
  box-shadow: #55a684;
  /*height: 100%;*/
  /* background-color: #55a684; */
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

.aside-box {
  margin-left: 100px;
  width: 300px;
  height: 650px;
}

.aside-btn {
  color: #55a684;
  font-size: 20px;
  text-align: center;
  margin-top: 35px;
  width: 280px;
  height: 65px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}
.main-box {
  width: 930px;
  margin-left: 30px;
  margin-top: 5px;
  /*margin-left: 430px;*/
  /*margin-top: -650px;*/
  /* margin-top: -220px; */
}
.search-text {
  font-size: 20px;
  letter-spacing: 0.4px;
  color: black;
}
.label-text {
  font-size: 20px;
  letter-spacing: 0.4px;
  color: black;
  margin-top: 35px;
}
.input-box {
  margin-top: 8px;
  width: 890px;
  height: 60px;
  opacity: 1;
  border-radius: 15px;
  font-size: 20px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
}
.input-box-btn {
  width: 80px;
  font-size: 22px;
  margin-top: -5px;
  text-align: center;
}
.table-box {
  height: 630px;
  width: 888px;
  margin-top: 12px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}
.drug-table {
  width: 840px;
  font-size: 20px;
  margin-left: 22px;
  margin-top: 20px;
}
.el-input {
  --el-input-border-radius: 15px;
}
:deep(.el-input-group__append) {
  border-radius: 0px 15px 15px 0px;
}


</style>