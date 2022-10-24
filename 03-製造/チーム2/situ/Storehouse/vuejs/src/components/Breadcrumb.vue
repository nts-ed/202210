<template>
    <div class="breadcrumb">
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/' }">一覧</el-breadcrumb-item>
            <el-breadcrumb-item>詳細</el-breadcrumb-item>
        </el-breadcrumb>
    </div>

</template>
    
<script>
export default {
    name: 'Breadcrumb',
    props: {

    }
}
</script>
    
   
<style scoped>

</style>