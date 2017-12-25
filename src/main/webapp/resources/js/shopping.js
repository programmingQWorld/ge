$(document).ready(function() {
    var vm = new Vue({
        el : '#app',
        data : {
           cartitems : [],  // 购物车中的商品集合对象
            list:[
                {
                    id:1,
                    shoppingImg:"images/Orders/ordersWorks.png",
                    author:'馮一1',
                    name:'《風》',
                    price:250,
                    count:1
                },
                {
                    id:2,
                    shoppingImg:"images/Orders/ordersWorks.png",
                    author:'馮一2',
                    name:'《風》',
                    price:340,
                    count:1
                },
                {
                    id:3,
                    shoppingImg:"images/Orders/ordersWorks.png",
                    author:'馮一3',
                    name:'《風》',
                    price:180,
                    count:1
                }
            ],
            selectList:[],
            checked:false
        },
        computed:{
            totalPrice:function(){
                var total = 0;
                for(var i = 0,len = this.selectList.length;i < len;i++){
                    var index = this.selectList[i];
                    var item = this.list[index];
                    if(item){
                        total += item.price * item.count;
                    }
                    else{
                        continue;
                    }
                }
                return total.toString();
            }
        },
        methods:{
            handleRemove:function(index, commid){
                if (!confirm("确认要删除？")) {  // 不删除
                    window.event.returnValue = false;
                }  else {  // 删除操作
                    setVueData("cart/reduceitem/" + commid, vm, "reduceitem");
                    this.cartitems.splice(index,1);  // index: 移除下标==index的元素  // 第二个参数： 整数值代表连续删除的个数
                }
            },
            /*全选*/
            swapCheck:function(){

                var selectList = document.getElementsByName('selectList');
                var len = selectList.length;
                if(this.checked){
                    for(var i = 0;i < len;i++){
                        var item = selectList[i];
                        item.checked = false;
                    }
                    this.checked = false;
                    this.selectList = [];  // 释放内存空间
                }
                else{
                    for(i = 0;i < len;i++){
                        item = selectList[i];
                        if(item.checked === false){
                            item.checked = true;
                            this.selectList.push(selectList[i].value);
                        }
                    }
                    this.checked = true;

                }
            },
            /*单选*/
            checkSingle:function(){
                var selectList = document.getElementsByName('selectList');
                for (var i=0;i<selectList.length;i++){
                    if(!selectList[i].checked){
                        this.checked = false;
                        return;
                    }
                    else{
                        this.checked = true;
                    }
                }
            }
        }});

    // 请求购物车列表
    setVueData("/cart/list", vm, "cartitems");
});