$(document).ready(function () {
    // 获取当前URL
    var url = window.location.href;
    // 绑定数据Vue
    var vm = new Vue({
        el: '.content',
        data: {
            commodity: {user:{}},
            tags: [],
        }
    });

    // 匹配商品id正则
    var reg = /detail-(\d+)\.html/;
    var patt = new RegExp(reg);
    // 获取商品id
    var commodityId = patt.exec(url)[1];

    // 请求商品详情
    setVueData("http://10.10.112.170:8080/commodity/" + commodityId + "/detail", vm, "commodity");

    // 请求商品标签
    setVueData("http://10.10.112.170:8080/commodity/" + commodityId + "/tag", vm, "tagList");
});