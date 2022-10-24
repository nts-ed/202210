<template>
    <div class="layout">
        <el-row :gutter="0">
            <el-col :span="20" :offset="2" :xs="20" :sm="20" :md="20" :lg="20" :xl="20">
                <div class="grid-content">
                    <el-container class="container-shadow">
                        <el-header class="header">
                            <Header></Header>
                        </el-header>
                        <el-main class="main">
                            <div class="formContent">
                                <h2 class="h2title mb-30">単位登録</h2>

                                <el-form :model="unitForm" :rules="unitRules" ref="unitForm" label-width="120px"
                                    class="">
                                    <el-form-item label="単位名称" prop="name">
                                        <el-input v-model="unitForm.name"></el-input>
                                    </el-form-item>
                                    <el-form-item label="備考" prop="remarks">
                                        <el-input type="textarea" v-model="unitForm.remarks"></el-input>
                                    </el-form-item>
                                    <el-form-item>
                                        <el-button type="primary" @click="onSubmit('unitForm')"
                                            class="ml-0 mr-10 mb-10">登録
                                        </el-button>
                                        <el-button type="warning" @click="resetform()" class="ml-0 mr-10 mb-10">クリア
                                        </el-button>
                                        <el-button type="info" @click="handleBack()" class="ml-0 mr-10 mb-10">戻る
                                        </el-button>
                                    </el-form-item>
                                </el-form>
                            </div>
                        </el-main>
                        <el-footer>
                            <Footer></Footer>
                        </el-footer>
                    </el-container>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
  
<script>
// @ is an alias to /src
import Header from '@/components/Header.vue';
import Footer from '@/components/Footer.vue';

export default {
    name: 'AddUnit',
    components: {
        Header,
        Footer,
    },
    data() {
        let checkUnit = (rule, value, callback) => {
            setTimeout(() => {
                let reg = /^[\u4E00-\u9FA5]+$/;
                // if (!value) {
                //     return callback(new Error('単位名を入力してください'));
                // }
                if (!reg.test(value)) {
                    callback(new Error('漢字を入力してください'));
                } else if (this.checkExistingUnit(value)) {
                    callback(new Error('この単位はすでに存在します'));
                } else {
                    return callback();
                }
            }, 500);
        };
        return {
            unitForm: {
                name: '',
                create_user_id: '',
                update_user_id: '',
                remarks: '',
            },
            unitRules: {
                name: [
                    { required: true, message: '在庫名称を入力してください', trigger: 'blur' },
                    { min: 0, max: 50, message: '50位以下入力してください', trigger: 'blur' },
                    { validator: checkUnit, trigger: 'blur' }

                ],
            },
            fetchUnitOptions: [],
            isUnitExist: false,
            employee_info: {
                employee_id: '',
            },
            bg: [],
        }
    },
    mounted() {
        this.getUnit();
    },
    methods: {
        getUnit() {
            this.$store.dispatch('getUnitNames').then((res) => {
                this.fetchUnitOptions = this.$store.state.unitNameList.map(item => item.name);
            }).catch(err => console.log(err));
        },
        checkExistingUnit(value) {
            if ((this.fetchUnitOptions).indexOf(value) > -1) {
                //检查输入名称是否与fetchUnitOptions里面的相同
                console.log("単位は存在しました");
                this.isUnitExist = true;
                return true;
            } else {
                this.isUnitExist = false;
                return false;
            }
        },
        onSubmit(formName) {
            if (this.unitForm.name != null && this.unitForm.name !== "" && this.unitForm.name !== undefined) {
                if (this.isUnitExist) {
                    return;
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.unitForm.create_user_id = this.employee_info.employee_id;
                        this.unitForm.update_user_id = this.employee_info.employee_id;
                        this.$axios.post("/api1/units/add", this.unitForm).then((res) => {
                            if (res.data) {
                                this.$message.success("登録完了しました");
                                this.$router.push({
                                    name: 'home',
                                })
                            } else {
                                this.$message.error("エラー、登録できません");
                            }
                        })
                    } else {
                        this.$message.error("エラー、フォームに必要データがありません。データを入力してください。");
                        return false;
                    }
                });
            }
        },
        handleBack() {
            this.$router.push({
                name: 'addgoods',
            })
        },
        resetform() {
            this.unitForm = {
                name: '',
                create_user_id: '',
                create_date: new Date(),
                update_user_id: '',
                update_date: new Date(),
                remarks: '',
            }
        },
    },
    computed: {
    },
    filters: {},
}
</script>
  
<style scoped>
.el-col {
    border-radius: 4px;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}

.layout {
    background-image: url("../assets/img/bg3.jpg");
    background-position: center;
    min-height: 100vh;
    width: 100%;
    background-size: cover;
}

.container-shadow {
    box-shadow:
        -5px 5px 20px -4px #cac6c6, 5px 5px 20px -4px #cac6c6;

}

.header {
    background-clip: padding-box;
    padding: 10px 30px;
    background: #fff;
    border: 1px solid #eaeaea;
}

.main {
    background-clip: padding-box;
    padding: 25px 30px;
    background: #fff;
    border: 1px solid #eaeaea;

}

.el-footer {
    padding: 0;
}

.h2title {
    text-align: center;
}

.formContent {
    width: 50%;
    margin: 0 auto;
}
</style>