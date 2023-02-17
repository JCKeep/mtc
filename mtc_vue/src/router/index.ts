import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import('../views/myHealthViews/LoginRegister.vue')
    },
    {
      path: '/login',
      name: 'Login',
      meta:{
        withoutP:true
      },
      component: () => import('../views/myHealthViews/LoginRegister.vue')
    },
    {
      path: '/login/ForgetPasswd',
      name: 'ForgetPasswd',
      meta:{
        withoutP:true
      },
      component: () => import('../views/ForgetPassword.vue')
    },
    {
      path: '/Myhealth',
      name: 'Myhealth',
      component: () => import('../views/myHealthViews/MyHealth.vue')
    },
    {
      path: '/Myhealth/Recording',
      name: 'Recording',
      component: () => import('../views/myHealthViews/RecordingFhysicalData.vue')
    },
    {
      path: '/Myhealth/ExportData',
      name: 'ExportData',
      component: () => import('../views/myHealthViews/ExportHealthData.vue')
    },
    {
      path: '/Myhealth/HealthAdvice',
      name: 'HealthAdvice',
      component: () => import('../views/myHealthViews/HealthAdvice.vue')
    },

    {
      path: '/Myhealth/BloodsugarPrediction',
      name: 'BloodsugarPrediction',
      component: () => import('../views/myHealthViews/BloodsugarPrediction.vue')
    },

    {
      path: '/PerCenter/ChangePas',
      name: 'ChangePas',
      component: () => import('../views/PersonalCenter/ChangePassword.vue')
    },
    {
      path: '/PerCenter/MyAvatar',
      name: 'MyAvatar',
      component: () => import('../views/PersonalCenter/MyAvatar.vue')
    },
    {
      path: '/PerCenter/UserPor',
      name: 'UserPor',
      component: () => import('../views/PersonalCenter/UserPortrait.vue')
    },
    {
      path: '/PerCenter/BasicInfor',
      name: 'BasicInfor',
      component: () => import('../views/PersonalCenter/BasicInformation.vue')
    },
    {
      path: '/PerCenter/DoctorCertification',
      name: 'DoctorCertification',
      component: () => import('../views/PersonalCenter/DoctorCertification.vue')
    },


    {
      path: '/Community',
      name: 'Community',
      component: () => import('../views/myCommunityViews/DrugHome.vue')
    },

    {
      path: '/Community/DrugHome',
      name: 'DrugHome',
      component: () => import('../views/myCommunityViews/DrugHome.vue')
    },

    {
      path: '/Community/DrugDetails',
      name: 'DrugDetails',
      component: () => import('../views/myCommunityViews/DrugDetails.vue')
    },

    {
      path: '/Community/FoodHome',
      name: 'FoodHome',
      component: () => import('../views/myCommunityViews/FoodHome.vue')
    },

    {
      path: '/Community/FoodDetails',
      name: 'FoodDetails',
      component: () => import('../views/myCommunityViews/FoodDetails.vue')
    },

    {
      path: '/Community/DocPatChat',
      name: 'DocPatChat',
      component: () => import('../views/myCommunityViews/DocPatChat.vue')
    },

    {
      path: '/Community/DoctorPatientBind',
      name: 'DoctorPatientBind',
      component: () => import('../views/myCommunityViews/DoctorPatientBind.vue')
    },

    {
      path: '/test',
      name: 'test',
      meta:{
        withoutP:true
      },
      component: () => import('../views/test.vue')
    },
    {
      path: '/test2',
      name: 'test2',
      meta:{
        withoutP:true
      },
      component: () => import('../views/test2.vue')
    },
    {
      path: '/test3',
      name: 'test3',
      meta:{
        withoutP:true
      },
      component: () => import('../views/test3.vue')
    },

    {
      path: '/:catchAll(.*)',
      name: '/404',
      meta:{
        withoutP:true
      },
      component: () => import('../views/404.vue')
    },


    {
      path: '/diet/index',
      name: 'dietindex',
      component:() => import('../views/dietviews/dietindex.vue')
    },
    {
      path: '/diet/result',
      name: 'imageresult',
      component: () => import('../views/dietviews/imageresult.vue')
    },
    {
      path: '/diet/record',
      name: 'dietrecord',
      component: () => import('../views/dietviews/dietrecord.vue')
    },
    {
      path: '/diet/recommand',
      name: 'recommand',
      component: () => import('../views/dietviews/foodrecommand.vue')
    },
    {
      path: '/diet/searchrecord',
      name: 'searchrecord',
      component: () => import('../views/dietviews/searchrecord.vue')
    },
    {
      path: '/diet/choosediet',
      name: 'choosediet',
      component: () => import('../views/dietviews/choosediet.vue')
    },
    {
      path: '/diet/editrecord',
      name: 'editrecord',
      component: () => import('../views/dietviews/editrecord.vue')
    },
  ]
})

export default router
