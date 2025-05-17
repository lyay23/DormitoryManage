import request from "@/utils/request";

const {ElMessage} = require("element-plus");
export default {
    name: "selfInfo",
    data() {
        // 手机号验证
        const checkPhone = (rule, value, callback) => {
            const phoneReg = /^1[3|4|5|6|7|8][0-9]{9}$/;
            if (!value) {
                return callback(new Error("电话号码不能为空"));
            }
            setTimeout(() => {
                if (!Number.isInteger(+value)) {
                    callback(new Error("请输入数字值"));
                } else {
                    if (phoneReg.test(value)) {
                        callback();
                    } else {
                        callback(new Error("电话号码格式不正确"));
                    }
                }
            }, 100);
        };
        const checkPass = (rule, value, callback) => {
            if (!this.editJudge) {
                console.log("验证");
                if (value == "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.form.password) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            } else {
                console.log("不验证");
                callback();
            }
        };
        return {
            showpassword: true,
            image: "",
            editJudge: true,
            disabled: true,
            dialogVisible: false,
            identity: "",
            username: "",
            name: "",
            gender: "",
            age: "",
            phoneNum: "",
            email: "",
            targetURL: "",
            avatar: "",
            form: {
                username: "",
                name: "",
                gender: "",
                age: "",
                phoneNum: "",
                email: "",
            },
            rules: {
                username: [
                    {required: true, message: "请输入账号", trigger: "blur"},
                    {
                        pattern: /^[a-zA-Z0-9]{4,9}$/,
                        message: "必须由 4 到 9 个字母或数字组成",
                        trigger: "blur",
                    },
                ],
                name: [
                    {required: true, message: "请输入姓名", trigger: "blur"},
                    {
                        pattern: /^(?:[\u4E00-\u9FA5·]{2,10})$/,
                        message: "必须由 2 到 10 个汉字组成",
                        trigger: "blur",
                    },
                ],
                gender: [{required: true, message: "请选择性别", trigger: "change"}],
                age: [
                    {required: true, message: "请输入年龄", trigger: "blur"},
                    {type: "number", message: "年龄必须为数字值", trigger: "blur"},
                    {
                        pattern: /^(1|[1-9]\d?|100)$/,
                        message: "范围：1-100",
                        trigger: "blur",
                    },
                ],
                phoneNum: [{required: true, validator: checkPhone, trigger: "blur"}],
                email: [
                    {type: "email", message: "请输入正确的邮箱地址", trigger: "blur"},
                ],
                password: [
                    {required: true, message: "请输入密码", trigger: "blur"},
                    {
                        min: 6,
                        max: 32,
                        message: "长度在 6 到 16 个字符",
                        trigger: "blur",
                    },
                ],
                checkPass: [{validator: checkPass, trigger: "blur"}],
            },
            display: {
                display: "none",
            },
            imgDisplay: {
                display: "none",
            },
        };
    },
    created() {
        this.load();
        this.find();
        this.init(this.avatar);
    },
    methods: {
        //获取个人信息页面信息
        load() {
            this.form = JSON.parse(sessionStorage.getItem("user"));
            this.identity = JSON.parse(sessionStorage.getItem("identity"));
            this.username = this.form.username;
            this.name = this.form.name;
            this.gender = this.form.gender;
            this.age = this.form.age;
            this.phoneNum = this.form.phoneNum;
            this.email = this.form.email;
            this.avatar = this.form.avatar;
        },
        //查询数据，更新session
        find() {
            this.form = JSON.parse(sessionStorage.getItem("user"));
            request.post("/" + this.identity + "/login", this.form).then((res) => {
                //更新sessionStorage
                window.sessionStorage.setItem("user", JSON.stringify(res.data));
                //更新页面数据
                this.load();
            });
        },
        Edit() {
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.$refs.form.resetFields();
                this.form = JSON.parse(sessionStorage.getItem("user"));
            });
        },
        cancel() {
            this.$refs.form.resetFields();
            this.display = {display: "none"};
            this.showpassword = true;
            this.editJudge = true;
            this.disabled = true;
            this.dialogVisible = false;
        },
        async save() {
            this.$refs.form.validate(async (valid) => {
                if (valid) {
                    //修改
                    await request.put("/" + this.identity + "/update", this.form).then((res) => {
                        if (res.code === "0") {
                            ElMessage({
                                message: "修改成功",
                                type: "success",
                            });
                            //更新sessionStorage
                            window.sessionStorage.setItem(
                                "user",
                                JSON.stringify(this.form)
                            );
                            this.find();
                            this.dialogVisible = false;
                        } else {
                            ElMessage({
                                message: res.msg,
                                type: "error",
                            });
                        }
                    });
                }
            });
        },
        EditPass() {
            if (this.editJudge) {
                this.display = {display: "flex"};
                this.showpassword = false;
                this.disabled = false;
                this.editJudge = false;
            } else {
                this.display = {display: "none"};
                this.showpassword = true;
                this.editJudge = true;
                this.disabled = true;
            }
        },
        //发送请求，获取头像
        async init(data) {
            if (data === null || data === "") {
                console.log("用户未设置头像");
                this.imgDisplay = {display: "none"};
            } else {
                this.imgDisplay = {display: "block"};
                console.log("头像名称：" + data);
                try {
                    const res = await request.get("/files/initAvatar/" + data);
                    console.log("获取头像响应:", res);
                    if (res.code === "0") {
                        this.image = res.data;  // 直接使用 res.data，因为后端返回的就是 Base64 字符串
                        console.log("头像数据设置成功");
                    } else {
                        console.error("获取头像失败:", res.msg);
                        ElMessage({
                            message: res.msg || "获取头像失败",
                            type: "error",
                        });
                    }
                } catch (error) {
                    console.error("获取头像请求失败:", error);
                    ElMessage({
                        message: "获取头像请求失败",
                        type: "error",
                    });
                }
            }
        },
        // el-upload 的 on-success 回调参数是 (response, file, fileList)
        // response 是 action URL (即 /files/upload) 的响应结果
        async uploadSuccess(response, file, fileList) {
            if (response && response.code === "0" && response.data) {
                const newFilename = response.data; // 从 /files/upload 接口获取新的头像文件名
                let currentUserInfo = JSON.parse(sessionStorage.getItem("user")); // 获取当前用户信息
                const identity = JSON.parse(sessionStorage.getItem("identity")); // 获取用户身份

                if (!currentUserInfo || !identity) {
                    ElMessage({
                        message: "无法获取用户信息或身份，请重新登录",
                        type: "error",
                    });
                    return;
                }

                // 构造请求体，确保包含用户标识，如 username
                // 后端 /uploadAvatar/{identity}/{filename} 接口的 @RequestBody 将接收这个对象
                // 服务层将使用此对象中的标识（如username）来更新记录
                const payload = {
                    username: currentUserInfo.username
                    // 根据实际情况，如果后端更新依赖其他唯一标识符，也应在此处加入
                    // 例如，如果学生表用 stuNum 作为更新依据而不是 username:
                    // if (identity === 'stu') { payload.stuNum = currentUserInfo.stuNum; }
                };

                // 构造更新头像信息的请求 URL
                const requestUrl = `/files/uploadAvatar/${identity}/${newFilename}`;

                await request.post(requestUrl, payload).then((updateRes) => {
                    if (updateRes.code === "0") {
                        ElMessage({
                            message: "设置成功",
                            type: "success",
                        });
                        
                        // 更新 sessionStorage 中的用户信息
                        currentUserInfo.avatar = newFilename; // updateRes.data 应该也是 newFilename
                        sessionStorage.setItem("user", JSON.stringify(currentUserInfo));

                        // 更新组件内部状态
                        this.avatar = newFilename;
                        this.form.avatar = newFilename; // 如果 this.form 被其他地方使用

                        console.log("头像更新成功：" + this.avatar);
                        // this.find(); // find() 会重新从后端请求数据并覆盖sessionStorage，可能会导致刚设置的头像又变回旧的，除非find能立刻返回新头像
                                     // 考虑到find()内部逻辑是先用旧form请求login，然后更新session，再load()，
                                     // 如果login接口不返回最新的avatar，这里可能会有问题。
                                     // 暂时注释掉，因为sessionStorage已经手动更新了。
                                     // 如果需要调用find()来刷新其他信息，要确保find()的逻辑不会覆盖新头像。
                                     // 或者，直接调用this.load()来从更新后的sessionStorage刷新页面数据
                        this.load(); // 从更新后的 sessionStorage 加载数据到 this.form 和其他组件变量
                        this.init(this.avatar); // 用新的文件名重新初始化头像显示
                    } else {
                        ElMessage({
                            message: updateRes.msg || "设置头像失败",
                            type: "error",
                        });
                    }
                }).catch(err => {
                    console.error("更新头像数据库时出错:", err);
                    ElMessage({
                        message: "请求后端更新头像失败",
                        type: "error",
                    });
                });
            } else {
                ElMessage({
                    message: (response && response.msg) || "文件上传失败或未返回有效文件名",
                    type: "error",
                });
            }
        },
    },
};