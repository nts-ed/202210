const { defineConfig } = require('@vue/cli-service');
const Timestamp = new Date().getTime();
module.exports = defineConfig({
  transpileDependencies: true,
  assetsDir: 'static',
  publicPath: '/',
  productionSourceMap: false,
  devServer: {
    host: 'localhost', // can be overwritten by process.env.HOST
    port: 8080, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined
    open: false,
    hot: true,
    proxy: {
      '/api1': {
        target: 'http://localhost:8090',
        changeOrigin: true,
        pathRewrite: { '^/api1': '' },
        // ws: true,

      },
      '/api2': {
        target: 'https://www.bing.com',
        changeOrigin: true,
        pathRewrite: { '^/api2': '/' },
      }
    }
  },
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      config.output.filename = `js/[name]-[hash:8].${Timestamp}.js`
      config.output.chunkFilename = `js/[name]-[hash:8].${Timestamp}.js`
    }
  },

  css: {
    extract: {
      filename: `css/[name]-[hash:8].${Timestamp}.css`,
      chunkFilename: `css/[name]-[hash:8].${Timestamp}.css`
    }
  },

})
