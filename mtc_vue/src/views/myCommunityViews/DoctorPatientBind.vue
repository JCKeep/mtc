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

        <el-main class="main-box" v-show="user.userIdentity==1">
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
                        v-show="scope.row.userImage!=-1"
                    />
                  </template>

                </el-table-column>

                <el-table-column property="userName" label="用户名" width="260" align="center" />

                <el-table-column property="userSex"  label="性别" width="130" align="center" />
                <el-table-column property="userAge"  label="年龄" width="130" align="center" />
                <el-table-column  label="操作" width="200" align="center">
                  <template v-slot="scope">
                    <el-button type="danger" plain round
                               v-show="scope.row.userBindingStatus==0"
                               style="font-size: 20px;margin-left: 0px"
                               @click.prevent="TransInfoPat(scope.row),centerDialogVisiblePai1 = true">
                      解除绑定
                    </el-button>

                    <el-button type="success" plain round
                               v-show="scope.row.userBindingStatus==1"
                               style="font-size: 20px;"
                               @click.prevent="TransInfoPat(scope.row),centerDialogVisiblePai2 = true">
                      同意
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>

            </el-scrollbar>
          </div>
        </el-main>

        <el-main class="main-box" v-show="user.userIdentity==2||user.userIdentity==0">
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
                <el-table-column  label="操作" width="200" align="center">
                  <template v-slot="scope">
                    <el-button type="success" plain round
                               v-show="scope.row.userBindingStatus==2"
                               style="font-size: 20px;margin-left: 0px"

                               @click.prevent="TransInfoDoc(scope.row),centerDialogVisible1 = true">
                      申请
                    </el-button>
                    <el-button type="danger" plain round
                               v-show="scope.row.userBindingStatus==0"
                               style="font-size: 20px;"
                               @click.prevent="TransInfoDoc(scope.row),centerDialogVisible2 = true">
                      解除绑定
                    </el-button>

                    <el-button type="info" plain round
                               v-show="scope.row.userBindingStatus==1"
                               style="font-size: 20px;"
                               @click.prevent="TransInfoDoc(scope.row),centerDialogVisible3 = true">
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

  <div>
    <el-dialog center
               v-model="centerDialogVisiblePai1" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您正在解除绑定关系！
    </span>
      <template #footer>
      <span>
        <el-button class="dialog-button" :icon="User"
                   plain type="danger"
                   @click="Unbind();centerDialogVisiblePai1 = false"
        >
          确认解除
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="success"
                   @click="centerDialogVisiblePai1 = false">
          取消操作
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

  <div>
    <el-dialog center
               v-model="centerDialogVisiblePai2" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您正在建立绑定关系！
    </span>
      <template #footer>
      <span>
        <el-button class="dialog-button" :icon="User"
                   plain type="success"
                   @click="confirmBinding();centerDialogVisiblePai2 = false"
        >
          确认绑定
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="danger"
                   @click="centerDialogVisiblePai2 = false">
          取消操作
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>


  <div>
    <el-dialog center
               v-model="centerDialogVisible1" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您正在申请绑定账户！
    </span>
      <template #footer>
      <span>
        <el-button class="dialog-button" :icon="User"
                   plain type="success"
                   @click="applyBind();centerDialogVisible1 = false"
        >
          确认申请
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="danger"
                   @click="centerDialogVisible1 = false">
          取消申请
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

  <div>
    <el-dialog center
               v-model="centerDialogVisible2" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您正在申请解除绑定！
    </span>
      <template #footer>
      <span>
        <el-button class="dialog-button" :icon="User"
                   plain type="danger"
                   @click="UnbindUser();centerDialogVisible2 = false"
        >
          确认解除
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="success"
                   @click="centerDialogVisible2 = false">
          取消操作
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

  <div>
    <el-dialog center
               v-model="centerDialogVisible3" draggable
               width="30%" align-center
               style="--el-dialog-border-radius: 15px;">
    <span class="logout-text">
      您正在撤销申请操作！
    </span>
      <template #footer>
      <span>
        <el-button class="dialog-button" :icon="User"
                   plain type="danger"
                   @click="UnbindUser();centerDialogVisible3 = false"
        >
          确认撤销
        </el-button>
        <el-button class="dialog-button" :icon="Back"
                   plain type="success"
                   @click="centerDialogVisible3 = false">
          取消操作
        </el-button>
      </span>
      </template>
    </el-dialog>
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
//确认框
const centerDialogVisible1 = ref(false)
const centerDialogVisible2 = ref(false)
const centerDialogVisible3 = ref(false)
const centerDialogVisiblePai1 = ref(false)
const centerDialogVisiblePai2 = ref(false)

const input = ref('')
const search = ref('')
const choiceA = ref('')
var user = reactive({
  email: sessionStorage.getItem("email"),
  userIdentity : sessionStorage.getItem("userIdenty"),
  userId: sessionStorage.getItem("userId")
  // userId:"5"
})

function init() {
  if(user.userIdentity == "1"){
    docUser.doctorId = sessionStorage.getItem("doctorId")
  }else{
    patientUser.userId = user.userId;
  }
}

var docUser = reactive({
  userId:'',
  doctorId:'',
  userImage: '',
  userName: '',
  userHospital: '',
  userCount:'',
  userBindingStatus:'',
})

var patientUser = reactive({
  userId:'',
  userImage: '',
  userName: '',
  userSex: '',
  userAge:'',
  userBindingStatus:'',
},)

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

function getUserDocList() {
  axios.get('/mtc/community/binding/dlist',{params:{userId:user.userId}})
      .then(function (res){
        userDocList.value = res.data.object;
        for(let i = 0;i<userDocList.value.length;i++){
          userDocList.value[i].userBindingStatus = "" + userDocList.value[i].userBindingStatus;
        }
      })
}


const userDocList = ref([
  {
    userId:'',
    doctorId:'',
    userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
    userName: '李四',
    userHospital: '北京协和医院',
    userCount:'26',
    userBindingStatus:'2',
  },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userHospital: '野生医院',
  //   userCount:'26',
  //   userBindingStatus:'0',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userHospital: '湖南大学校医院',
  //   userCount:'26',
  //   userBindingStatus:'1',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '王五',
  //   userHospital: '北京协和医院',
  //   userCount:'26',
  //   userBindingStatus:'2',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userHospital: '北京协和医院',
  //   userCount:'26',
  //   userBindingStatus:'0',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '赵六',
  //   userHospital: '北京协和医院',
  //   userCount:'26',
  //   userBindingStatus:'2',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userHospital: '北京协和医院',
  //   userCount:'26',
  //   userBindingStatus:'2',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈哈',
  //   userHospital: '北京协和医院',
  //   userCount:'26',
  //   userBindingStatus:'2',
  // },
])

function getUserList() {
  axios.get('/mtc/community/binding/ulist',{params:{userId:user.userId}})
      .then(function (res){
        userList.value = res.data.object;
        for(let i = 0;i<userList.value.length;i++){
          userList.value[i].userBindingStatus = "" + userList.value[i].userBindingStatus;
        }
        console.log("患者信息")
        console.log(userList.value)
      })
}

const userList = ref([
  {
    userId:'',
    userImage: '-1',
    userName: '',
    userSex: '',
    userAge:'',
    userBindingStatus:'',
  },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '王五',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'1',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'0',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '王五',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'1',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'0',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈哈',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'0',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'0',
  // },
  // {
  //   userId:'',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈哈',
  //   userSex: '男',
  //   userAge:'23',
  //   userBindingStatus:'0',
  // },
])

function getInfo() {
  axios.post('/mtc/community/getDrug',{option: true,left: 1,right: 5})
      .then(function (res){
        // console.log(res.data.object)
        // tableDrug.value = res.data.object;
      })
}

function TransInfoDoc(user:any) {
  docUser = user;
  console.log("这里是医生信息")
  console.log(user)
}

function TransInfoPat(user:any) {
  patientUser = user;
}

function applyBind() {
  axios.post('/mtc/community/binding/request', {userId: patientUser.userId, doctorId:docUser.doctorId})
      .then(function (res) {
        // console.log(res.data.object)
        ElMessage.success("申请绑定成功")
        router.go(0);
      })

}

function UnbindUser(id:any) {
  axios.post('/mtc/community/binding/remove', {userId: patientUser.userId, doctorId:docUser.doctorId})
      .then(function (res) {
        // console.log(res.data.object)
        ElMessage.success("已成功撤销绑定申请")
        router.go(0);
      })
}

function confirmBinding(id:any) {
  axios.post('/mtc/community/binding/accept', {userId: patientUser.userId, doctorId:docUser.doctorId})
      .then(function (res) {
        // console.log(res.data.object)
        ElMessage.success("已成功建立绑定关系")
        router.go(0);
      })
}

function Unbind(id:any) {
  axios.post('/mtc/community/binding/remove', {userId: patientUser.userId, doctorId:docUser.doctorId})
      .then(function (res) {
        // console.log(res.data.object)
        ElMessage.success("已成功解除绑定关系")
        router.go(0);
      })
}

onBeforeMount(() => {
  init(),
      getInfo(),
      getUserDocList(),
      getUserList()
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

.logout-text {
  justify-content: center;
  font-size: 28px;
  margin-left: 130px;
}
.logout-confirm-context {
  justify-content: center;
  font-size: 28px;
  margin-left: 40px;
}

.dialog-button {
  margin-top: 10px;
  margin-left: 20px;
  margin-bottom: 20px;
  font-size: 20px;
  width: 200px;
  height: 50px;
}

</style>