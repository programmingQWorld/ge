$(document).ready(function () {
    // 绑定数据的Vue对象
    var vm = new Vue({
        el: ".content",
        data: {
            levelCommodities: [],                 // 轮播图商品
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

    // 设置轮播商品
    setVueData("/commodity/5/level", vm, "levelCommodities");

    // 设置标签商品
    setVueData("/tag/1/commodity", vm, "tagCommodities");

    // 设置新上架商品
    setVueData("/commodity/list", vm, "commodityList");

});