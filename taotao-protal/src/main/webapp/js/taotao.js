var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("TT_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,// 跨域URL
			dataType : "jsonp",//jsonp 类型
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
//					var html = username + "，欢迎来到淘淘！<a href=\"http://www.jd.com\"  class=\"link-logout\">[退出]</a>";

					var html = username + "，欢迎来到淘淘！<a  onclick=\"TT.logout()\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	},
	logout : function(){
		var _ticket = $.cookie("TT_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/logout/" + _ticket,// 跨域URL
			dataType : "jsonp",//jsonp 类型
			type : "GET",
			success : function(data){
				if(data.status == 200){
					alert('已完成退出！');
					location.href = "/";
				}
			}
		});
	}


}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});