// Vuex核心store
import axios from 'axios'
import Vue from 'vue'
// 引入Vuex
import Vuex from 'vuex'
// 使用Vuex插件
Vue.use(Vuex)

// 准备actions - 响应式组件中的动作
const actions = {
    async getUnitNames(context) {
        await axios.get("/api1/units").then((res) => {
            const unitList = res.data.data.map((item, index) => { return Object.assign({}, { unit_id: item.unitId, name: item.name }) });
            context.commit('GetUnitNames', unitList);
        }).catch(err => console.log(err));
    },
    saveStockId(context, value) {
        context.commit('SaveStockId', value);
    },
    async getStockInfo(context) {
        await axios.get("/api1/stocks/" + state.pagination.currentPage + "/" + state.pagination.pageSize).then((res) => {
            const stockInfoList = res.data.data;
            context.commit('GetStockInfo', stockInfoList);
        }).catch(err => console.log(err));
    },
    async getIOData(context) {
        if (state.stock_id != '') {
            await axios.get("/api1/stocks/io/" + state.stock_id + "/" + state.pagination.currentPage + "/" + state.pagination.pageSize).then((res) => {
                const ioDataList = res.data.data;
                context.commit("GetIOData", ioDataList);
            }).catch(err => console.log(err));
        } else {
            return;
        }
    },
    async getStockType(context) {
        await axios.get("/api1/codes").then((res) => {
            const codeList = res.data.data.map((item, index) => { return Object.assign({}, { codeId: item.codeId, name: item.name }) });
            context.commit('GetStockType', codeList);
        }).catch(err => console.log(err));
    }
}

// 准备mutations - 操作数据state
const mutations = {
    GetUnitNames(state, unitList) {
        state.unitNameList = unitList;
    },
    SaveStockId(state, value) {
        state.stock_id = value
        // console.log(state, value);
    },
    GetStockInfo(state, stockInfoList) {
        const { records, current, size, total } = stockInfoList;
        state.stockRecords = records;
        state.pagination.currentPage = current;
        state.pagination.pageSize = size;
        state.pagination.total = total;
    },
    GetIOData(state, ioDataList) {
        const { records, current, size, total } = ioDataList;
        state.ioRecords = records;
        state.pagination.currentPage = current;
        state.pagination.pageSize = size;
        state.pagination.total = total;
    },
    GetStockType(state, codeList) {
        state.codeNameList = codeList;
    },
}

//准备state - 存储数据
const state = {
    unitNameList: '',
    codeNameList: '',
    stock_id: '',
    employee_id: '',
    pagination: {
        currentPage: 1,
        pageSize: 10,
        total: 0,
    },
    stockRecords: [],
    ioRecords: [],
}

// 创建并导出store
export default new Vuex.Store({
    actions,
    mutations,
    state,
})

