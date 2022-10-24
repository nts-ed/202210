<template>
    <div class="layout">
        <el-container>
            <!-- <el-header></el-header> -->
            <el-main>
                <div class="loginContainer">
                    <div class="header">
                        <h3>倉庫管理システム</h3>
                    </div>
                    <div>
                        <el-form ref="loginForm" :model="loginForm" :rules="loginRules">
                            <el-form-item prop="username">
                                <el-input type="text" v-model.trim="loginForm.username" clearable placeholder="ユーザーID"
                                    auto-complete="off" prefix-icon="el-icon-user-solid"></el-input>
                            </el-form-item>
                            <el-form-item prop="password">
                                <el-input type="password" v-model="loginForm.password" clearable placeholder="パスワード"
                                    show-password auto-complete="off" prefix-icon="el-icon-lock"></el-input>
                            </el-form-item>
                        </el-form>
                    </div>
                    <div class="tool">
                        <div>
                            <el-checkbox v-model="checked" @change="remenberID">ユーザーIDとパスワードを保存する</el-checkbox>
                        </div>
                    </div>
                    <div class="button">
                        <el-button type="primary" @click.native.prevent="login('form')">ログイン</el-button>
                        <el-button class="" @click="register">新規登録</el-button>
                    </div>
                </div>
            </el-main>
        </el-container>



    </div>
</template>
  
<script>
// @ is an alias to /src

export default {
    name: 'LoginView',
    data() {
        return {
            loginForm: {
                password: '',
                username: '',
            },
            checked: false,
            loginRules: {
                username: [
                    { required: true, message: 'ユーザーIDを入力してください', trigger: 'blur' },
                    { max: 11, message: "11文字を超えて入力することはできません、11文字を入力してください", trigger: "blur" },
                ],
                password: [
                    { required: true, message: "パスワードを入力してください", trigger: "blur" },
                    { max: 50, message: "50文字を超えて入力することはできません、50文字を入力してください", trigger: "blur" },
                ],
            },
        };
    },
    mounted() {
        if (localStorage.getItem("news")) {
            this.form = JSON.parse(localStorage.getItem("news"))
            this.checked = true
        }
    },
    methods: {
        login(loginForm) {
            this.$refs.loginForm.validate((valid) => {
                if (valid) {
                    // login(this.loginForm)
                    this.$axios.post('/api1/login', {
                        userId: this.loginForm.username,
                        password: this.loginForm.password
                    })
                        .then(res => {
                            if (res.data.code === 200) {
                                sessionStorage.setItem("USERNAME", res.data.data.username);
                                this.$message({
                                    message: "ログインしました",
                                    type: "success",
                                    showClose: true,
                                });
                                this.$router.push({
                                    name: 'home',
                                })
                            } else if (res.data.code === 401 || res.data.code === 400) {
                                this.$message({
                                    message: res.data.message,
                                    type: "error",
                                    showClose: true,
                                });
                            }
                        })
                        .catch((err) => {
                            console.log(err);
                            this.$message({
                                message: "ユーザー名またはパスワードが間違っている",
                                type: "error",
                                showClose: true,
                            });
                        });
                } else {
                    return false;
                }
            });
        },
        remenberID(data) {
            this.checked = data
            if (this.checked) {
                localStorage.setItem("news", JSON.stringify(this.form))
            } else {
                localStorage.removeItem("news")
            }
        },
        register() {
            this.$message({
                type: "info",
                message: "開発中",
                showClose: true
            })
        },
    },
}
</script>

<style scoped>
.layout {
    background-image: url("../assets/img/bg.jpg");
    background-position: center;
    height: 100vh;
    width: 100%;
    background-size: cover;
    /* position: fixed; */
}

.loginContainer {
    width: 350px;
    border-radius: 15px;
    background-clip: padding-box;
    margin: 95px auto;
    padding: 25px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
}

.header {
    line-height: 20px;
    text-align: center;
    font-size: 20px;
    font-weight: bolder;
    margin: 0 auto 20px auto;
    color: #505458;
}

.tool {
    display: flex;
    justify-content: space-between;
    color: #606266;
}

.button {
    margin-top: 10px;
    text-align: center;
}
</style>
