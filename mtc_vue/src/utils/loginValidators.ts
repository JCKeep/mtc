import {ref} from "vue";

// 进行类型的匹配
interface User {
    email: string,
    password: string
}

var validatePass = (rule:any, value:any, callback:any) => {
    //必须包含⼤⼩写字母、数字、特殊字符长度再9-16位之间
    var regex = new RegExp("(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,19}");
    if (value === "") {
        callback(new Error("请输⼊密码"));
    } else if (value.length < 6 || value.length > 19) {
        callback(new Error("请输⼊6~19位密码"));
    } else if (!regex.test(value)) {
        callback(new Error("密码必须同时包含字母、数字和特殊字符其中三项且⾄少6位"));
    } else {
        callback();
    }
};

//
// export const verifyPassword = (value: any) => {
//     const regs = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z]).{6,20}$/;
//     if (!regs.test(value)) {
//         return true;
//     }
//     return false;
// };

export const loginUser = ref<User>({
    email: "",
    password: "",
});

interface Rules {
    email:({
        required: boolean,
        message: string,
        trigger: string
    }| {
        type: string,
        message: string,
        required: boolean,
        trigger: string
    })[],
    password: ({
        required: boolean,
        message: string,
        trigger: string,
        ax?: undefined,
        min?: undefined
    } | {
        max: number,
        min: number,
        message: string,
        trigger: string,
        required?: undefined
    }  | {
        validator:(rule:Rules,
                   value:string,
                   callback:any)=>void;
        trigger: string;
    })[];
}

// 校验规则
export const rules = ref<Rules>({
    email: [
        {
            required:true,
            message:"邮箱账户不能为空",
            trigger:'blur'
        },
        {
            type: "email",
            message: "邮箱格式有误",
            required: true,
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: "请确认密码",
            trigger: "blur",
        },
        {
            validator: validatePass, trigger: "blur"
        }
        // {
        //     pattern:/^(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z]).{6,20}$/,
        //     message:"密码必须同时包含字母、数字和特殊字符其中三项且⾄少9位",
        //     trigger:'blur'
        // },
        // {
        //     pattern: /^[^\s]*$/,
        //     message: "不能输入空格",
        //     trigger: "change",
        // }

    ],
});