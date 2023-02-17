<template>
  <div class="diet-management">
    <div class="common-layout">
      <el-container>
        <el-header class="header">
          <TopBar></TopBar>
        </el-header>
        <el-container>
          <el-aside width="480px">
            <dietaside style="margin-left: 100px;"></dietaside>
          </el-aside>
          <el-main>
            <div style="height:60px;"></div>
            <p style="color:#56a785;font-size: 28px">饮食记录</p>
            <div style="height:20px;"></div>
            <div style="width: 92%; background-color: #dfede6; border-radius: 15px; padding: 20px 50px 12px 0">
              <div style="height:20px;"></div>
              <el-form
                  ref="dietformRef"
                  label-width="150px"
                  :model="dietform"
              >
                <el-form-item label="用餐日期" prop="dietdate" class="item input error_style"
                              :rules="[{
																   required: true,
																   message: '请选择用餐日期',
																   trigger: 'change',
																 }]">
                  <el-col :span="11">
                    <el-date-picker
                        v-model="dietform.dietdate"
                        type="date"
                        placeholder="Pick a date"
                        style="width: 260px"
                        value-format="YYYY-M-D"
                    />
                  </el-col>
                </el-form-item>
                <el-form-item label="用餐时间" prop="diettime" class="item input error_style"
                              :rules="[{
																				   required: true,
																				   message: '请选择用餐时间',
																				   trigger: 'change',
																				 }]"
                >
                  <el-select v-model="dietform.diettime" placeholder="用餐时间" style="width: 260px">
                    <el-option label="早餐" value="早餐"/>
                    <el-option label="中餐" value="中餐"/>
                    <el-option label="晚餐" value="晚餐"/>
                  </el-select>
                </el-form-item>
                <el-form-item label="食物名称" prop="foodname" class="item error_style"
                              :rules="[{
													        required: true,
													        message: '请输入食物名称',
													        trigger: 'blur',
													      }]"
                >
                  <el-input v-model="dietform.foodname" style="width: 260px" class="input" maxlength="10"
                            show-word-limit/>
                </el-form-item>
                <el-form-item label="食物克数" prop="foodweight" class="item error_style"
                              :rules="[{ type: 'number', message: '必须填入数字', trigger: 'blur'}]">
                  <el-popover
                      placement="right"
                      :width="380"
                      trigger="click"
                      content="食物热量,重量,含糖量,脂肪,蛋白质等数据只能填五位以下正整数."
                      popper-class="note_style"
                  >
                    <template #reference>
                      <el-button :icon="More" plain size="small" color="#56a785" circle
                                 style="margin-left: -130px; margin-top: 0px;"/>
                    </template>
                  </el-popover>

                  <el-input v-model.number="dietform.foodweight" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')"
                            style="width: 260px; margin-left: 106px;" class="input" maxlength="5">
                    <template #append>克</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="食物热量" prop="foodcalories" style=" float: left;" class="item error_style"
                              :rules="[{ type: 'number', message: '必须填入数字',  trigger: 'blur'}]">
                  <el-input v-model.number="dietform.foodcalories" style="width: 260px;"
                            onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" class="input" maxlength="5">
                    <template #append>cal</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="食物含糖量" prop="foodsugar" style=" margin-left: 50px;" class="item error_style"
                              :rules="[{ type: 'number', message: '必须填入数字', trigger: 'blur'}]">
                  <el-input v-model.number="dietform.foodsugar" style="width: 260px;"
                            onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" class="input" maxlength="5">
                    <template #append>克</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="食物脂肪" prop="foodfat" style=" float: left;" class="item error_style"
                              :rules="[{ type: 'number', message: '必须填入数字',  trigger: 'blur'}]">
                  <el-input v-model.number="dietform.foodfat" style="width: 260px;"
                            onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" class="input" maxlength="5">
                    <template #append>克</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="食物蛋白质" prop="foodprotein" style=" margin-left: 50px;" class="item error_style"
                              :rules="[{ type: 'number', message: '必须填入数字', trigger: 'blur'}]">
                  <el-input v-model.number="dietform.foodprotein" style="width: 260px;"
                            onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" class="input" maxlength="5">
                    <template #append>克</template>
                  </el-input>
                </el-form-item>
                <el-form-item label="信息备注" prop="desc" class="item">
                  <el-input v-model="dietform.desc" type="textarea" class="textarea" maxlength="100" show-word-limit/>
                </el-form-item>
                <el-form-item style="margin-left:150px;">
                  <el-button type="primary" color="#56a785"
                             style="width:25%;height:50px; margin-top:15px;font-size: 20px;" plain @click="ontest()">创建
                  </el-button>
                  <el-button @click="goBack" style="width:25%;height:50px; margin-top:15px;font-size: 20px;">返回
                  </el-button>
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
// 开始
import {
  More
} from '@element-plus/icons-vue'
import {ref, reactive, onMounted} from 'vue'
import {ElMessage} from 'element-plus';
import {useRouter} from 'vue-router'
import {useRoute} from 'vue-router'
import axios from 'axios'
import dietaside from '../../components/dietaside.vue'
import TopBar from '../../components/TopBar.vue'

const input = ref('')
const router = useRouter()
const route = useRoute()
const chosen = ref(route.query?.chosen)
const userId = ref(route.query?.userId)
const foodId = ref(route.query?.foodId)
const foodName = ref(route.query?.foodName)
const foodCalories = ref(route.query?.foodCalories)
const foodSugar = ref(route.query?.foodSugar)
const foodFat = ref(route.query?.foodFat)
const foodProtein = ref(route.query?.foodProtein)

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

if (chosen.value * 1 == 1) {
  dietform.diettime = "早餐"
}
if (chosen.value * 1 == 2) {
  dietform.diettime = "中餐"
}
if (chosen.value * 1 == 3) {
  dietform.diettime = "晚餐"
}

if (foodId.value * 1 > 0) {
  dietform.foodname = foodName,
      dietform.foodweight = 100,
      dietform.foodcalories = foodCalories.value * 1,
      dietform.foodsugar = foodSugar.value * 1,
      dietform.foodprotein = foodProtein.value * 1,
      dietform.foodfat = foodFat.value * 1
}
//form.foodname=chosen;

const ontest = () => {
  dietformRef.value.validate((valid) => {
    if (valid) {
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
      axios.get('http://114.132.226.110/api/mtc/diet/addRecord', {
        params: {
          userId: userId.value,
          dietDate: dietform.dietdate,
          dietTime: dietform.diettime,
          foodName: dietform.foodname,
          foodWeight: dietform.foodweight,
          foodCalories: dietform.foodcalories,
          foodSugar: dietform.foodsugar,
          foodProtein: dietform.foodprotein,
          foodFat: dietform.foodfat,
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
    } else {
      ElMessage.error('数据类型错误')
      return false
    }
  })
}


function goBack() {
  router.push({name: 'dietindex', query: {userId: userId.value}})
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

.item >>> .el-form-item__label {
  font-size: 22px;
}

.el-form-item {
  margin-bottom: 30px;
}

.textarea >>> .el-textarea__inner {
  font-family: "Microsoft";
  font-size: 20px;
}

.input >>> .el-input__inner {
  font-family: "Microsoft";
  font-size: 20px;
  height: 25px;
}

.error_style >>> .el-form-item__error {
  font-size: 20px;
}

.el-popover.note_style {
  font-size: 12px;
  height: 40px;
}
</style>
