$(document).ready(function () {
    // 获取当前URL
    var url = window.location.href;
    // 绑定数据Vue
    var vm = new Vue({
        el: '.content',
        data: {
            commodity: {user:{}},
            tags: [],
            receivings: [],
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
            },
            // 生成订单,并跳转到订单页面
            createOrderButton : function () {
                // 生成订单需要用到的参数
                // 发送请求到后台请求生成订单
                var rec_id = $("input[name=address]:checked").val();  // 收货id
                var commids = [];
                commids.push(this.commodity.id);                                      // 商品id
                var ids = [this.commodity.id];
                $.ajax({
                    url: "order/addOrder",
                    type: 'POST',
                    // dataType:"json",
                    traditional:true,
                    data : {
                        commids :ids ,
                        recid : rec_id
                    },
                    success : function (xhr) {
                            if ( !(xhr.data.status === 3002)) {
                                alert("订单创建成功");
                                window.location = "/user.html";
                            } else {
                                alert (xhr.data.info);
                            }
                        }
                });

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

    // 加入购物车
    $('.btn-add-cart').click(function () {
        $.ajax({
            url: "/cart/additem/" + commodityId,
            success: function (data, status) {
                if(data.success){
                    alert(data.errorMsg);
                    window.location.href = "/shopping.html";
                }else{
                    alert(data.errorMsg);
                }
            }
        });
    });
});