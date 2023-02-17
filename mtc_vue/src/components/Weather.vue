<!-- <template>
    <div id="he-plugin-simple"></div>
</template>
    
    <script>
    export default {
        
      created(){
        //和风天气插件调用
        window.WIDGET = {
          "CONFIG": {
            "modules": "012",
            "background": "5",
            "tmpColor": "000000",
            "tmpSize": "16",
            "cityColor": "000000",
            "citySize": "16",
            "aqiColor": "FFFFFF",
            "aqiSize": "16",
            "weatherIconSize": "24",
            "alertIconSize": "18",
            "padding": "10px 10px 10px 10px",
            "shadow": "0",
            "language": "zh",
            "borderRadius": "15",
            "fixed": "false",
            "vertical": "top",
            "horizontal": "left",
            "key": "b8a605b86ace49adaae133334f5dda1e"
            
            // "modules": "01234",
            // "background": "1",
            // "tmpColor": "FFFFFF",
            // "tmpSize": "16",
            // "cityColor": "FFFFFF",
            // "citySize": "16",
            // "aqiColor": "FFFFFF",
            // "aqiSize": "16",
            // "weatherIconSize": "24",
            // "alertIconSize": "18",
            // "padding": "10px 10px 10px 10px",
            // "shadow": "0",
            // "language": "auto",
            // "fixed": "false",
            // "vertical": "top",
            // "horizontal": "left",
            // "key": "373e001db2cf45a48e4eba7bd89340d9"
          }
        };
        (function (d) {
          var c = d.createElement('link');
          c.rel = 'stylesheet';
          c.href = 'https://widget.heweather.net/simple/static/css/he-simple.css?v=1.4.0';
          var s = d.createElement('script');
          s.src = 'https://widget.heweather.net/simple/static/js/he-simple.js?v=1.4.0';
          var sn = d.getElementsByTagName('script')[0];
          sn.parentNode.insertBefore(c, sn);
          sn.parentNode.insertBefore(s, sn);
        })(document);
      },
    }
      
</script>

<style>
he-plugin-simple {
    z-index: 10;
}
</style> -->

<template>
    <el-row>
      <div id="he-plugin-standard"></div>
    </el-row>
</template>
    
    <script>
    export default {
        
      created(){
        //和风天气插件调用
        window.WIDGET = {
          "CONFIG": {
            "layout": "1",
            "width": "350",
            "height": "135",
            "background": "5",
            "dataColor": "000000",
            "language": "zh",
            "borderRadius": "15",
            "key": "19f6a64966f4486ea79b15e5bd80d3d2"
          }
        };
        (function (d) {
        var c = d.createElement('link');
        c.rel = 'stylesheet';
        c.href = 'https://widget.qweather.net/standard/static/css/he-standard.css?v=1.4.0';
        var s = d.createElement('script');
        s.src = 'https://widget.qweather.net/standard/static/js/he-standard.js?v=1.4.0';
        var sn = d.getElementsByTagName('script')[0];
        sn.parentNode.insertBefore(c, sn);
        sn.parentNode.insertBefore(s, sn)
        })(document)
      },
    }
      
</script>

<style scoped>
#he-plugin-standard {
    margin-left: 125px;
    margin-top: -85px;
}
</style>