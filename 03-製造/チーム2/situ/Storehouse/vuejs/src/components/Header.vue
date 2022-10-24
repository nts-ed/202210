<template>
  <div>
    <h1 class="floatLeft"><img src="../assets/img/logo.png" width="33px" class="mr-10"
        style="vertical-align: middle;">倉庫管理システム</h1>
    <el-tooltip class="item" effect="dark" content="背景画像更新" placement="right">
      <el-button type="primary" class="floatRight mt-5" size="mini" circle @click="getEverydayBgImg()"><i
          class="el-icon-picture-outline"></i></el-button>
    </el-tooltip>
    <div class="clearBoth"></div>
  </div>
</template>
  
<script>
import { getBingWallpaper } from "../api/api";

export default {
  name: 'Header',

  data() {
    return {
      bg: '',
    }
  },
  methods: {
    getEverydayBgImg() {
      getBingWallpaper(0).then(res => {
        this.bg = res.bingWallPaperUlr;
        // console.log(this.bg);
        if (document.querySelector(".layout")) {
          document.querySelector(".layout").style.backgroundImage = "url(" + this.bg + ")";
        }
      });
    },
  },
}
</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>