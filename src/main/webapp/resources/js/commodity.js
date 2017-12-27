$(document).ready(function () {
    var offset = 0;                   // 从哪条数据开始查询
    var limit = 6;                    // 每页数据条数
    var page = 1;                     // 页数
    var masonryActive = false;      // 瀑布流插件初始化标志
    var tagId = 0;                  // 标签Id，0为全部标签
    var commodityUrl = "/commodity/list?limit=" + limit;     // 请求商品数据url,默认请求全部
    // 绑定数据的Vue对象
    var vm = new Vue({
        el: '.content',
        data: {
            commodities: [],     // 商品对象数组
            tags: []            //标签数组对象
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
        },
        methods: {
            // 作品名称滑入动画
            fadeImg: function (out, event) {
                if(!out){
                    $(event.currentTarget).siblings('.slide_name').stop().fadeIn(500);
                }else{
                    $(event.currentTarget).siblings('.slide_name').stop().fadeOut(500);
                }
            },
            // 点击标签按钮事件
            clickTag: function (event) {
                tagId = $(event.currentTarget).attr("data-id");
                // 判断是否点击了全部按钮
                if(tagId != 0) {
                    // 请求tagId标签下的商品数据
                    commodityUrl = "/tag/" + tagId + "/commodity?limit=" + limit;
                }else{
                    // 请求全部商品数据
                    commodityUrl = "/commodity/list?limit=" + limit;
                }
                // 设置该标签下的第一页
                page = 1;
                setVueData(commodityUrl, vm, "commodityList");
            },
            // 生成商品详情页链接
            makeDetailUrl: function (id) {
                return "detail-" + id + ".html";
            }
        }
    });

    // 请求标签数据
    setVueData("/tag/list", vm, "tagList");

    // 请求商品数据
    setVueData(commodityUrl, vm, "commodityList");

    // 滚动事件
    $(document).scroll(function () {
        var clientH = $(window).height();           // 可见高度
        var offsetH = $(this).height();            // 内容高度
        var scrollTop = $(this).scrollTop();       // 当前滚动条的高度
        if(scrollTop == offsetH - clientH){     // 滚动条到达底部
            // 请求下一页数据
            offset = page * limit;
            page++;
            var nextCommodityUrl = commodityUrl + "&offset=" + offset;
            setVueData(nextCommodityUrl, vm ,"nextCommodityList");
        }
    });
});