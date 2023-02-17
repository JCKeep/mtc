import axios from "axios";
import type {AxiosRequestConfig, AxiosResponse} from  "axios";
//引入加载动画
import {ElLoading} from 'element-plus'


let loading: any;

const startLoading = () => {
    interface Options {
        lock: boolean,
        text: string,
        background: string
    };

    const options: Options = {
        lock: true,
        text: "加载中",
        background: 'rgba(0,0,0,0.7)'
    }
    loading = ElLoading.service(options);
}

const endLoading = () => {
    loading.close();
}

//请求拦截
axios.interceptors.request.use((config: AxiosRequestConfig) => {
    //加载Loading
    startLoading();

    return config;
})


//响应拦截
axios.interceptors.response.use((response: AxiosResponse<any>) => {
    //结束Loading
    endLoading();

    return response;
}, error => {
    //结束Loading
    endLoading();
    //错误提醒


    return Promise.reject(error);
})

export default axios;
