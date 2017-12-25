$(document).ready(function () {
    // 获取当前URL
    var url = window.location.href;
    // 绑定数据Vue
    var vm = new Vue({
        el: '.content',
        data: {
            commodity: {user:{}},
            tags: [],
            receivings: []
        },
        methods: {
            // 生成作者链接
            makeAuthorUrl: function (id) {
                return "author-" + id + ".html";
            },
            // 默认地址
            getDefaultReceiving: function (p) {
                for(var i = 0; i < this.receivings.length; i++){
                    if(this.receivings[i].isDefault == 1){
                        switch (p){
                            case "receiver" :
                                return this.receivings[i].receiver;
                            case "address" :
                                return this.receivings[i].address;
                            case "phone" :
                                return this.receivings[i].phone;
                        }
                    }
                }
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

    // 请求收货信息
    setVueData("/receiving/searchReceiving", vm, "receivingList");

    // 点击立即购买事件
    $('.btn-buy-now').click(function () {
        if(!getCookie('loginUser')){
            alert("请先登录");
        }else{
            $("#BuyNow").modal('show');
        }
    });
});