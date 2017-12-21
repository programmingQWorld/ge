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
            user: {},       // 登录用户
            orders: [],     // 订单数据
            receivings: [], // 收货信息
            commodities: [] // 商品信息
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
            }
        }
    });

    // 请求登录用户数据
    setVueData("http://10.10.112.170:8080/user/queryUserById/" + loginUser, vm, "user");

    // 请求订单数据
    setVueData("http://10.10.112.170:8080/order/list", vm, "orderList");

    // 请求收货信息数据
    setVueData("/receiving/searchReceiving", vm, "receivingList");

    // 请求卖家商品信息
    setVueData("/user/" + loginUser + "/commodity", vm, "commodityList");

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
});