<template>
  <div style="height:30px;"></div>
<!--  <el-row>-->
<!--    <el-card class="community-card-full" style="margin-left: 2px; box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);">-->
<!--      <el-button type="text" style="width: 236px; font-size: 20px; color:#000000; background-color: white;"-->
<!--                 @click="isComFull()">-->
<!--        社区论坛&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;-->
<!--        <expand-text-input theme="outline" size="20" fill="#333" strokeLinecap="butt" v-show="isCardA==0"-->
<!--                           class="el-icon&#45;&#45;right"/>-->
<!--        <collapse-text-input theme="outline" size="20" fill="#333" strokeLinecap="butt" v-show="isCardA==1"-->
<!--                             class="el-icon&#45;&#45;right"/>-->
<!--        &lt;!&ndash;        <el-icon v-show="isCardA==0" class="el-icon&#45;&#45;right"><Expand /></el-icon>&ndash;&gt;-->
<!--        &lt;!&ndash;        <el-icon v-show="isCardA==1" class="el-icon&#45;&#45;right"><Fold /></el-icon>&ndash;&gt;-->
<!--      </el-button>-->
<!--      <div style="height:0px;"></div>-->
<!--      <el-button v-show="isCardA==1" class="aside-inbtn" color="#55a684" plain @click="CommunityCenter"-->
<!--                 :icon="Collection">-->
<!--        社区中心&emsp;&emsp;&emsp;&emsp;&emsp;-->
<!--      </el-button>-->
<!--      <div style="height:0px;"></div>-->
<!--      <el-button v-show="isCardA==1" class="aside-inbtn" color="#55a684" plain @click="MyPost" :icon="Collection">-->
<!--        我的发布&emsp;&emsp;&emsp;&emsp;&emsp;-->
<!--      </el-button>-->
<!--      <div style="height:0px;"></div>-->
<!--      <el-button v-show="isCardA==1" class="aside-inbtn" color="#55a684" plain @click="MyCollect" :icon="Collection">-->
<!--        我的收藏&emsp;&emsp;&emsp;&emsp;&emsp;-->
<!--      </el-button>-->
<!--    </el-card>-->
<!--  </el-row>-->

  <div style="height:37px;"></div>
  <el-row>
    <el-card class="community-card-full" style="margin-left: 2px; box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);">
      <el-button type="text" style="width: 236px; font-size: 20px; color:#000000; background-color: white;"
                 @click="isDocFull()">
        我的医患&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
        <expand-text-input theme="outline" size="20" fill="#333" strokeLinecap="butt" v-show="isCardB==0"
                           class="el-icon--right"/>
        <collapse-text-input theme="outline" size="20" fill="#333" strokeLinecap="butt" v-show="isCardB==1"
                             class="el-icon--right"/>
        <!--					<el-icon v-show="isCardB==0" class="el-icon&#45;&#45;right"><Expand /></el-icon>-->
        <!--					<el-icon v-show="isCardB==1" class="el-icon&#45;&#45;right"><Fold /></el-icon>-->
      </el-button>
      <div style="height:0px;"></div>
      <el-button v-show="isCardB==1" class="aside-inbtn" color="#55a684" plain @click="DocPatChat"
                 :icon="Collection">
        消息中心&emsp;&emsp;&emsp;&emsp;&emsp;
      </el-button>
      <div style="height:0px;"></div>
      <el-button v-show="isCardB==1" class="aside-inbtn" color="#55a684" plain @click="DoctorPatientBind()" :icon="Collection">
        医患绑定&emsp;&emsp;&emsp;&emsp;&emsp;
      </el-button>
    </el-card>
  </el-row>
  <div style="height:37px;"></div>

  <el-row>
    <el-card class="community-card-full" style="margin-left: 2px; box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);">
      <el-button type="text" style="width: 236px; font-size: 20px; color:#000000; background-color: white;"
                 @click="isDrugFull(),DrugHome()">
        药物指南&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
        <book-one class="el-icon&#45;&#45;right" theme="outline" size="20" fill="#333" strokeLinecap="butt" />
<!--        <p>{{isCardC}}</p>-->
<!--        <book-one v-show="isCardC==0" class="el-icon&#45;&#45;right" theme="outline" size="20" fill="#333" strokeLinecap="butt" />-->
<!--        <book-open v-show="isCardC==1" class="el-icon&#45;&#45;right" theme="outline" size="20" fill="#333" strokeLinecap="butt"/>-->
        <!--					<el-icon class="el-icon&#45;&#45;right"><Expand /></el-icon>-->
      </el-button>
    </el-card>
  </el-row>
  <div style="height:37px;"></div>

  <el-row>
    <el-card class="community-card-full" style="margin-left: 2px; box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);">
      <el-button type="text" style="width: 236px; font-size: 20px; color:#000000; background-color: white;"
                 @click="FoodHome(),isFoodFull()">
        饮食百科&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
        <book-one class="el-icon&#45;&#45;right" theme="outline" size="20" fill="#333" strokeLinecap="butt" />
<!--        <book-one v-show="isCardD==0" class="el-icon&#45;&#45;right" theme="outline" size="20" fill="#333" strokeLinecap="butt" />-->
<!--        <book-open v-show="isCardD==1" class="el-icon&#45;&#45;right" theme="outline" size="20" fill="#333" strokeLinecap="butt"/>-->
<!--        <el-icon class="el-icon&#45;&#45;right">-->
<!--          <Expand/>-->
<!--        </el-icon>-->
      </el-button>
    </el-card>
  </el-row>
</template>

<script setup>
import {ref, reactive, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import axios from 'axios'
import {ExpandTextInput, CollapseTextInput, BookOne, BookOpen} from '@icon-park/vue-next';

const router = useRouter()
import {useRoute} from 'vue-router'
import BasicInfo from "@/components/BasicInfo.vue";

const route = useRoute()

import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
  Expand,
  Fold
} from '@element-plus/icons-vue'

const userId = ref(route.query?.userId)
var isCardA = ref("0")
var isCardB = ref("0")
var isCardC = ref("0")
var isCardD = ref("0")


function isComFull() {
  if (isCardA.value == 0) {
    isCardA.value = 1
    isCardB.value = 0
    isCardC.value = 0
    isCardD.value = 0
  } else if (isCardA.value == 1) {
    isCardA.value = 0
  }
}

function isDocFull() {
  if (isCardB.value == 0) {
    isCardA.value = 0
    isCardB.value = 1
    isCardC.value = 0
    isCardD.value = 0
  } else if (isCardB.value == 1) {
    isCardB.value = 0
  }
}

function isDrugFull() {
    isCardA.value = 0
    isCardB.value = 0
    isCardC.value = 1
    isCardD.value = 0
}

function isFoodFull() {
    isCardA.value = 0
    isCardB.value = 0
    isCardC.value = 0
    isCardD.value = 1
}

function CommunityCenter() {
  router.push({path: "/Community/CommunityCenter"})
}

function MyPost() {
  router.push({path: "/Community/MyPost"})
}

function MyCollect() {
  router.push({path: "/Community/Mycollect"})
}

function DocPatChat(){
  router.push({path: "/Community/DocPatChat"})
}

function DoctorPatientBind(){
  router.push({path: "/Community/DoctorPatientBind"})
}

function DrugHome() {
    // isCardA.value = 0
    // isCardB.value = 0
    // isCardC.value = 1
    // isCardD.value = 0
  router.push({path: "/Community/DrugHome"})
}

function FoodHome() {
  // isCardA.value = 0
  // isCardB.value = 0
  // isCardC.value = 0
  // isCardD.value = 1
  router.push({path: "/Community/FoodHome"})
}
</script>

<style>

.community-card-full {
  color: #000000;
  font-size: 20px;
  width: 280px;
  /* height: 172px; */
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  /*box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);*/
  /*margin-left: 10px;*/
}

.aside-inbtn {
  color: #55a684;
  font-size: 20px;
  text-align: center;
  width: 236px;
  height: 41px;
  border-radius: 15px;
  border: none;
  background: rgba(255, 255, 255, 1);
}

.aside-inbtn:focus {
  font-size: 20px;
  text-align: center;
  width: 236px;
  height: 41px;
  /* 	  opacity: 3; */
  border-radius: 15px;
  background-color: #55a684;
  color: #FFFFFF;
}

.aside-inbtn:active {
  font-size: 20px;
  text-align: center;
  width: 236px;
  height: 41px;
  border-radius: 15px;
  background-color: #55a684;
  color: #FFFFFF;
}

.aside-btn {
  color: #55a684;
  font-size: 20px;
  text-align: center;
  margin-top: 0px;
  width: 280px;
  height: 65px;
  opacity: 1;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
}

</style>