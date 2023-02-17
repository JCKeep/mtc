<template>
  <div>

    <el-row>
      <el-col :span="3">
        <el-dropdown trigger="click"  @comand="commandHandle">
          <span class="choose-time">
              <el-button class="arrow-down" :icon="ArrowDown" />
          </span>
          <template #dropdown>
            <el-dropdown-menu >
              <el-dropdown-item @click="switchWor"><el-icon style="font-size: 16px; width: 20px; color: orange;"><Sunrise /></el-icon>晨间用药</el-dropdown-item>
              <el-dropdown-item @click="switchNon"><el-icon style="font-size: 16px; width: 20px; color: #ff6347;"><Sunny /></el-icon>午间用药</el-dropdown-item>
              <el-dropdown-item @click="switchNig"><el-icon style="font-size: 16px; width: 20px; color: #6a5acd;"><Moon /></el-icon>晚间用药</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-col>
    <el-col :span="17" >
      <el-row >
        <div class="drug-time" style="margin-left: 10px">
        <el-col :span="13" >

          <div class="drug-time-text" >
            <!--        <el-icon style="font-size: 16px; width: 20px; padding-top: 7px; color: orange;"><Sunrise /></el-icon>&nbsp;&nbsp;{{user.types}}-->
            <el-icon v-if="user.types === '晨间'" style="font-size: 16px; width: 20px; padding-top: 15px; color: orange;"><Sunrise /></el-icon>
            <span class="abc"  v-if="user.types === '晨间'">晨间用药</span>
            <el-icon v-if="user.types === '午间'" style="font-size: 16px; width: 20px; padding-top: 15px; color: #ff6347;"><Sunny /></el-icon>
            <span style="margin-left: 10px" v-if="user.types === '午间'">午间用药</span>
            <el-icon v-if="user.types === '晚间'" style="font-size: 16px; width: 20px; padding-top: 15px; color: #6a5acd;"><Moon /></el-icon>
            <span style="margin-left: 10px" v-if="user.types === '晚间'">晚间用药</span>
          </div>
        </el-col>

        <el-col :span="1" style="margin-left: 290px;margin-top: -25px">
          <el-button  :icon="EditPen" style="font-size: 18px;" link @click="outerVisible = true"/>
        </el-col>
        </div>
<!--        <el-col :span="3" style="margin-left:375px;margin-top: -55px">-->
          <el-col :span="3" style="margin-left:353px;margin-top: -34px">

          <el-button  type="default" :icon="Plus" circle @click="adderVisible = true" />
        </el-col>
      </el-row>


<!--      <el-button  type="default" :icon="Plus" circle @click="adderVisible = true" />-->



      <div class="drug-add">

        <el-dialog
            class="outer-dialog"
            v-model="outerVisible"
            top="37vh" draggable
            :title="user.types + '用药'"
            width="360px"
            style="--el-dialog-border-radius: 15px;
                 --el-font-size-large: 24px;">
          <!-- <el-dialog v-model="outerVisible" title="Outer Dialog"> -->
          <template #default>
            <el-dialog
                class="inner-dialog"
                v-model="innerVisible"
                top="37vh" draggable
                width="460px"
                title="药物指南"
                style="--el-dialog-border-radius: 15px;"
                append-to-body>
              <!--            <el-input class="drug-add-search" v-model="input" placeholder="Search here" />-->
              <!--            <el-button class="drug-add-search-btn" type="primary" :icon="Search" circle />-->
              <el-table :data="tableDrug" class="drug-table-item1" style="margin-top: -30px">
                <el-table-column type="index" label="序号" width="60" align="center" />
                <el-table-column property="drugId" label="ID" width="120" align="center" />
                <el-table-column property="drugName" label="药物" width="160" align="center" />
                <el-table-column  label="操作" width="80" align="center">
                  <template v-slot="scope">
                    <el-button link type="primary" @click.prevent="onAddItem(scope.row.drugId)">
                      添加
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-dialog>
          </template>



          <template #footer>
            <div class="dialog-footer">
              <el-table :data="tableData" class="drug-table-crud">
                <el-table-column type="index" label="序号" width="70" align="center" />
                <el-table-column property="drugName" label="药物" width="170" align="center" />
                <el-table-column  label="操作" width="80" align="center">
                  <template #default="scope">
                    <!--                <el-button link type="primary" @click.prevent="deleteRow(scope.$index)">-->
                    <el-button link type="primary" style="font-size: 18px;"
                               @click.prevent="onDeleteItem(scope.row.drugId); deleteRow(scope.$index)">
                      删除
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-button class="mt-4" style="width: 320px; height: 40px; margin-top: 12px; font-size: 18px;"
                         @click="innerVisible = true">添加其他药物</el-button>

              <!-- <el-button @click="outerVisible = false">Cancel</el-button> -->
              <!-- <el-button type="primary" @click="innerVisible = true">
                open the inner Dialog
              </el-button> -->
            </div>
          </template>
        </el-dialog>

        <!-- <el-dialog
          v-model="dialogTableVisible" draggable
          top="37vh" title="晨间药物" width="320px"
          style="--el-dialog-border-radius: 15px;"
        >

        </el-dialog> -->
      </div>


    </el-col >

    <el-col class="batch-add-button">

        <el-dialog
            class="adder-dialog"
            v-model="adderVisible"
            top="20vh" draggable
            width="1080px"
            title="药物指南-批量添加至我的药物"
            style="--el-dialog-border-radius: 15px;"
            append-to-body>
          <!--            <el-input class="drug-add-search" v-model="input" placeholder="Search here" />-->
          <!--            <el-button class="drug-add-search-btn" type="primary" :icon="Search" circle />-->
          <el-input
              v-model="search"
              class="input-box"

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



          <el-table :data="tableDrugList" class="drug-table-item">
            <el-table-column type="index" label="序号" width="60" align="center" />
<!--            <el-table-column property="drugId" label="ID" width="120" align="center" />-->
            <el-table-column property="drugImage"  label="图片" width="200" align="center" >

              <template #default="scope">
                <el-image
                    style="width: 100px; height: 100px; border-radius: 15px;"
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

            <el-table-column property="drugName" label="药物" width="160" align="center" />
            <el-table-column property="drugDetail"  label="详情" width="170" align="center" />
            <el-table-column property="sendType" fixed="right" label="选择时间" width="270" align="center">
              <template #default="scope">
              <div style="display: inline-block">
                <el-select
                    v-model="scope.row.sendType"
                    multiple
                    placeholder="Select"
                    style="width: 230px"
                >
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  />
                </el-select>
              </div>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="160" align="center">
              <template v-slot="scope">
                <el-button link type="primary" @click.prevent="onAddBatchItem(scope.row.drugId,scope.row.sendType)">
                  添加
                </el-button>
                <el-button link type="danger" @click.prevent="onDeleteBatchItem(scope.row.drugId,scope.row.sendType)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-dialog>
    </el-col>
    </el-row>

    <div class="drug-item">
      <!-- Drugtable -->
      <el-table :data="tableData" class="drug-table" stripe>
        <el-table-column type="index" label="序号" width="70" align="center" />
        <!--        <el-table-column prop="medicationId" label="Id" width="80" align="center" />-->
        <el-table-column prop="drugName" label="药物" width="80" align="center" />
        <el-table-column prop="medicationUsage"  label="详情" width="170" align="center" />
        <el-table-column  width="80" label="操作" align="center">
          <template #default="scope">
            <el-switch
                v-model="scope.row.isTake"
                :active-value="false"
                :inactive-value="true"
                active-color="#13ce66"
                inactive-color="#B9B9B9"
                @click="onFlagSwitchItem(scope.row.drugId,scope.row.isTake)"
            />
          </template>
        </el-table-column>
      </el-table>
    </div>




    <div class="drug-or-not">
      <div class="drug-slide-true" v-if="user.flag === false">
        <el-button v-if="user.flag === false" :icon="SuitcaseLine" size="large" style="font-size: 35px;" @click="hasDrug"/>
        <p class="drug-slide-text-true" v-if="user.flag === false">您该吃药啦！请点击确认</p>
      </div>
      <div class="drug-slide-false" v-if="user.flag === true">
        <el-button  v-if="user.flag === true" :icon="SuitcaseLine" size="small" style="font-size: 35px;" @click="hasDrug"/>
        <p class="drug-slide-text-false" v-if="user.flag === true" style="width: 260px">已用药，祝您身体健康！</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ArrowDown, Sunrise, Sunny, Moon, EditPen, SuitcaseLine, Delete,Search,CirclePlus,Plus } from '@element-plus/icons-vue'

import {ref, reactive, onBeforeMount, computed} from 'vue'
import axios from 'axios'
import {ElMessage} from "element-plus";
const outerVisible = ref(false)
const innerVisible = ref(false)
const adderVisible = ref(false)
const search = ref('')
const choiceA = ref('')

const value1 = ref([])
const value2 = ref([])
const value3 = ref([])
const options = [
  {
    value: '晨间',
    label: '晨间',
  },
  {
    value: '午间',
    label: '午间',
  },
  {
    value: '晚间',
    label: '晚间',
  },
]

const dialogTableVisible = ref(false)
const input = ref('')
var user = reactive({
  email:sessionStorage.getItem("email"),
  flag: false,
  types:"晨间"
})

const form = reactive({
  delivery: false,
})

const hasDrug = () => {
  user.flag = !user.flag;
  subTake();
}

const deleteRow = (index: number) => {
  tableData.value.splice(index, 1)
}

const onAddItemRow = () => {
  tableData.value.push({
    drugName:'',
    medicationId:'',
    medicationUsage: '',
    isTake: false,
    // drugName: '厄尔沙坦片',
    // drugDetail: '饭前服用，一次一粒',
    // status: 1,
  })
}

const tableData = ref([
  {
    drugName:'',
    medicationId:'',
    medicationUsage: '',
    isTake: false,
    // status: 1,
  },
])

const tableDrug = ref([
  {
    drugId:'',
    drugName: '',
    drugDetail: '',
    sendType:["晨间",],
    drugImage:'',
  },
])


const tableDrugList = computed(() =>
    tableDrug.value.filter(
        (data) =>
            !search.value ||
            data.drugName.toLowerCase().includes(search.value.toLowerCase())||data.drugDetail.toLowerCase().includes(search.value.toLowerCase())
    )
)


function switchWor() {
  user.types="晨间";
  defaultInfo();
}

function switchNon() {
  user.types="午间"
  defaultInfo();
}

function switchNig() {
  user.types="晚间"
  defaultInfo();
}



function onAddItem(value:any,type:any) {
  axios.post('/mtc/medication/modify',{email:user.email,type:user.types,drugId:value,option:1
  }).then(function (res){
    if(res.data === 200){
      defaultInfo();
      ElMessage.success("添加药物成功")
    }
    else {
      defaultInfo();
      ElMessage.success("添加药物成功，并为您过滤掉重复的药物")
    }
  })
}

function onAddBatchItem(value:any,type:any) {
  // var length = 3;
  // if(length == 0){
  //   ElMessage.warning("请至少选择一个时间段")
  // }else{
  if(type.length == 0){
    ElMessage.error("至少选择一个时间段进行添加")
  }else {
    for (var i = 0; i < type.length; i++) {
      axios.post('/mtc/medication/modify', {
        email: user.email, type: type[i], drugId: value, option: 1
      }).then(function (res) {
        if (res.data === 200) {
          defaultInfo();
          // ElMessage.success("添加药物成功")
        } else {
          defaultInfo();
          // ElMessage.success("添加药物成功，并为您过滤掉重复的药物")
        }
      })
    }
    ElMessage.success("批量添加药物成功，并为您过滤掉重复的药物")
  }
  // }
}

function onDeleteBatchItem(value:any,type:any) {
  // var length = 3;
  // if(length == 0){
  //   ElMessage.warning("请至少选择一个时间段")
  // }else{
  if(type.length == 0){
    ElMessage.error("至少选择一个时间段进行删除")
  }else {
    for (var i = 0; i < type.length; i++) {
      axios.post('/mtc/medication/modify', {
        email: user.email, type: type[i], drugId: value, option: -1
      }).then(function (res) {
        if (res.data === 200) {
          defaultInfo();
          // ElMessage.success("添加药物成功")
        } else {
          defaultInfo();
          // ElMessage.success("添加药物成功，并为您过滤掉重复的药物")
        }
      })
    }
    ElMessage.success("删除药物成功，并为您过滤掉表内不存在的药物")
  }
  // }
}

function onDeleteItem(value:any) {
  axios.post('/mtc/medication/modify',{email:user.email,type:user.types,drugId:value,option:-1
  }).then(function (res){
    ElMessage.success("删除药物成功")
    getInfo()
  })
}

function onFlagSwitchItem(value1:any,value2:boolean) {
  if(value2 === true){
    axios.post('/mtc/medication/modify',{email:user.email,type:user.types,drugId:value1,option:0
    }).then(function (res){
    })
  }
  else {
    axios.post('/mtc/medication/modify', {
      email: user.email, type: user.types, drugId: value1, option: 1
    }).then(function (res) {
    })
  }
}

function getInfo() {
  axios.post('/mtc/community/getDrug',{option: true,left: 0,right: 300})
      .then(function (res){
        // console.log(res.data.object)
        tableDrug.value = res.data.object;
      })
}

function defaultInfo() {
  // axios.get('/mtc/medication/get',{params:{type: user.types ,email: user.email}})
  axios.post('/mtc/medication/get',{type: user.types ,email: user.email})
      .then(function (res){
        // console.log(res.data.object)
        tableData.value = res.data.object;
      })
}

function isTake() {
  // axios.get('/mtc/medication/get',{params:{type: user.types ,email: user.email}})
  axios.get('/mtc/state',{params:{email: user.email}})
      .then(function (res){
        // console.log(res.data.object)
        user.flag = res.data.object;
      })
}

function subTake() {
  // axios.get('/mtc/medication/get',{params:{type: user.types ,email: user.email}})
  axios.post('/mtc/state',{email: user.email,state:user.flag})
      .then(function (res){
        // console.log(res.data.object)
        // user.flag = res.data.object;
      })
}

onBeforeMount(() => {
  getInfo(),
      defaultInfo(),
      isTake()
})



</script>

<style scoped>


.drug-time {
  width: 500px;
  height: 37.21px;
  opacity: 1;
  border-radius: 23.92px;
  background: #FFFFFF;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
  /*display: flex;*/
  justify-content: left;
  align-items: center;
  margin-left: 54px;
  margin-top: 20px;
}
.drug-time-text{
  padding-left: 20px;
  width: 120px;
}
.drug-item {
  width: 422.56px;
  height: 256px;
  opacity: 1;
  background: rgba(255, 255, 255, 1);
  border-radius: 29.48px;
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
  margin-left: 20px;
  margin-top: 25px;
}
.drug-add {
  padding-left: 165px;
  margin-top: -2px;
  width: 20px;
}
.drug-table {
  width: 450px;
  height: 240px;
  padding-left: 16px;
  border-radius: 30px;
  font-size: 16px;
}
.drug-or-not{
  left: 20px;
  top: 22px;
  width: 422.56px;
  height: 106px;
  opacity: 1;
  border-radius: 29.48px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}
.drug-slide-true {
  left: 22px;
  top: 18px;
  width: 378px;
  height: 70px;
  opacity: 1;
  border-radius: 30px;
  background: rgba(249, 235, 227, 1);
  /* background: linear-gradient(180.39deg, rgba(249, 235, 227, 1) 0%, rgba(255, 255, 255, 0.52) 100%); */
}
.drug-slide-false {
  left: 22px;
  top: 18px;
  width: 378px;
  height: 70px;
  opacity: 1;
  border-radius: 30px;
  background: #E1EFE9;
  /* background: linear-gradient(180.39deg, rgba(249, 235, 227, 1) 0%, rgba(255, 255, 255, 0.52) 100%); */
}
.drug-slide-text-true {
  color: #414841;
  margin-top: -50px;
  margin-left: 110px;
  font-size: 20px;
}
.drug-slide-text-false {
  margin-top: -50px;
  margin-left: 30px;
  font-size: 20px;
}
.el-button--large {
  --el-button-size: 70px;
  width: 80px;
  border-radius: 30px;
  color: #FFFFFF;
  background-color: rgba(227, 60, 100, 1);
}
.el-button--small {
  margin-left: 298px;
  --el-button-size: 70px;
  width: 80px;
  border-radius: 30px;
  color: #FFFFFF;
  background-color: #7DD4AF;
}
.drug-table-crud {
  font-size: 19px;
  margin-top: -55px;
  height: 240px;
}
.drug-table-item {
  margin-top: 30px;
  height: auto;
}

.drug-table-item1 {
  /*margin-top: -30px;*/
  /*height: auto;*/
}
.drug-add-search {
  width: 110px;
  margin-left: 90px;
  margin-top: -128px;
}
/*.el-button.is-circle {*/
/*  margin-top: -130px;*/
/*  margin-left: -15px;*/
/*}*/

.abc{
  margin-left: 10px;
  height: 10px;
}

.choose-time {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
  margin-left: 20px;
  margin-top: 23px;
  width: 40px;
}

.arrow-down {
  color: #55A684;
  width: 35px;
  height: 33px;
  opacity: 1;

  border-radius: 8.77px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

/*!*批量添加按钮*!*/
:deep(.el-button.is-circle[data-v-27185a17] ){
  /*margin-top: 20px;*/
  /*margin-left: 15px;*/
  /*border-radius: 8.77px;*/
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}


.input-box {
  margin-top: -30px;
  width: 1024px;
  height: 40px;
  opacity: 1;
  border-radius: 15px;
  font-size: 18px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 0px 20px 0px rgba(0, 0, 0, 0.1);
}

.el-button.is-circle[data-v-99886573] {
  /* margin-top: -130px; */
  /* margin-left: -15px; */
}
</style>