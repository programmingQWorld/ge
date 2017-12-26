$(document).ready(function () {
    var page = 1;       // 目前页数
    var limit = 8;      // 每页多少
    var offset = 0;     // 从哪条开始查询
    var loginUser = getCookie("loginUser");     // 登录用户
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
            count: 0,           // 商品数量
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
            },
            // 计算分页
            pageCount: function () {
                return Math.ceil(this.count/limit);
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
                    return "#" + id;
                }else
                    return id;
            },
            // 删除收货信息
            delReceiving: function (e) {
                $.ajax({
                    url: "/receiving/delete/" + $(e.currentTarget).attr('data-id'),
                    success: function (data, status) {
                        if(data.data.status == 1){
                            alert(data.data.info);
                        }else{
                            alert(data.data.info);
                        }
                    }
                });
            },
            // 设默认地址
            setDefaultReceiving: function (e) {
                $.ajax({
                    url: "/receiving/setIsDefault/" + $(e.currentTarget).attr('data-id'),
                    success: function (data, status) {
                        if(data.data.status == 1){
                            alert(data.data.info);
                        }else {
                            alert(data.data.info);
                        }
                    }
                });
            },
            // 发货
            send: function (e) {
                $.ajax({
                    url: "/order/seller/handlerorder/" + $(e.currentTarget).attr('data-id'),
                    success: function (data, status) {
                        if(data.data.status){
                            alert(data.data.info);
                        }else{
                            alert(data.data.info);
                        }
                    }
                });
            },
            // 付款
            pay: function (e) {
                $.ajax({
                    url: "/order/pay/" + $(e.currentTarget).attr('data-id'),
                    success: function (data, status) {
                        if(data.data.status){
                            alert(data.data.info);
                        }else{
                            alert(data.data.info);
                        }
                    }
                });
            },
            // 点击分页按钮
            jumpPageNum: function (n) {
                page = n;
                offset = (page-1) * limit;
                setVueData("/user/" + loginUser + "/commodity?offset=" + offset + "&limit=" + limit, vm, "commodityList");
            }
        },
        updated: function () {
            // 为分页上样式
            var pageNum = $('.works-page.fy ul li');
            $(pageNum).find('a').removeClass('active');
            $(pageNum[page]).find('a').addClass('active');
        }
    });

    // 请求登录用户数据
    setVueData("/user/queryUserById/" + loginUser, vm, "user");

    // 请求订单数据
    setVueData("/order/list", vm, "orderList");

    // 请求收货信息数据
    setVueData("/receiving/searchReceiving", vm, "receivingList");

    // 请求卖家商品信息
    setVueData("/user/" + loginUser + "/commodity?offset=" + offset + "&limit=" + limit, vm, "commodityList");

    // 请求标签数据
    setVueData("/tag/list", vm, "tagList");

    // 请求卖家订单
    setVueData("/order/seller/list", vm, "sellerOrderList");

    // 请求商品数量
    setVueData("/user/" + loginUser + "/commodity/count", vm, "count");

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
    // 修改商品
    $(document).on("click", ".EditButton", function (e) {
        var id = $(this).attr("data-id");
        var name = $(this).parent().prev().find('input[name="name"]').val();
        var author = $(this).parent().prev().find('input[name="author"]').val();
        var sizeW = $(this).parent().prev().find('input[name="sizeW"]').val();
        var sizeH = $(this).parent().prev().find('input[name="sizeH"]').val();
        var price = $(this).parent().prev().find('input[name="price"]').val();
        if(name == "" || author == "" || sizeW == "" || sizeH == "" || price == "") {
            alert(":(  不能為空");
            return false;
        } else {
            $.ajax({
                url: "/commodity/" + id + "/update",
                method: "POST",
                data: {
                    "id": id,
                    "name": name,
                    "sizeW": sizeW,
                    "sizeH": sizeH,
                    "price": price
                },
                success: function (data, status) {
                    if(data.data.status == 1){
                        alert(":)  修改成功");
                        $('.modal').modal('hide');
                    }else{
                        alert(data.data.info);
                    }
                }
            });

        }
    });

    // 商品分页，上一页
    $('.works-page.fy .prev').click(function (e) {
        if(page == vm.pageCount) page--;
        page--;
        offset = page * limit;
        if(page == 0) page = 1, offset = 0;
        setVueData("/user/" + loginUser + "/commodity?offset=" + offset + "&limit=" + limit, vm, "commodityList");
    });
    // 商品分页，下一页
    $('.works-page.fy .next').click(function (e) {
        offset = page * limit;
        var commodity = vm.commodities[0];
        setVueData("/user/" + loginUser + "/commodity?offset=" + offset + "&limit=" + limit, vm, "commodityList");
        if(page != vm.pageCount) page++;
    });
});