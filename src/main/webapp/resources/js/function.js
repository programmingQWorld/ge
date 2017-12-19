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
            }
        }
    });
}