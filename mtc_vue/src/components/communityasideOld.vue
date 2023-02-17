<template>
	<div style="height:30px;"></div>
<el-button class="community_box community_aside_text" color="#dfede6" @click="goDruginfo">药物指南<el-icon class="el-icon--right"><Memo /></el-icon></el-button>
	<div style="height:1px;"></div>
<el-button class="community_box community_aside_text" color="#dfede6" @click="goFoodinfo">饮食百科<el-icon class="el-icon--right"><Collection /></el-icon></el-button>

</template>

<script setup>
	import { ref, reactive, onMounted } from 'vue'
	import { useRouter } from 'vue-router'
	import { useRoute } from 'vue-router'
	import axios from 'axios'
	const router = useRouter()
	const route = useRoute()
import { Memo, Collection} from '@element-plus/icons-vue'

//const userId = ref(route.query?.userId)
const userId = ref('1')

	function goDruginfo() {
	  router.push({ path:"/Community/DrugHome"})
	}
	function goFoodinfo() {
	  router.push({ name: '', query: {userId:userId.value}})
	}
</script>

<style>
	.community_aside_text {
/* 	  color: #121a2d; */
	  font-size: 20px;
/* 	  text-align: center; */
	  margin: 5px 5px;
	}
	
	.community_box {
		margin-top: 35px;
		height: 57px;
	  width: 280px;
	  background-color: white;
	  border-radius: 15px;
			box-shadow: 5px 5px 5px 0 #d3d3d3;
	}

</style>