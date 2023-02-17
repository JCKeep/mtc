import {reactive, ref} from "vue";

interface RegisterUser {
    name: string,
    email: string,
    password: string,
    password2: string,
    identify:string,
    code:string,
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

interface RegisterRules {
    name: ({
        message: string,
        required: boolean,
        trigger: string,
    } | {
        max: number,
        min: number,
        message: string,
        trigger: string,
    })[],
    email: ({
        required: boolean,
        message: string,
        trigger: string
    }|{
        type: string,
        message: string,
        required: boolean,
        trigger: string
    })[];
    password: ({
        required: boolean,
        message: string,
        trigger: string,
    } | {
        min: number;
        max: number;
        message: string;
        trigger: string;
    }| {
        validator:(rule:RegisterRules,
                   value:string,
                   callback:any)=>void;
        trigger: string;
    })[];
    password2: ({
        required: boolean,
        message: string,
        trigger: string,
    } | {
        min: number,
        max: number,
        message: string,
        trigger: string,
    } | {
        validator:(rule:RegisterRules,
            value:string,
            callback:any)=>void;
        trigger: string;
    })[];
    identify:({
        required: boolean,
        message: string,
        trigger: string,
    })[];
}

export const registerUser = ref<RegisterUser>({
    name: "",
    email: "",
    password: "",
    password2: "",
    identify:"",
    code:"",
});

// export const registerUser = reactive({
//     name: "",
//     email: "",
//     password: "",
//     password2: "",
//     identify:"",
//     code:"",
// })

const validatePass2 = (rule: RegisterRules, value: string, callback: any) => {
    if (value === '') {
        console.log(registerUser.value.password)
        callback(new Error('请再次输入密码'))
    } else if (value != registerUser.value.password) {
        console.log(registerUser.value.password)
        console.log(value)
        callback(new Error("两次输入的密码不一致"))
    } else {
        callback()
    }
};

export const registerRules = ref<RegisterRules>({

    name: [
        {
            message: "用户名不能为空",
            required: true,
            trigger: 'blur'
        },
        {
            max: 30,
            min: 2,
            message: "名字的长度为2-30",
            trigger: 'blur'
        }
    ],
    email: [
        {
            required: true,
            message: "邮箱账户不能为空",
            trigger: 'blur'
        },
        {
            type: "email",
            message: "邮箱格式错误",
            required: true,
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: "请输入密码",
            trigger: "blur",
        },
        {
            validator: validatePass, trigger: "blur"
        }
    ],
    password2: [
        {
            required: true,
            message: "请确认密码",
            trigger: "blur",
        },
        {
            validator: validatePass, trigger: "blur"
        },
        {validator: validatePass2, trigger: "blur"},
    ],

    identify: [
        {
            required: true,
            message: "请输入验证码",
            trigger: 'blur',
        },
    ],
})