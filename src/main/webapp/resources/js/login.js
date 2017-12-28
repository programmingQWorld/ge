$(document).ready(function(){
	/*登录注册切换*/
	$(document).on('click', '.btnReg', function (e) {
        $(".btnReg").addClass("onBtn");
        $(".btnLog").removeClass("onBtn");
        $(".register").show();
        $(".login").hide();
    });

	$(document).on('click', '.btnLog', function (e) {
        $(".btnLog").addClass("onBtn");
        $(".btnReg").removeClass("onBtn");
        $(".register").hide();
        $(".login").show();
    });
	/*验证*/
	// layui.use("layer",function(){
	// 	$(".btnLog").click(function(){
	 	var name=$("input[name='name']").val();
	 	var pwd=$("input[name='pwd']").val();
	 	var yzm=$("input[name='yzm']").val();
	// 	 	if(name==""){
	// 			var layer=layui.layer;
	// 			layer.msg(23);
	// 	 		console.log(2);
	// 	 	}
			
	// 	})
	// });
});

function login_submit_sure(){
		var name=$("input[name='name1']").val();
	 	var pwd=$("input[name='pwd1']").val();
	 	var yzm=$("input[name='yzm']").val();
	 	debugger
	 	//用户名正则，4到16位（字母，数字，下划线，减号）
		var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
		//密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
		var pPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
	 	if(name==""){
	 		ErrorAlert("请输入账号");
	 		return false;
	 	}else if(!uPattern.test(name)){
	 			ErrorAlert("账号为4到16位（字母，数字，下划线，减号）");
	 			return false;
	 	}
	 	if(pwd==""){
	 		ErrorAlert("请输入密码");
	 		return false;
	 	   }
		// else if(!pPattern.test(pwd)){
	 	// 		ErrorAlert("密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
	 	// 		return false;
	 	// }
	 	if(yzm==""){
	 		ErrorAlert("请输入验证码");
	 		return false;
	 	}else if(yzm.length!=4){
	 		ErrorAlert("请输入正确验证码");
	 		return false;
	 	}
	 	// 用户登录请求
		$.ajax({
			url: "/user/login",
			type: "POST",
			data: {"account": name, "password": pwd, "checkCode": yzm},
			success: function (data, status) {
				// 登录成功
				if(data.data.status == 1){
					successAlert("Welcome Back!");
					// 设置cookie
					setCookie("loginUser", data.data.userId, null);
					// 跳转用户个人中心
					jumpAfterTime("/user.html", 2000);
				}else{
					ErrorAlert(data.data.info);
				}
            }
		});
	 	return false;
}
function register_submit_sure(){
		var name=$("input[name='name2']").val();
	 	var pwd=$("input[name='pwd2']").val();
	 	var email=$("input[name='email']").val();
	 	var iphone=$("input[name='iphone']").val();

	 	//用户名正则，4到16位（字母，数字，下划线，减号）
		var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
		//密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
		var pPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
	 	if(name==""){
	 		ErrorAlert("请输入账号");
	 		return false;
	 	}else if(!uPattern.test(name)){
	 			ErrorAlert("账号为4到16位（字母，数字，下划线，减号）");
	 			return false;
	 	}
	 	if(pwd==""){
	 		ErrorAlert("请输入密码");
	 		return false;
	 	   }

		// else if(!pPattern.test(pwd)){
	 	// 		ErrorAlert("密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
	 	// 		return false;
	 	// }
	 	if(email==""){
	 		ErrorAlert("请输入邮箱");
	 		return false;
	 	}
	 	if(iphone==""){
	 		ErrorAlert("请输入电话号码");
	 		return false;
	 	}
		// 用户注册请求
		$.ajax({
			url: "/user/insertUser",
			type: "post",
			data: {"account":name, "email":email, "password":pwd, "phone":iphone},
			success: function (data, status) {
				// 注册成功
				if(data.data.status == 1){
					successAlert("Welcome Register");
				}else{		// 注册失败
					ErrorAlert(data.data.info);
				}
            }
		});
	 	return false;
}
/*提示信息弹出框*/
function ErrorAlert(e){
	layui.use("layer",function(){
		var index=layer.alert(e,{title:"温馨提示",offset:"50px",icon:5,closeBtn:2,time:3000,anim:2});
	});
}

// 成功弹框
function successAlert(msg) {
	layui.use("layer", function () {
		layer.alert(msg,{title:"温馨提示",offset:"50px",icon:6,closeBtn:2,time:3000,anim:2});
    });

}
