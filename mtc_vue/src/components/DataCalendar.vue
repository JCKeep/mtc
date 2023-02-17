<template>
  <el-calendar class="data-box">
    <template #date-cell="{ data }" >
      <p :class="data.isSelected ? 'is-selected' : ''" class="data-box-farm" >
        {{ data.day.split('-').slice(1).join('-') }}
        {{ data.isSelected ? '✔️' : '' }}
      </p>
    </template>
  </el-calendar>

</template>

<script>
import {reactive} from "vue";

const user = reactive({
  time:"",
})
</script>

<style scoped>
.is-selected {
  color: #414841;
}
.data-box{
  left: 0px;
  top: 0px;
  width: 688px;
  height: 375px;
  opacity: 1;
  border-radius: 12px;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px 30px 84px 0px rgba(19, 10, 46, 0.08), 0px 8px 32px 0px rgba(19, 10, 46, 0.07), 0px 3px 14px 0px rgba(19, 10, 46, 0.03), 0px 1px 3px 0px rgba(19, 10, 46, 0.13);
}
.el-calendar {
  --el-calendar-border: var(--el-table-border, 1px solid var(--el-border-color-lighter));
  --el-calendar-header-border-bottom: var(--el-calendar-border);
  --el-calendar-selected-bg-color: var(--el-color-primary-light-9);
  --el-calendar-cell-width: 40px;
  background-color: var(--el-fill-color-blank);
}
body {
  min-height: 100vh;
  color: #55a684;
  background: var(--color-background);
  transition: color 0.5s, background-color 0.5s;
  line-height: 1.6;
  font-family: Inter, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
  font-size: 15px;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.el-calendar-table .el-calendar-day:hover {
  cursor: pointer;
  background-color: rgba(85, 166, 132, 0.42);
}
/*日期背景颜色*/
.el-calendar {
  --el-calendar-border: var(--el-table-border, 1px solid var(--el-border-color-lighter));
  --el-calendar-header-border-bottom: var(--el-calendar-border);
  --el-calendar-selected-bg-color: rgba(85, 166, 132, 0.43);
  --el-calendar-cell-width: 40px;
  background-color: var(--el-fill-color-blank);
}
/*当前日期字体颜色*/
.el-calendar-table td.is-today {
  color: #414841;
}
</style>
