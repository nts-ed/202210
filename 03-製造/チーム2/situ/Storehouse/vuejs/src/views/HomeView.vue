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
              <div class="mt-10 mb-30">
                <div class="floatLeft">
                  <h2>在庫情報一覧</h2>
                </div>
                <div class="floatRight">
                  <el-button class="ml-5" type="info" @click="handleBack()">ログアウト</el-button>
                </div>
                <div class="clearBoth"></div>
              </div>
              <div class="mb-20">
                <el-form :inline="true" :model="searchForm" ref="searchForm">
                  <el-form-item label="在庫名称" class="mr-20">
                    <el-input v-model="searchForm.name"></el-input>
                  </el-form-item>
                  <el-form-item label="在庫状況" class="mr-20">
                    <el-select v-model="searchForm.stockType" clearable placeholder="在庫状況選択">
                      <el-option v-for=" item in stockTypeOptions" :key="item.value" :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <br>
                  <el-form-item label="更新日時" class="mr-20">
                    <el-date-picker v-model="searchForm.date" type="daterange" align="right" unlink-panels
                      range-separator="〜" start-placeholder="開始日" end-placeholder="終了日" :picker-options="pickerOptions">
                    </el-date-picker>
                  </el-form-item>

                  <el-button class="" type="primary" icon="el-icon-search" @click="searchStockInfo()">
                    検索
                  </el-button>
                  <div class="floatRight mb-20">
                    <el-button class="mr-20" type="success" @click="handleCreate()">追加<i
                        class="el-icon-circle-plus-outline ml-5"></i></el-button>
                    <el-button class="" type="danger" @click="handleDeleteAll([scope.row])">削除<i
                        class="el-icon-delete ml-5"></i>
                    </el-button>
                  </div>
                </el-form>
              </div>
              <div class="clearBoth"></div>

              <el-table :data="stockTableDataList" stripe border :default-sort="{prop: 'id', order: 'ascending'}">
                <el-table-column type="selection" align="center" width="54"></el-table-column>
                <el-table-column prop="id" align="center" label="在庫ID" sortable></el-table-column>
                <el-table-column prop="name" align="center" label="在庫名称" sortable></el-table-column>
                <el-table-column prop="unitId" align="center" label="単位" sortable><template v-slot="unitIdScope">
                    {{convertUnitName(unitIdScope.row.unitId)}}
                  </template></el-table-column>
                <el-table-column prop="stockNum" align="center" label="在庫数量" sortable></el-table-column>
                <el-table-column prop="updateUser" align="center" label="更新者"></el-table-column>
                <el-table-column prop="updateDate" align="center" label="更新日時" sortable><template
                    v-slot="updateDateScope">
                    {{ updateDateScope.row.updateDate | convertDate }}
                  </template></el-table-column>
                <el-table-column prop="remarks" align="center" label="備考"></el-table-column>
                <el-table-column align="center" label="操作">
                  <template v-slot="scope">
                    <el-button size="mini" type="warning" plain @click="handleEdit(scope.$index, scope.row)">編集 <i
                        class="el-icon-edit mr-5"></i></el-button>
                    <br>
                    <el-button size="mini" type="danger" plain @click="handleDelete(scope.$index, scope.row)"
                      class="mt-5">
                      削除 <i class="el-icon-delete mr-5"></i>
                    </el-button>
                    <br>
                    <el-button size="mini" type="info" plain @click="handleAddIO(scope.$index, scope.row)" class="mt-5">
                      入出庫 <i class="el-icon-shopping-cart-2"></i>
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
              <div class="pagination-container">
                <div class="block mt-5">
                  <el-pagination class="pagination" @current-change="handleCurrentChange"
                    :current-page="pagination.currentPage" :page-size="pagination.pageSize" small
                    layout="total, prev, pager, next" :total="pagination.total">
                  </el-pagination>
                </div>
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
import { parse, stringify } from 'qs';

export default {
  name: 'HomeView',
  components: {
    Header,
    Footer
  },
  data() {
    return {
      searchForm: {
        name: '',
        stockType: '',
        date: null,
      },
      stockTypeOptions: [{ value: 1, label: '在庫数量あり' }, { value: 0, label: '在庫数量なし' }],
      stockTableDataList: [],
      pickerOptions: {
        shortcuts: [{
          text: '最近一週間',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一ヶ月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三ヶ月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      pagination: {
        currentPage: 1,
        pageSize: 10,
        total: 0,
      },
      fetchUnitOptions: [],
      that: this,
    }
  },
  mounted() {
    this.getStockData();
    this.getUnit();
  },
  methods: {
    getStockData() {
      this.$store.dispatch('getStockInfo').then((res) => {
        this.pagination.pageSize = this.$store.state.size;
        this.pagination.currentPage = this.$store.state.current;
        this.pagination.total = this.$store.state.total;
        this.stockTableDataList = this.$store.state.stockRecords;
      }).catch(err => console.log(err));
    },
    getUnit() {
      this.$store.dispatch('getUnitNames').then((res) => {
        this.fetchUnitOptions = this.$store.state.unitNameList.map(item => item.name);
      }).catch(err => console.log(err));
    },
    searchStockInfo() {
      if (this.searchForm.date == '' && this.searchForm.name == '' && this.searchForm.stockType == '') {
        return;
      }
      if (this.searchForm.date !== null || this.searchForm.name !== '' || this.searchForm.stockType !== '') {
        this.$axios.get(("/api1/stocks/search/" + this.$store.state.pagination.currentPage + "/" + this.$store.state.pagination.pageSize),
          {
            params: {
              name: this.searchForm.name,
              date: this.searchForm.date == null ? [] : [this.formatDateTime(this.searchForm.date[0]), this.formatDateTime(this.searchForm.date[1])],
              type: this.searchForm.stockType,
            },
            paramsSerializer: {
              encode: parse,
              serialize: (params) => stringify(params, { arrayFormat: 'repeat' }),
            }
          }).then((res) => {
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
            this.pagination.total = res.data.data.total;
            this.stockTableDataList = res.data.data.records;
          }).catch(err => console.log(err));
      } else {
        this.getStockData();
      }
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getStockData();
    },
    handleEdit(index, row) {
      this.$router.push({
        name: 'addgoods',
        params: {
          id: row.id
        }
      })
    },
    handleCreate() {
      this.$router.push({
        name: 'addgoods',
      })
    },
    handleDelete(index, row) {
      this.$confirm("削除は確認しましたか", "確認メッセージ", { type: "warning", confirmButtonText: '確認', cancelButtonText: 'キャンセル', center: true }).then(() => {
        this.$axios.delete("/api1/stocks/delete/" + row.id).then((res) => {
          if (res.data) {
            this.$message.success("削除しました");
          } else {
            this.$message.error("削除できません");
          }
        }).finally(() => {
          this.getStockData();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'キャンセルしました'
        });
      })
    },
    handleDeleteAll([]) {
      this.$confirm("削除は確認しましたか", "確認メッセージ", { type: "warning", confirmButtonText: '確認', cancelButtonText: 'キャンセル', center: true }).then(() => {
        this.$axios.delete("/api1/stocks/delete/" + row.id).then((res) => {
          if (res.data) {
            this.$message.success("削除しました");
          } else {
            this.$message.error("削除できません");
          }
        }).finally(() => {
          this.getStockData();
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'キャンセルしました'
        });
      })
    },
    handleAddIO(index, row) {
      this.$store.dispatch('saveStockId', row.id);
      this.$router.push({
        name: 'ioinfo',
        params: { stock_id: row.id, name: row.name, io_num: row.stockNum, unit_id: row.unitId }
      })
    },
    handleBack() {
      this.$router.push({
        name: 'login',
      })
    },
    convertUnitName(value) {
      const nameList = this.$store.state.unitNameList;
      if (nameList != '' && nameList != undefined && nameList != null) {
        const unitName = nameList.find((item) => { return item.unit_id === value });
        if (unitName != undefined) {
          return unitName.name;
        } else {
          return value;
        }
      } else {
        return value;
      }
    },
    formatDateTime(value) {
      let date = new Date(value);
      let Y = date.getFullYear() + '-';
      let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
      let D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + ' ';
      let h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
      let m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
      let s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
      return (Y + M + D + h + m + s);
    },
  },
  computed: {

  },
  filters: {
    convertDate(value) {
      if (value != null) {
        let date = new Date(value);
        let Y = date.getFullYear() + '-';
        let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        let D = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate()) + '\n ';
        let h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
        let m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
        let s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
        return (Y + M + D + h + m + s);
      }
    },
  },
}
</script>

<style scoped>
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  height: 100%;
}

.layout {
  background-image: url("../assets/img/bg4.jpg");
  background-position: center;
  min-height: 100vh;
  width: 100%;
  background-size: cover;
  /* position: fixed; */
}

.container-shadow {
  box-shadow:
    -5px 5px 20px -4px #cac6c6, 5px 5px 20px -4px #cac6c6;

}

.searchArea {
  width: 30%;
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

.searchBox,
.goBack {
  margin: 10px 0;
}
</style>
