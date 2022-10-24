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
                                <h2 class="h2title mb-30">入出庫情報登録</h2>
                                <el-form :model="ioForm" :rules="ioRules" ref="ioForm" label-width="120px" class="">
                                    <el-form-item label="在庫ID" prop="id">
                                        <el-input v-model="ioForm.id" readonly class="ioreadonly">
                                        </el-input>
                                    </el-form-item>
                                    <el-form-item label="在庫名称" prop="name">
                                        <el-input v-model="ioForm.name" readonly class="ioreadonly"></el-input>
                                    </el-form-item>
                                    <el-form-item label="単位" prop="unit_name">
                                        <el-input v-model="ioForm.unitName" readonly class="ioreadonly"></el-input>
                                    </el-form-item>
                                    <el-form-item label="在庫数量" prop="stock_num">
                                        <el-input v-model.number="ioForm.stockNum" readonly class="ioreadonly">
                                        </el-input>
                                    </el-form-item>
                                    <el-form-item label="入出庫タイプ" prop="io_type">
                                        <el-select v-model.number="ioForm.ioType" clearable placeholder="入出庫タイプ選択">
                                            <el-option v-for="item in ioTypeOptions" :key="item.unitId"
                                                :label="item.name" :value="item.unitId">
                                            </el-option>
                                        </el-select>
                                    </el-form-item>
                                    <el-form-item label="入出庫数量" prop="io_num">
                                        <el-input v-model.number="ioForm.ioNum"></el-input>
                                    </el-form-item>

                                    <el-form-item label="備考" prop="remarks">
                                        <el-input type="textarea" v-model="ioForm.remarks"></el-input>
                                    </el-form-item>
                                    <el-form-item>
                                        <el-button type="primary" @click="onSubmit('ioForm')" class="ml-0 mr-10 mb-10">
                                            登録</el-button>
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
    name: 'AddIOView',
    components: {
        Header,
        Footer,
    },
    data() {
        return {
            ioForm: {
                id: '',
                // name: '',
                // unitName: '',
                // stockNum: '',
                inOutNo: 0,
                ioType: null,
                ioNum: null,
                remarks: '',
                createUser: '',
                updateUser: '',
                delFlg: 0,
            },
            ioRules: {
                ioType: [
                    { type: 'number', required: true, message: 'タイプを選択してください', trigger: 'blur' }

                ],
                ioNum: [
                    { min: 0, max: 999999999, type: 'number', required: true, message: '0以上数字を入力してください', trigger: 'blur' },
                    { required: true, message: '数字を入力してください', trigger: 'blur' }
                ],

            },
            ioTypeOptions: [],
            employee_info: {
                employee_id: '',
            }
        }
    },
    mounted() {
        this.getStockType();
        this.getIODetails();
    },
    methods: {
        async getStockType() {
            await this.$axios.get("/api1/codes").then((res) => {
                this.ioTypeOptions = res.data.data.map((item, index) => { return Object.assign({}, { 'unitId': item.codeId, 'name': item.name }) })
            })
        },
        getIODetails() {
            this.ioForm.id = this.$route.params.stock_id;

            if (this.ioForm.id != null) {
                this.$axios.get("/api1/stocks/" + this.ioForm.id).then((res) => {
                    this.ioForm.name = res.data.data.name;
                    this.ioForm.unitName = res.data.data.unitId;
                    this.ioForm.stockNum = res.data.data.stockNum;
                }).catch(err => console.log(err));
                this.$axios.get("/api1/stocks/io/" + this.ioForm.id).then((res) => {
                    if (res.data) {
                        this.ioForm.inOutNo = res.data.data.inOutNo + 1;
                    }
                }).catch(err => console.log(err));
            }
        },
        onSubmit(formName) {
            if (this.ioForm.ioType != null) {
                this.ioForm.create_user_id = this.employee_info.employee_id;
                this.ioForm.update_user_id = this.employee_info.employee_id;

                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.get("/api1/stocks/io/" + this.ioForm.id).then((res) => {
                            if (res.data) {
                                console.log(res);
                                // this.ioForm.inOutNo = 
                            }
                        })
                        this.ioForm.inOutNo += 1;
                        this.$axios.post("/api1/stocks/io/add", this.ioForm).then((res) => {
                            if (res.data) {
                                this.$message.success("登録完了しました");
                                this.$router.push({
                                    name: 'ioinfo',
                                    params: { stock_id: this.ioForm.id, name: this.ioForm.name, io_num: this.ioForm.stockNum, unit_id: this.ioForm.unitId },
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
                name: 'ioinfo',
                params: { stock_id: this.ioForm.id, name: this.ioForm.name, io_num: this.ioForm.stockNum, unit_id: this.ioForm.unitId }
            })
        },
        resetform() {
            this.form = {}
        },
    },
    computed: {},
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
    background-image: url("../assets/img/bg2.jpg");
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

:deep(.ioreadonly) .el-input__inner {
    border: 0 !important;
    outline: none;
}
</style>