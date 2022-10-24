import axios from 'axios';

export function getBingWallpaper(idx) {
    var idx = idx;
    let baseUrl = 'https://www.bing.com';
    let bingWallPaperUlr;
    let bingWallPaperDesc;
    let bingWallPaperCopyright;

    function getAxios() {
        return new Promise(function (resolve, reject) {
            axios.get('/api2/HPImageArchive.aspx?format=js&idx=' + idx + '&n=1')
                .then(function (res) {
                    bingWallPaperUlr = baseUrl + res.data.images[0].url;
                    bingWallPaperDesc = res.data.images[0].copyright.substring(0, res.data.images[0].copyright.indexOf("("));
                    bingWallPaperCopyright = res.data.images[0].copyright.substring(res.data.images[0].copyright.indexOf("(") + 1, res.data.images[0].copyright.indexOf(")"));
                    console.log("Copyright: " + bingWallPaperCopyright);

                    let bingWallpaperOB = {
                        bingWallPaperUlr: bingWallPaperUlr,
                        bingWallPaperDesc: bingWallPaperDesc,
                        bingWallPaperCopyright: bingWallPaperCopyright
                    }
                    resolve(bingWallpaperOB);
                })
                .catch(function (error) {
                    console.log(error);
                    reject(error);
                });
        })
    }
    return getAxios();
}

export const bingWallPaperSize = ['1920x1080', '1280x768', '1366x768', '1024x768', '800x600', '800x480', '720x1280', '640x480', '480x800', '400x240', '320x240', '240x320']