$(document).ready(function () {
    var loginUser = getCookie("loginUser");
    // 判断是否登录
    if(!loginUser){
        window.location.assign("index.html");
        return;
    }
    // 绑定数据Vue对象
    var vm = new Vue({
        el: '.content',
        data: {
            user: {},           // 登录用户
            orders: [],         // 订单数据
            receivings: [],     // 收货信息
            commodities: [],    // 商品信息
            tags:[],            // 标签
            sellerOrders: []    // 卖家订单
        },
        computed: {
            // 用户名
            username: function () {
                return (this.user.nickname == null) ? this.user.account : this.user.nickname;
            },
            // 用户头像
            userAvatar: function () {
                return (this.user.avatar == null) ? "/images/Orders/avatar.png" : this.user.avatar;
            }
        },
        methods: {
            // 获取总价钱
            getTotalCost: function (index) {
                return this.orders[index].commodity.price + this.orders[index].commodity.shippingCost;
            },
            // 显示商品的信息
            showDetail: function (out, event) {
                if(!out){
                    $(event.currentTarget).find(".imgmsg").show();
                }else{
                    $(event.currentTarget).find(".imgmsg").hide(200);
                }
            },
            // 生成模态框id
            makeModalId: function (id, t) {
                if(t){
                    return "#Oreder_Detail_" + id;
                }else
                    return "Oreder_Detail_" + id;
            },
            // 删除收货信息
            delReceiving: function (e) {
                $.ajax({
                    url: "/receiving/delete/" + $(e.currentTarget).attr('data-id'),
                    success: function (data, status) {
                        if(data.data.status == 1){
                            alert(data.status.info);
                        }else{
                            alert(data.status.info);
                        }
                    }
                });
            }
        }
    });

    // 请求登录用户数据
    setVueData("/user/queryUserById/" + loginUser, vm, "user");

    // 请求订单数据
    setVueData("/order/list", vm, "orderList");

    // 请求收货信息数据
    setVueData("/receiving/searchReceiving", vm, "receivingList");

    // 请求卖家商品信息
    setVueData("/user/" + loginUser + "/commodity", vm, "commodityList");

    // 请求标签数据
    setVueData("/tag/list", vm, "tagList");

    // 请求卖家订单
    setVueData("/order/seller/list", vm, "sellerOrderList");

    // 上传头像
    $('#avatar').change(function () {
        var formData = new FormData();
        formData.append('file', $('#avatar')[0].files[0]);
        $.ajax({
            url: "/user/upload",
            type: 'POST',
            processData: false,
            cache: false,
            contentType: false,
            data: formData,
            success: function (data, status) {
                if(data.data.status == 1){
                    $('#showAvatar').attr("src", data.data.fileName);
                }else{
                    alert(data.data.info);
                }
            }
        });
    });

    // 保存用户个人信息
    $('.saveUserBtn').click(function () {
        $.ajax({
            url: "/user/updateUser",
            type: "POST",
            data: {
                "nickname": vm.user.updateNickname,
                "filePath": $('#showAvatar').attr('src'),
                "sex": vm.user.sex,
                "birthday": vm.user.birthYear + "-" + vm.user.birthMonth + "-" + vm.user.birthDay,
                "email": vm.user.email
            },
            success: function (data, status) {
                if(data.data.status == 1){
                    alert(data.data.info);
                }else{
                    alert(data.data.info);
                }
            }
        });
    });

    // 保存收货信息
    $('.saveReceivingBtn').click(function (){
        var receiver = $('#EditAddress .receiver').val();
        var phone = $('#EditAddress .phone').val();
        var zip = $('#EditAddress .zip').val();
        var address = $('#EditAddress .address').val();
        $.ajax({
            url: "/receiving/insert",
            type: "POST",
            data: {
                "receiver": receiver,
                "phone": phone,
                "zipCode": zip,
                "address": address,
            },
            success: function (data, status) {
                if(data.data.status == 1){
                    alert(data.data.info);
                }else {
                    alert(data.data.info);
                }
            }
        });
    });

    // 上传商品图片
    $('#commodityPicture').change(function () {
        var formData = new FormData();
        formData.append("file", $('#commodityPicture')[0].files[0]);
        $.ajax({
            url: "/commodity/upload",
            type: "POST",
            processData: false,
            cache: false,
            contentType: false,
            data: formData,
            success: function (data, status) {
                if(data.data.status == 1){
                    $('#showCommodityPic').attr('src', data.data.fileName);
                }else{
                    alert(data.data.info);
                }
            }
        });
    });

    // 保存新增商品
    $('.addOpus .sub').click(function () {
        var formData = new FormData($('.addOpus .addopusForm')[0]);
        formData.append("cpic", $('.addOpus #showCommodityPic').attr('src'));
        $.ajax({
            url: "/user/commodity/add",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data, status) {
                if(data.data.status == 1){
                    alert(data.data.info);
                }else{
                    alert(data.data.info);
                }
            }
        });
    });
});