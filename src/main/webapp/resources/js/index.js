$(document).ready(function () {
    // 绑定数据的Vue对象
    var vm = new Vue({
        el: ".content",
        data: {
            tagCommodities: ['1','2','3','4','5'],     // 根据标签展示商品
            commodities: []     // 新上架商品
        },
        methods: {
            // 根据下标获取数组值
            indexOfTagCommodities: function (index) {
                console.log(this);
                return this.tagCommodities[index];
            },
            // 根据下标获取数组值
            indexOfCommodities: function (index) {
                return this.tagCommodities[index];
            },
            // 生成商品详情页链接
            makeDetailUrl: function (id) {
                return "detail-" + id + ".html";
            }
        }
    });

    // 设置标签商品
    setVueData("http://10.10.112.170:8080/tag/1/commodity", vm, "tagCommodities");

    // 设置新上架商品
    setVueData("http://10.10.112.170:8080/commodity/list", vm, "commodityList");

});