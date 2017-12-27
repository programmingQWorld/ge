/**
 * 管理公共部分的样式
 */
$(document).ready(function () {
    var common = new Vue({
        el: '.pageheader',
        data: {
            user:{}     // 登录用户
        },
        computed: {
            // 用户名
            username: function () {
                return (this.user.nickname == null) ? this.user.account : this.user.nickname;
            }
        },
        updated: function () {
            $('#navbar .logn_up').css('background-color','white').css('border','1px solid red').find('a').css('color', 'red');
        }
    });
    // 请求是否已登录
    setVueData("/user/islogin", common, "isLogin");
});

/**
 * 使用ajax请求获取指定url的数据并给Vue对象赋值
 * @param url
 * @param vm
 * @param type
 */
function setVueData(url, vm, type) {
    $.ajax({
        url: url,
        dataType: "json",
        success: function (data, status) {
            // 根据type判断，给Vue对象数据赋值
            switch (type){
                case "commodityList" :
                    // 判断是否还有数据
                    if(data.data.commodityList != null) {
                        vm.commodities = data.data.commodityList;
                    }
                    break;
                case "tagList" :
                    vm.tags = data.data.tagList;
                    break;
                case "nextCommodityList" :
                    // 判断是否还有数据
                    if(data.data.commodityList != null) {
                        // 向Vue对象的commodities加入下一页的数据，同时使视图重新渲染
                        vm.commodities = vm.commodities.concat(data.data.commodityList);
                    }
                    break;
                case "tagCommodities" :
                    vm.tagCommodities = data.data.commodityList;
                    break;
                case "commodity" :
                    vm.commodity = data.data.commodity;
                    break;
                case "user" :
                    vm.user = data.data.user;
                    vm.user.updateNickname = data.data.user.nickname;
                    var birthday = data.data.user.birthday.split('-');
                    vm.user.birthYear = birthday[0];
                    vm.user.birthMonth = birthday[1];
                    vm.user.birthDay = birthday[2];
                    vm.user.updateAvatar = data.data.user.avatar;
                    break;
                case "orderList" :
                    vm.orders = data.data.orderList;
                    break;
                case "receivingList" :
                    vm.receivings = data.data.receivingList;
                    break;
                case "sellerOrderList" :
                    vm.sellerOrders = data.data.orderList;
                    break;
                case "levelCommodities" :
                    vm.levelCommodities = data.data.commodityList;
                    break;
                case "isLogin" :
                    if(data.success){
                        vm.user = data.data.user;
                    }else{
                        delCookie("loginUser");
                    }
                    break;
                case "cartitems" :
                    vm.cartitems = data.data.items;
                    break;
                case "count" :
                    vm.count = data.data.count;
                    break;
                case "checkCode" :
                    vm.checkCode = data.data.code;
                    break;
            }
        }
    });
}

/**
 * 设置cookie
 * @param key
 * @param value
 * @param expiredays
 */
function setCookie(key, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = key + "=" + encodeURI(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}

/**
 * 根据key获取cookie的value
 * @param key
 * @return string
 */
function getCookie(key) {
    if(document.cookie.length > 0){
        // key正则
        var reg =key + "=([^;]+)";
        var patt = new RegExp(reg);
        return encodeURI(patt.exec(document.cookie)[1]);
    }
    return "";
}

/**
 * 根据key删除cookie
 * @param key
 */
function delCookie(key) {
    setCookie(key, "", -1);
}

/**
 * time毫秒后跳转url
 * @param url
 * @param time
 */
function jumpAfterTime(url, time) {
    setTimeout("window.location.assign('" + url + "');", time);
}