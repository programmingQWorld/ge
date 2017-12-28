$(document).ready(function () {
    // 绑定数据的Vue对象
    var vm = new Vue({
        el: ".content",
        data: {
            levelCommodities: [],                 // 轮播图商品
            tagCommodities: [],     // 根据标签展示商品
            commodities: [],     // 新上架商品
            checkCode: ""       // 验证码
        },
        methods: {
            // 根据下标获取数组值
            indexOfTagCommodities: function (index, p) {
                if(this.tagCommodities[index] == null) return;
                switch (p){
                    case "id" :
                        return this.tagCommodities[index].id;
                    case "name" :
                        return this.tagCommodities[index].name;
                    case "picture" :
                        return this.tagCommodities[index].picture;
                }
            },
            // 根据下标获取数组值
            indexOfCommodities: function (index, p) {
                if(this.commodities[index] == null) return;
                switch (p){
                    case "id" :
                        return this.commodities[index].id;
                    case "name" :
                        return this.commodities[index].name;
                    case "picture" :
                        return this.commodities[index].picture;
                }
            },
            // 生成商品详情页链接
            makeDetailUrl: function (id) {
                return "detail-" + id + ".html";
            },
            // 更换验证码
            changeCode: function () {
                setVueData("/user/getCheckCode", vm, "checkCode");
            }
        }
    });

    // 设置轮播商品
    setVueData("/commodity/5/level", vm, "levelCommodities");

    // 设置标签商品
    setVueData("/tag/3/commodity", vm, "tagCommodities");

    // 设置新上架商品
    setVueData("/commodity/list", vm, "commodityList");

    // 设置验证码
    setVueData("/user/getCheckCode", vm, "checkCode");

});