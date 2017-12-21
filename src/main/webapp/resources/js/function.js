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
                    vm.commodities = data.data.commodityList;
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
 * time毫秒后跳转url
 * @param url
 * @param time
 */
function jumpAfterTime(url, time) {
    setTimeout("window.location.assign('" + url + "');", time);
}