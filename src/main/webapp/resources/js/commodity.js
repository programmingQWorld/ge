$(document).ready(function () {
    var offset = 0;                   // 页数
    var limit = 6;                    // 每页数据条数
    var masonryActive = false;      // 瀑布流插件初始化标志
    // 绑定数据的Vue对象
    var commodity = new Vue({
        el: '.grid',
        data: {
            items: []     // 商品对象数组
        },
        // 挂载Vue数据更新钩子，当data发生变化时执行，进行DOM操作
        updated: function () {
            // 图片加载完成执行
            $('.grid').imagesLoaded(function () {
                // 判断瀑布流插件是否已初始化
                if(masonryActive){
                    // 重载瀑布流
                    $('.grid').masonry('reloadItems');
                }
                // 使用jQuery插件实现瀑布流
                $('.grid').masonry({
                    itemSelector: '.grid-item',
                });
                // 瀑布流插件已初始化
                masonryActive = true;
            });
            // 作品名称滑入动画
            $('.slide_name').css('width',$('.slide_name').siblings('img').width());
            $('.tanchuname_baba img').mouseenter(function(){
                $(this).siblings('.slide_name').stop().fadeIn(500);
            });
            $('.tanchuname_baba img').mouseleave(function(){
                $(this).siblings('.slide_name').stop().fadeOut(500);
            });
        }
    });

    // 请求数据
    $.ajax({
        url: "http://10.10.112.170:8080/commodity/list?limit=" + limit,
        dataType: "json",
        success: function (data, status) {
            // 设置Vue对象的items数据为获取到的商品对象数组
            commodity.items = data.data.commodityList;
        }
    });

    // 滚动事件
    $(document).scroll(function () {
        var clientH = $(window).height();           // 可见高度
        var offsetH = $(this).height();            // 内容高度
        var scrollTop = $(this).scrollTop();       // 当前滚动条的高度
        if(scrollTop == offsetH - clientH){     // 滚动条到达底部
            // 请求下一页数据
            offset ++;
            offset *= limit;
            $.ajax({
                url: "http://10.10.112.170:8080/commodity/list?offset=" + offset + "&limit=6",
                dataTye: "json",
                success: function (data, status) {
                    // 判断是否还有数据
                    if(data.data.commodityList != null) {
                        // 向Vue对象的items加入下一页的数据，同时使视图重新渲染
                        commodity.items = commodity.items.concat(data.data.commodityList);
                    }
                }
            });
        }
    });
});