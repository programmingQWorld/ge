$(document).ready(function(){
	/*登录注册切换*/
	$(".btnReg").click(function(){
		$(".btnReg").addClass("onBtn");
		$(".btnLog").removeClass("onBtn");
		$(".register").show();
		$(".login").hide();
	});
	$(".btnLog").click(function(){
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
	 	//用户名正则，4到16位（字母，数字，下划线，减号）
		var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
		//密码强度正则，最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符
		var pPattern = /^.*(?=.{6,})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
	 	if(name==""){
	 		ErroAlert("请输入账号");
	 		return false;
	 	}else if(!uPattern.test(name)){
	 			ErroAlert("账号为4到16位（字母，数字，下划线，减号）");
	 			return false;
	 	}
	 	if(pwd==""){
	 		ErroAlert("请输入密码");
	 		return false;
	 	   }
	 	   // else if(!pPattern.test(pwd)){
	 	// 		ErroAlert("密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
	 	// 		return false;
	 	// }
	 	if(yzm==""){
	 		ErroAlert("请输入验证码");
	 		return false;
	 	}else if(yzm.length!=4){
	 		ErroAlert("请输入正确验证码");
	 		return false;
	 	}
	 	return true;	
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
	 		ErroAlert("请输入昵称");
	 		return false;
	 	}else if(!uPattern.test(name)){
	 			ErroAlert("账号为4到16位（字母，数字，下划线，减号）");
	 			return false;
	 	}
	 	if(pwd==""){
	 		ErroAlert("请输入密码");
	 		return false;
	 	   }

	 	   // else if(!pPattern.test(pwd)){
	 	// 		ErroAlert("密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符");
	 	// 		return false;
	 	// }
	 	if(email==""){
	 		ErroAlert("请输入邮箱");
	 		return false;
	 	}
	 	if(iphone==""){
	 		ErroAlert("请输入电话号码");
	 		return false;
	 	}
		// 用户注册
		$.ajax({
			url: "http://10.10.112.170:8080/user/insertUser",
			method: "post",
			data: {"email":email, "password":pwd, "nickname":name, "phone":iphone},
			success: function (data, status) {
				console.log(data);
            }
		});
	 	return false;
}
/*提示信息弹出框*/
function ErroAlert(e){
	layui.use("layer",function(){
		var index=layer.alert(e,{title:"温馨提示",offset:"50px",icon:5,closeBtn:2,time:3000,anim:2});
	})
}
