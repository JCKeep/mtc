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
                  上一步
                </el-button>
              </el-col>
              <el-col :span="14" style="text-align: center;">
                <p style="color:#56a785;font-size: 28px">查看饮食记录</p>
              </el-col>
              <el-col :span="5">
                <el-button @click="nextStep"
                           style="background-color:#56a785; color:white; float:right; width:120px; height:34px; font-size:20px;">返回
                </el-button>
              </el-col>
            </el-row>
            <div style="height:60px;"></div>
            <div>
              <el-row :gutter="30">
                <el-col :span="5"/>
                <el-col :span="14">
                  <el-steps :space="400" :active="2" finish-status="success" align-center>
                    <el-step title="选择时间"/>
                    <el-step title="选择记录"/>
                    <el-step title="修改记录"/>
                  </el-steps>
                </el-col>
                <el-col :span="5"/>
              </el-row>
            </div>
            <div style="height:30px;"></div>
            <el-row>
              <el-col :span="4"/>
              <el-col :span="16">
                <div style="width: 100%; background-color: #dfede6; border-radius: 15px; padding: 20px">
                  <el-form
                      ref="dietformRef"
                      label-width="150px"
                      :model="dietform"
                      >
                    <el-form-item label="用餐日期" prop="dietdate" class="item input"
                                  :rules="[{
																   required: true,
																   message: '请选择用餐日期',
																   trigger: 'change',
																 }]">
                      <el-col :span="11">
                        <el-date-picker
                            v-model="dietform.dietdate"
                            type="date"
                            placeholder="选择一个日期"
                            style="width: 280px"
                            value-format="YYYY-M-D"
                        />
                      </el-col>

                    </el-form-item>
                    <el-form-item label="用餐时间" prop="diettime" class="item input"
                                  :rules="[{
																				   required: true,
																				   message: '请选择用餐时间',
																				   trigger: 'change',
																				 }]"
                    >
                      <el-select v-model="dietform.diettime" placeholder="用餐时间" style="width: 280px">
                        <el-option label="早餐" value="早餐"/>
                        <el-option label="中餐" value="中餐"/>
                        <el-option label="晚餐" value="晚餐"/>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="食物名称" prop="foodname" class="item"
                                  :rules="[{
													        required: true,
													        message: '请输入食物名称',
													        trigger: 'blur',
													      }]"
                    >
                      <el-input v-model="dietform.foodname" style="width: 280px" class="input" maxlength="10" show-word-limit/>
                    </el-form-item>
                    <el-form-item label="食物克数" prop="foodweight" class="item"
                                  :rules="[{ type: 'number', message: '必须填入数字', trigger: 'blur'}]">
                      <el-popover
                        placement="right"
                        :width="380"
                        trigger="click"
                        content="食物热量,重量,含糖量,脂肪,蛋白质等数据只能填五位以下正整数."
                      		popper-class="note_style"
                      >
                        <template #reference>
                          <el-button :icon="More" plain size="small" color="#56a785" circle style="margin-left: -130px; margin-top: 0px;"/>
                        </template>
                      </el-popover>
                      
																						<el-input v-model.number="dietform.foodweight" style="width: 280px; margin-left: 106px;" class="input" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
                        <template #append>克</template>
                      </el-input>
                    </el-form-item>
                    <el-form-item label="食物热量" prop="foodcalories" style=" float: left;" class="item"
                                  :rules="[{ type: 'number', message: '必须填入数字',  trigger: 'blur'}]">
                      <el-input v-model.number="dietform.foodcalories" style="width: 280px;" class="input" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
                        <template #append>cal</template>
                      </el-input>
                    </el-form-item>
                    <el-form-item label="食物含糖量" prop="foodsugar" style=" margin-left: 50px;" class="item"
                                  :rules="[{ type: 'number', message: '必须填入数字', trigger: 'blur'}]">
                      <el-input v-model.number="dietform.foodsugar" style="width: 280px;" class="input" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
                        <template #append>克</template>
                      </el-input>
                    </el-form-item>
                    <el-form-item label="食物脂肪" prop="foodfat" style=" float: left;" class="item"
                                  :rules="[{ type: 'number', message: '必须填入数字',  trigger: 'blur'}]">
                      <el-input v-model.number="dietform.foodfat" style="width: 280px;" class="input" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
                        <template #append>克</template>
                      </el-input>
                    </el-form-item>
                    <el-form-item label="食物蛋白质" prop="foodprotein" style=" margin-left: 50px;" class="item"
                                  :rules="[{ type: 'number', message: '必须填入数字', trigger: 'blur'}]">
                      <el-input v-model.number="dietform.foodprotein" style="width: 280px;" class="input" maxlength="5" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
                        <template #append>克</template>
                      </el-input>
                    </el-form-item>
                    <el-form-item label="信息备注" prop="desc" class="item">
                      <el-input v-model="dietform.desc" type="textarea" class="text" style="width: 710px;" maxlength="100" show-word-limit/>
                    </el-form-item>
                    <el-form-item style="margin-left:150px;">
                      <el-button type="primary" color="#ff6374"
                                 style="width:25%;height:50px; margin-top:15px;font-size: 20px;" plain
                                 @click="deleterecord()">删除
                      </el-button>
                      <el-button color="#56a785" style="width:25%;height:50px; margin-top:15px;font-size: 20px;" plain
                                 @click="goEdit()">修改
                      </el-button>
                    </el-form-item>
                  </el-form>
                </div>
              </el-col>
              <el-col :span="4"/>
            </el-row>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script setup>
import {
  Edit,
  Plus,
		More
} from '@element-plus/icons-vue'
import {ref, reactive, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'
import axios from 'axios'
import {ElMessage, ElMessageBox} from 'element-plus';
import TopBar from '../../components/TopBar.vue'

const router = useRouter()
const route = useRoute()

const userId = ref(route.query?.userId)
const dietId = ref(route.query?.dietId)

let datevalue = ref('')

function goBack() {
  router.push({name: 'searchrecord', query: {userId: userId.value}})
}

function nextStep() {
  router.push({name: 'dietindex', query: {userId: userId.value}})
}

const dietform = reactive({
  dietdate: '',
  diettime: '',
  foodname: '',
  foodweight: '',
  foodcalories: '',
  foodsugar: '',
  foodprotein: '',
  foodfat: '',
  desc: ''
})
const dietformRef = ref(null);
const dateValue = ref();
onMounted(() => {
  axios.get('http://114.132.226.110/api/mtc/diet/information', {params: {userId: userId.value, dietId: dietId.value}})
      .then(function (res) {
        console.log(res.data)
        const data = res.data.object
        dietform.foodname = data.foodName,
            dietform.dietdate = data.dietDate,
            // datevalue = data.dietDate.substr(0,10)+1,
            // dietform.dietdate = datevalue,
            dietform.diettime = data.dietTime,
            dietform.foodweight = data.foodWeight,
            dietform.foodcalories = data.foodCalories,
            dietform.foodsugar = data.foodSugar,
            dietform.foodprotein = data.foodProtein,
            dietform.foodfat = data.foodFat,
            dietform.desc = data.desc
      })
})

const goEdit = () => {
  dietformRef.value.validate((valid) => {
			if (valid) {
			ElMessageBox.confirm(
			  '确定要修改此条饮食记录吗？',
			  'Warning',
			  {
			    confirmButtonText: '确定',
			    cancelButtonText: '取消',
			    type: 'warning'
			  }
			)
			.then(() => {
				if (dietform.foodweight == null || dietform.foodweight == "") {
				  dietform.foodweight = -1;
				}
				if (dietform.foodcalories == null || dietform.foodcalories == "") {
				  dietform.foodcalories = -1;
				}
				if (dietform.foodsugar == null || dietform.foodsugar == "") {
				  dietform.foodsugar = -1;
				}
				if (dietform.foodprotein == null || dietform.foodprotein == "") {
				  dietform.foodprotein = -1;
				}
				if (dietform.foodfat == null || dietform.foodfat == "") {
				  dietform.foodfat = -1;
				}
				if (dietform.desc == null || dietform.desc == "") {
				  dietform.desc = -1;
				}
				axios.get('http://114.132.226.110/api/mtc/diet/updateRecord', {
				  params: {
				    dietId: dietId.value,
				    dietDate: dietform.dietdate,
				    dietTime: dietform.diettime,
				    foodName: dietform.foodname,
				    foodWeight: dietform.foodweight,
				    foodCalories: dietform.foodcalories,
				    foodSugar: dietform.foodsugar,
				    foodFat: dietform.foodfat,
				    foodProtein: dietform.foodprotein,
				    desc: dietform.desc
				  }
				})
				    .then(function (response) {
				      console.log(response);
				    })
				    .catch(function (error) {
				      console.log(error);
				    });
				ElMessage.success('成功上传！')
				router.push({name: 'dietindex', query: {userId: userId.value}})
			})
			.catch(() => {
			  ElMessage({
			    type: 'info',
			    message: '修改失败',
			  })
					})
	}
   else {
      ElMessage.error('数据类型错误！')
      return false
    }
  })
		}

/* const deleterecord = () => {
  axios.get('http://114.132.226.110/api/mtc/diet/deleteRecord', {params: {dietId: dietId.value}})
      .then(function (res) {
        console.log(res.data)
      })
  ElMessage.success('删除成功！')
  router.push({name: 'dietindex', query: {userId: userId.value}})
} */

const deleterecord = () => {
  ElMessageBox.confirm(
    '确定要删除此条饮食记录吗？',
    'Warning',
    {
      confirmButtonText: '确定删除',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
					axios.get('http://114.132.226.110/api/mtc/diet/deleteRecord', {params: {dietId: dietId.value}})
					    .then(function (res) {
					      console.log(res.data)
					    })
      ElMessage({
        type: 'success',
        message: '删除成功！',
      })
						router.push({name: 'dietindex', query: {userId: userId.value}})
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}

</script>

<style scoped>
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

.common-layout {
  overflow: hidden;
}

.header {
  margin-left: 0;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.25);
}

.header[data-v-541e628a] {
   margin-left: 157px;
  border-radius: 15px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 2px 4px 0px rgb(0 0 0 / 25%);
}

.text >>> .el-textarea__inner {
  font-family: "Microsoft";
  font-size: 20px;
}

.input >>> .el-input__inner {
  font-family: "Microsoft";
  font-size: 20px;
}

.error_style .el-form-item__error{
	font-size: 20px;
}

.el-popover.note_style{
	font-size: 12px;
	height: 40px;
}

</style>
