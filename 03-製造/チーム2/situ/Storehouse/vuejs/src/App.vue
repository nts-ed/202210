<template>
  <div id="app">
    <router-view />
  </div>
</template>
<script>
export default {
  // 下面是解决刷新页面丢失vuex数据
  created() {
    //在页面加载时读取sessionStorage里的状态信息
    if (sessionStorage.getItem('store')) {
      console.log(JSON.parse(sessionStorage.getItem('store')))//这里进行得到的store数据进行操作
    }

    //在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem('store', JSON.stringify(this.$store.state));
    });
  }
}
</script>
<style>
#app {
  font-family: "游ゴシック体", "YuGothic", "游ゴシック", "Yu Gothic", "ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro", "メイリオ", "Meiryo", "微软雅黑", Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
