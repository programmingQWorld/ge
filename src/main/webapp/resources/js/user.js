$(document).ready(function () {
    // 判断是否登录
    /*if(!getCookie("isLogin")){
        window.location.assign("index.html");
        return;
    }*/
    // 绑定数据Vue对象
    var vm = new Vue({
        el: 'container',
        data: {
            user: {
            },
            orders: []
        }
    });

    // 请求登录用户数据
    setVueData("", vm, "user")

    // 请求订单数据
    // setVueData("")
});