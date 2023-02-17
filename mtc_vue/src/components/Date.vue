<template>
    <div class="timecolumn">
      <p class="boxDate">{{ dateYear }}</p>
      <p class="boxDay">{{ dateWeek }}</p>
      <p class="boxTime">{{ dateDay }}</p>
    </div>
 </template>
  
 <script>
 import dayjs from "dayjs";
 export default {
   data() {
     return {
       dateDay: null,
       dateYear: null,
       dateWeek: null,
       weekday: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
       timer: null,
     };
   },
   mounted() {
     this.timer = setInterval(() => {
       const date = dayjs(new Date());
       this.dateDay = date.format("HH:mm:ss");
       this.dateYear = date.format("YYYY-MM-DD");
       this.dateWeek = date.format(this.weekday[date.day()]);
     }, 1000);
   },
   beforeDestroy() {
     if (this.timer) {
       clearInterval(this.timer);
     }
   },
 };
 </script>
 
 <style scoped>
 .timecolumn{
    color: #000;
    align-items: center;
    padding-top: 20px;
    margin-left: 20px;

 }
 /* 星期几 */
 .boxDay{ 
    font-size: 18px;
    padding-left: 24px;
    padding-top: 7px;
 }
 .boxTime{
    font-size: 19px;
    padding-left: 15px;
    padding-top: 10px;
 }
 .boxDate{
    font-size: 19px;
    padding-left: 2px;

 }
 </style>
 