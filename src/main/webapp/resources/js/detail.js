$(document).ready(function () {
    // 获取当前URL
    var url = window.location.href;
    // 绑定数据Vue
    var vm = new Vue({
        el: '.content',
        data: {
            commodity: {user:{}},
            tags: [],
        },
        methods: {
            // 生成作者链接
            makeAuthorUrl: function (id) {
                return "author-" + id + ".html";
            }
        }
    });

    // 匹配商品id正则
    var reg = /detail-(\d+)\.html/;
    var patt = new RegExp(reg);
    // 获取商品id
    var commodityId = patt.exec(url)[1];

    // 请求商品详情
    setVueData("/commodity/" + commodityId + "/detail", vm, "commodity");

    // 请求商品标签
    setVueData("/commodity/" + commodityId + "/tag", vm, "tagList");
});