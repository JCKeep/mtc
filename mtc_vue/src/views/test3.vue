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

        <el-main class="main-box" v-show="user.userIdentity==0">
          <el-input
              v-model="search"
              class="input-box"
              size="large"
              placeholder="输入患者用户名进行搜索"
              clearable
              :prefix-icon="Search"
          >
          </el-input>

          <el-row>
            <el-col :span="11">
              <div class="label-text">患者列表</div>
            </el-col>

            <el-col :span="11" style="margin-left: 25px;margin-top: 30px">
              <el-button-group class="ml-4">
                <el-button  size="large" class="top-button" @click="selectAll()">
                  <hands theme="outline" size="20" fill="#ffffff" strokeLinecap="butt"/>
                  &nbsp;&nbsp;全部用户
                </el-button>
                <el-button size="large" class="top-button" @click="selectBind()">
                  <people theme="outline" size="20" fill="#ffffff" strokeLinecap="butt"/>
                  &nbsp;&nbsp;我的病人
                </el-button>
                <el-button size="large" class="top-button" @click="selectUnBind()">
                  <audit theme="outline" size="20" fill="#ffffff" strokeLinecap="butt"/>
                  &nbsp;&nbsp;待审批
                </el-button>
              </el-button-group>
            </el-col>
          </el-row>




          <div class="table-box">
            <el-scrollbar>
              <el-table :data="tableListB" class="drug-table" >
                <el-table-column property="userImage"  label="头像" width="120" align="center" >

                  <template #default="scope">
                    <el-image
                        style="width: 80px; height: 80px; border-radius: 50px;"
                        :src="scope.row.userImage"
                        fit="cover"
                    />
                  </template>

                </el-table-column>

                <el-table-column property="userName" label="用户名" width="260" align="center" />

                <el-table-column property="userSex"  label="性别" width="130" align="center" />
                <el-table-column property="userAge"  label="年龄" width="130" align="center" />
                <el-table-column fixed="right" label="操作" width="200" align="center">
                  <template v-slot="scope">
                    <el-button type="danger" plain round
                               v-show="scope.row.userBindingStatus==0"
                               style="font-size: 20px;margin-left: 0px"
                               @click.prevent="Unbind(scope.row.userId)">
                      解除绑定
                    </el-button>

                    <el-button type="success" plain round
                               v-show="scope.row.userBindingStatus==1"
                               style="font-size: 20px;"
                               @click.prevent="confirmBinding(scope.row.userId)">
                      同意
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>

            </el-scrollbar>
          </div>
        </el-main>

        <el-main class="main-box" v-show="user.userIdentity==1">
          <el-input
              v-model="search"
              class="input-box"
              size="large"
              placeholder="输入医生用户名进行搜索"
              clearable
              :prefix-icon="Search"
          >
          </el-input>

          <el-row>
            <el-col :span="11">
              <div class="label-text">医生列表</div>
            </el-col>

            <el-col :span="11" style="margin-left: 25px;margin-top: 30px">
              <el-button-group class="ml-4">
                <el-button  size="large" class="top-button" @click="selectAll()">
                  <hands theme="outline" size="20" fill="#ffffff" strokeLinecap="butt"/>
                  &nbsp;&nbsp;全部医生
                </el-button>
                <el-button size="large" class="top-button" @click="selectBind()">
                  <people theme="outline" size="20" fill="#ffffff" strokeLinecap="butt"/>
                  &nbsp;&nbsp;我的医生
                </el-button>
                <el-button size="large" class="top-button" @click="selectUnBind()">
                  <audit theme="outline" size="20" fill="#ffffff" strokeLinecap="butt"/>
                  &nbsp;&nbsp;待审批
                </el-button>
              </el-button-group>
            </el-col>
          </el-row>




          <div class="table-box">
            <el-scrollbar>
              <el-table :data="tableDocListB" class="drug-table" >
                <el-table-column property="userImage"  label="头像" width="120" align="center" >

                  <template #default="scope">
                    <el-image
                        style="width: 80px; height: 80px; border-radius: 50px;"
                        :src="scope.row.userImage"
                        fit="cover"
                    />
                  </template>

                </el-table-column>

                <el-table-column property="userName" label="用户名" width="260" align="center" />

                <el-table-column property="userHospital"  label="所在医院" width="130" align="center" />
                <el-table-column property="userCount"  label="病人数" width="130" align="center" />
                <el-table-column fixed="right" label="操作" width="200" align="center">
                  <template v-slot="scope">
                    <el-button type="success" plain round
                               v-show="scope.row.userBindingStatus==2"
                               style="font-size: 20px;margin-left: 0px"
                               @click.prevent="Unbind(scope.row.userId)">
                      申请
                    </el-button>
                    <el-button type="danger" plain round
                               v-show="scope.row.userBindingStatus==0"
                               style="font-size: 20px;"
                               @click.prevent="Unbind(scope.row.userId)">
                      解除绑定
                    </el-button>

                    <el-button type="info" plain round
                               v-show="scope.row.userBindingStatus==1"
                               style="font-size: 20px;"
                               @click.prevent="confirmBinding(scope.row.userId)">
                      待同意
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
import {onMounted, ref, reactive, toRefs, onBeforeMount, computed} from 'vue'
import TopBar from "@/components/TopBar.vue"
import CommunityAside from "@/components/CommunityAside.vue"
import axios from "axios";
// import router from "@/router";
import { ElMessage } from "element-plus";
import { Memo, Collection, Search } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { useRoute } from 'vue-router'
import {Audit, Hands, People} from '@icon-park/vue-next';

const router = useRouter()
const route = useRoute()


const input = ref('')
const search = ref('')
const choiceA = ref('')
var user = reactive({
  email: sessionStorage.getItem("email"),
  userIdentity : '1',
})

function selectAll() {
  choiceA.value = '';
}

function selectBind() {
  choiceA.value = "0";
}

function selectUnBind() {
  choiceA.value = "1";
}

const tableList = computed(() =>
    userList.value.filter(
        (data) =>
            !search.value ||
            data.userName.toLowerCase().includes(search.value.toLowerCase())
    )
)

const tableListB = computed(() =>
    tableList.value.filter(
        (data) =>
            !choiceA.value ||
            data.userBindingStatus.toLowerCase().includes(choiceA.value.toLowerCase())
    )
)

const tableDocList = computed(() =>
    userDocList.value.filter(
        (data) =>
            !search.value ||
            data.userName.toLowerCase().includes(search.value.toLowerCase()) ||
            data.userHospital.toLowerCase().includes(search.value.toLowerCase())
    )
)

const tableDocListB = computed(() =>
    tableDocList.value.filter(
        (data) =>
            !choiceA.value ||
            data.userBindingStatus.toLowerCase().includes(choiceA.value.toLowerCase())
    )
)

const userDocList = ref([
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'2',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userHospital: '野生医院',
    userCount:'26',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userHospital: '湖南大学校医院',
    userCount:'26',
    userBindingStatus:'1',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '王五',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'2',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '赵六',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'2',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'2',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '哈哈哈',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'2',
  },



])

const userList = ref([
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '王五',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'1',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '王五',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'1',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '哈哈哈',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'0',
  },
  {
    userId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '哈哈哈',
    userSex: '男',
    userAge:'23',
    userBindingStatus:'0',
  },
])

function getInfo() {
  axios.post('/mtc/community/getDrug',{option: true,left: 1,right: 5})
      .then(function (res){
        // console.log(res.data.object)
        // tableDrug.value = res.data.object;
      })
}



function confirmBinding(id:any) {

}

function Unbind(id:any) {

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
  height: 900px;
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
  height: 660px;
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

.top-button{
  opacity: 1;
  border-radius: 10px;
  background: rgb(86, 167, 133);
  color: #f8f8f8;
  font-size: 15px;
  width: 140px;
  height: 35px;
}

</style>