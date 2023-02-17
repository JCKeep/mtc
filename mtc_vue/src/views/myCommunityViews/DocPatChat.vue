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
          <el-container>
            <el-aside class="aside-box-list">
              <div class="other-name">
                通讯录
                <!--                <el-button style="font-size: 15px;color: #1d3650;margin-left: 140px" @click="sendData()">-->
                <!--                在线客服-->
                <!--              </el-button>-->
              </div>


              <el-input
                  v-model="search"
                  class="input-box"
                  size="large"
                  placeholder="输入用户名搜索"
                  clearable
                  :prefix-icon="Search"
              >
              </el-input>


              <div class="table-box">


                <el-scrollbar>
                  <div
                      v-for="(list,i) in tableList"
                      :key="i"
                  >
                    <el-button style="height: 100px; width: 300px"
                               @click="openChatRecord(list.userName,list.userImage,list.userId)">
                      <el-container style="margin-top: 20px;margin-left: -10px">
                        <el-aside width="60px">
                          <template #default="scope">
                            <el-image
                                style="width: 60px; height: 60px; border-radius: 5px;"
                                :src="list.userImage"
                                fit="cover"
                                preview-teleported="true"
                            />
                            <!--                  <img :src="scope.row.drugPic" />-->
                          </template>
                        </el-aside>
                        <el-container>
                          <el-header style="height: 20px">
                            <el-row type="flex" class="row-bg">
                              <el-col :span="1" v-show="Number(list.unreadCount)!=0">
                                <el-badge :value="Number(list.unreadCount)" :max="99">
                                  <el-button style="margin-left: -20px; font-size: 18px" type="plain" text>
                                    {{ list.userName.toString().substring(0, 6) }}
                                  </el-button>
                                  <!--                                  <div>{{list.unreadCount}}</div>-->
                                </el-badge>

                                <!--                                <el-badge :value="Number(list.unreadCount)" :max="99" v-show="list.unreadCount!=0">-->
                                <!--                                  <el-button style="margin-left: -20px; font-size: 18px" type="plain" text>-->
                                <!--                                    {{ list.userName.toString().substring(0, 6) }}-->
                                <!--                                  </el-button>-->
                                <!--                                </el-badge>-->
                              </el-col>

                              <el-col :span="1" v-show="Number(list.unreadCount)==0">
                                <el-badge :value="Number(list.unreadCount)" :max="99" hidden>
                                  <el-button style="margin-left: -20px; font-size: 18px" type="plain" text>
                                    {{ list.userName.toString().substring(0, 6) }}
                                  </el-button>
                                  <!--                                  <div>{{list.unreadCount}}</div>-->
                                </el-badge>

                                <!--                                <el-badge :value="Number(list.unreadCount)" :max="99" v-show="list.unreadCount!=0">-->
                                <!--                                  <el-button style="margin-left: -20px; font-size: 18px" type="plain" text>-->
                                <!--                                    {{ list.userName.toString().substring(0, 6) }}-->
                                <!--                                  </el-button>-->
                                <!--                                </el-badge>-->
                              </el-col>
                              <el-col :span="16"></el-col>
                              <el-col :span="2">
                                <!--                                <time  style="margin-left: 0px">{{ getDay(list.lastTime) }}</time>-->
                                <time style="margin-left: 0px">{{ list.lastTime = renderTime(list.lastTime).substring(0,10) }}</time>
                              </el-col>
                            </el-row>

                          </el-header>

                          <el-main>
                            <div style="width: 180px; margin-left: -5px; font-size: 18px ;text-align: left">
                              <!--                              {{ list.lastMessage.toString().substring(0, 10) + ".." }}-->
                              {{ list.lastMassage.toString().substring(0, 10) + ".." }}
                            </div>
                          </el-main>

                        </el-container>
                      </el-container>

                    </el-button>
                  </div>


                </el-scrollbar>
              </div>
              <!--              <el-divider direction="vertical"/>-->
            </el-aside>

            <el-main class="main-chat-box">
              <el-container>
                <el-header class="header-main-box">
                  <el-row>
                    <el-col :span="18">
                      <div style="text-align: left" class="other-name">
                        {{ basicInfo.otherName.toString().substring(0, 17) }}
                      </div>
                    </el-col>
                    <el-col :span="2">
                      <el-button v-show="user.userIdenty!=1" class="main-chat-send-data"
                                 style="font-size: 15px;color: #f8f8f8" @click="sendData()">
                        发送身体数据
                      </el-button>
                    </el-col>
                  </el-row>
                  <el-divider/>
                </el-header>
                <el-main class="right-main-box">

                  <el-scrollbar>
                    <div
                        v-for="(list,i) in chatRecord"
                        :key="i"
                    >
                      <el-container v-show="list.messageSender==0">
                        <el-aside width="40px" style="border-radius: 4px;background: rgb(255,255,255);">
                          <el-image
                              style="width: 40px; height: 40px; border-radius: 50px;"
                              :src="basicInfo.otherImage"
                              fit="cover"
                              preview-teleported="true"
                          />
                        </el-aside>
                        <el-container style="display: inline-block;">
                          <el-header style="display: inline-block;">
                            <p style="border-radius: 5px;padding: 10px;background: rgba(85,166,132,0.86);font-size: 20px; color: #ffffff">
                              {{ list.messageText }}
                            </p>
                          </el-header>
                          <el-main>
                            <!--                            <time style="margin-left: 4px">{{ getDay(list.messageItme) }}</time>-->
                            <time style="margin-left: 4px">{{ list.messageTime = renderTime(list.messageTime) }}</time>
                          </el-main>
                        </el-container>
                      </el-container>

                      <el-container v-show="list.messageSender==1">
                        <el-container align="right" style="margin-left: 70px;display: inline-block">
                          <el-header style="display: inline-block">
                            <p align="left"
                               style="border-radius: 5px;padding: 10px;background: rgba(85,166,132,0.86);font-size: 20px; color: #ffffff">
                              {{ list.messageText }}
                            </p>
                          </el-header>
                          <el-main>
                            <!--                            <time style="margin-left: 4px">{{ getDay(list.messageItme) }}</time>-->
                            <time style="margin-left: 4px">{{ list.messageTime = renderTime(list.messageTime) }}</time>
                          </el-main>
                        </el-container>

                        <el-aside width="40px" style="border-radius: 4px;background: rgb(255,255,255);">
                          <el-image
                              style="width: 40px; height: 40px; border-radius: 50px;"
                              :src="form.userPortrait"
                              fit="cover"
                              preview-teleported="true"
                          />
                        </el-aside>
                      </el-container>
                    </div>
                  </el-scrollbar>

                </el-main>
                <el-divider/>
                <el-footer class="right-footer-box">
                  <el-row v-show="sendMessage.receiverId!=''">
                    <el-col :span="22">
                      <el-input
                          v-model="sendMessage.text"
                          maxlength="1000"
                          placeholder="输入聊天内容"
                          :autosize="{ minRows: 4, maxRows: 4 }"
                          show-word-limit
                          type="textarea"
                          style="width: 400px"
                          resize="none"
                      />
                    </el-col>

                    <el-col :span="2">
                      <el-button style="width: 60px;
                      margin-top: 55px;
                      height: 36px;
                      opacity: 1;
                      border-radius: 5px;
                      background: rgba(85, 166, 132, 1);
                      color: #f8f8f8"
                                 @click="send()">
                        发送
                      </el-button>
                    </el-col>
                  </el-row>


                </el-footer>
              </el-container>


            </el-main>

          </el-container>


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
import {ElMessage} from "element-plus";
import {Memo, Collection, Search} from '@element-plus/icons-vue'
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'

const text = ref('')
const router = useRouter()
const route = useRoute()

const search = ref('')
const tableList = computed(() =>
    tableChatList.value.filter(
        (data) =>
            !search.value ||
            data.userName.toLowerCase().includes(search.value.toLowerCase())
    )
)

function doHandleMonth(month) {
  var m = month;
  if (month.toString().length == 1) {
    m = "0" + month;
  }
  return m;
}


function renderTime(date: any) {
  var dateee = new Date(date).toJSON();
  var d = new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '');
  return d
}

function getUpdateTime(time: any) {
  let date = new Date(time)
  let Y = date.getFullYear() + '-'
  let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
  let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
  let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
  let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
  let s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds())
  return Y + M + D
}

//
// function getDay(day:any,type:any) {
//   var now = new Date();
//   var today = new Date(day);
//   var targetday_milliseconds = today.getTime();
//   today.setTime(targetday_milliseconds); //注意，这行是关键代码
//
//   var nowYear = now.getFullYear();
//   var nowMonth = now.getMonth();
//   var nowDate = now.getDate();
//   nowMonth = doHandleMonth(nowMonth + 1);
//   nowDate = doHandleMonth(nowDate);
//
//   var tYear = today.getFullYear();
//   var tMonth = today.getMonth();
//   var tDate = today.getDate();
//   var tHours = today.getHours();
//   var tMinutes = today.getMinutes();
//   var tMeconds=today.getSeconds();
//   var AmOrPm = 'AM';
//
//   tMonth = doHandleMonth(tMonth + 1);
//   tDate = doHandleMonth(tDate);
//   tHours = doHandleMonth(tHours);
//   tMinutes = doHandleMonth(tMinutes);
//   tMeconds=doHandleMonth(tMeconds);
//
//   if(tHours>=12){
//     AmOrPm = 'PM';
//   }
//   // alert(tYear + "-" + tMonth + "-" + tDate)
//   if((nowDate-tDate)==1){
//     return "昨天  " + tHours+ ":" + tMinutes;
//   }else if((nowDate-tDate)>1){
//     return tMonth+ "月" +tDate+"日"
//   }
//   return tHours+ ":" + tMinutes+ "   " + AmOrPm;
// }


function getDay(day: any) {
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

var user = reactive({
  email: sessionStorage.getItem("email"),
  userId: sessionStorage.getItem("userId"),
  userIdenty: sessionStorage.getItem("userIdenty"),
  // uid:sessionStorage.getItem("uid")
})

const form = reactive({
  userPortrait: "https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500",
})

function getBasicInfo() {
  axios.get("/mtc/user/getuserinfo", {
    params: {
      email: user.email
    }
  }).then(function (res) {
    const data = res.data.object
    form.userPortrait = data.userPortrait;
  })
  // router.go(0)
}

onMounted(() => {
  getBasicInfo()
})


const input = ref('')

const chatRecord = ref([
  {
    messageSender: '',
    messageText: '',
    messageTime: '',
  },
])

const basicInfo = ref({
  otherName: "" + sessionStorage.getItem("otherName"),
  otherImage: "" + sessionStorage.getItem("otherImage"),
})

const sendMessage = ref({
  myId: sessionStorage.getItem("userId"),
  receiverId: sessionStorage.getItem("receiverId"),
  text: '',
})

function getSimpleDate(date: any) {
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


function send() {
  axios.post('/mtc/msg/send', {
    myId: sendMessage.value.myId,
    receiverId: sendMessage.value.receiverId,
    text: sendMessage.value.text
  })
      .then(function (res) {
        if (res.data.code == 200) {
          // console.log(res.data.code)
          // tableDrug.value = res.data.object;
          router.go(0);
        } else {
          ElMessage.error("您与医生的绑定关系似乎胡出现了问题，消息无法发送")
        }


      })
  sendMessage.value.text = '';
  // getChatRecord(sendMessage.value.receiverId)
}

function sendData() {
  // sendMessage.value.text
  axios.get('/mtc/health/healthdata7day', {params: {email: sessionStorage.getItem("email")}})
      .then(function (res) {
        var data = res.data.object;
        sendMessage.value.text = "以下为我近七天的的血糖数据\n";
        sendMessage.value.text += getDay(-6) + ": " + data[0].bloodSugar + "mmol/L\n";
        sendMessage.value.text += getDay(-5) + ": " + data[1].bloodSugar + "mmol/L\n";
        sendMessage.value.text += getDay(-4) + ": " + data[1].bloodSugar + "mmol/L\n";
        sendMessage.value.text += getDay(-3) + ": " + data[1].bloodSugar + "mmol/L\n";
        sendMessage.value.text += getDay(-2) + ": " + data[1].bloodSugar + "mmol/L\n";
        sendMessage.value.text += getDay(-1) + ": " + data[1].bloodSugar + "mmol/L\n";
        sendMessage.value.text += getDay(0) + ": " + data[1].bloodSugar + "mmol/L\n";
        ElMessage.success("已获取您的数据，温馨提示，显示为0则说明您可能未记录数据")

      })

}

const tableChatList = ref([
  {
    userId: '',
    userImage: '',
    userName: '',
    lastTime: '',
    lastMassage: '',
    unreadCount: '',
  },])

const tableChatList1 = ref([
  {
    userId: '',
    userImage: '',
    userName: '',
    lastTime: '',
    lastMassage: '',
    unreadCount: '',
  },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '',
  //   lastTime: new Date(),
  //   lastMassage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '8',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '王五',
  //   lastTime: new Date(),
  //   lastMessage: '青年',
  //   unreadCount: '9',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈哈',
  //   lastTime: new Date(),
  //   lastMessage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '108',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '李四',
  //   lastTime: new Date(),
  //   lastMessage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '5',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '王五',
  //   lastTime: new Date(),
  //   lastMessage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '9',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈hhhhhhhhhhhhhhhhhhhh哈',
  //   lastTime: new Date(),
  //   lastMessage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '100',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈',
  //   lastTime: new Date(),
  //   lastMessage: '青年',
  //   unreadCount: '5',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '王五',
  //   lastTime: new Date(),
  //   lastMessage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '9',
  // },
  // {
  //   userId: '',
  //   userImage: 'http://114.132.226.110/file/IMG_5267.JPG',
  //   userName: '哈哈hhhhhhhhhhhhhhhhhhhh哈',
  //   lastTime: new Date(),
  //   lastMessage: '青年大学习做了没青年大学习做了没青年大学习做了没',
  //   unreadCount: '100',
  // },
])

function getTableChatList() {
  axios.get("/mtc/msg/chatlist", {
    params: {
      userId: sessionStorage.getItem("userId")
      // email:user.email
    }
  }).then(function (res) {
    const data = res.data.object
    tableChatList.value = data;
  })
}


function DrugHome() {
  router.push({path: "/Community/DrugHome"})
}

function FoodHome() {
  router.push({path: "/Community/FoodHome"})
}

function getChatRecord(uid: any) {
  axios.get("/mtc/msg/getmsg", {
    params: {
      myId: user.userId,
      uid: uid,
      // email:user.email
    }
  }).then(function (res) {
    const data = res.data.object
    chatRecord.value = data;
    // console.log("hello1")
  })
}


function openChatRecord(name: any, image: any, uid: any) {
  basicInfo.value.otherName = name;
  sessionStorage.setItem("otherName", name)
  basicInfo.value.otherImage = image;
  sessionStorage.setItem("otherImage", image)
  sendMessage.value.receiverId = uid;
  sessionStorage.setItem("receiverId", uid)
  getChatRecord(uid);
}

function openChatRecordinit() {
  getChatRecord(sendMessage.value.receiverId);
}

onBeforeMount(() => {
  getTableChatList(),
      openChatRecordinit()
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
  width: 800px;
  height: 800px;
  margin-left: 30px;
  margin-right: 101px;
  margin-top: 30px;
  border-radius: 10px;
  background: rgba(255, 255, 255, 1);

  border: 1px solid rgba(229, 229, 229, 1);

  box-shadow: 2px 4px 4px 0px rgba(21, 34, 50, 0.08);
}

.aside-box-list {
  /*margin-left: 10px;*/
  width: 320px;
  /*height: 758px;*/
}

.main-chat-box {

  opacity: 1;
  border-radius: 10px;
  background: rgba(255, 255, 255, 1);

  border: 1px solid rgba(229, 229, 229, 1);
}


.label-text {
  font-size: 20px;
  letter-spacing: 0.4px;
  color: black;
  margin-top: 35px;
}

.input-box {
  margin-top: 8px;
  width: 300px;
  height: 60px;
  opacity: 1;
  font-size: 20px;
  border-radius: 4px;
}

.input-box-btn {
  width: 80px;
  font-size: 22px;
  margin-top: -5px;
  text-align: center;
}

.table-box {
  height: 646px;
  width: 300px;
  margin-top: 12px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.drug-table {
  width: 840px;
  font-size: 20px;
  /*margin-left: 22px;*/
  margin-top: 20px;
}

.el-input {
  --el-input-border-radius: 15px;
}

:deep(.el-input-group__append) {
  border-radius: 0px 15px 15px 0px;
}

.other-name {
  font-size: 20px;
  font-weight: 700;
  letter-spacing: 0px;
  line-height: 24px;
  color: rgba(19, 21, 35, 1);
  text-align: left;
  vertical-align: top;
}

.main-chat-send-data {
  width: 121px;
  height: 36px;
  opacity: 1;
  border-radius: 4px;
  background: rgba(85, 166, 132, 1)
}

.header-main-box {
  height: 70px;
}

.right-main-box {
  height: 500px;
}

.right-footer-box {
  /*height: 90px;*/
}

.el-header {
  --el-header-height: 0px;
}
</style>