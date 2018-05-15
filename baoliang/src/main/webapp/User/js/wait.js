 function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); 
            var username=document.getElementById("username").innerHTML;
            return username;
            //返回参数值
        }
$('#view1').hide();
$('#view2').hide()
$.ajax({
		type:"get",
		url:"getuserstatue",
		data:"phone="+getUrlParam("username"),
		dataType:"json",
		success:function(data)
		{
	
			
			var urls=window.location.href;
			if(urls.indexOf('uploadcardfile')>=0)
				{
				
			window.location.href="http://"+window.location.hostname+":"+window.location.port+"/baoliang/User/wait.jsp"; 
				}
			if(data.flag==4)
				{
				$('#view1').hide();
				$('#view2').show();
				document.getElementById("reason").innerHTML=data.description;
				}else if(data.flag==1){
					alert("恭喜审核通过");
					var username=document.getElementById("username").innerHTML;
					window.location.href="http://"+window.location.hostname+":"+window.location.port+"/baoliang/Logforward1?username="+username;
				}else{
					$('#view1').show();
					$('#view2').hide();
				}
			
		},
		error:function(){
			$('#view1').show();
			$('#view2').hide();
			
		}
		
	});

function logout()
{
	var parm=$('usernamelabel').val();
	 $.ajax({ 
        type : "post",
        url : "Logout",
        data : "name="+parm,
        dataType:"json",

        success : function(data) {
       	 window.location.href="http://"+window.location.hostname+":"+window.location.port+"/baoliang"; 
     	   console.log("退出成功");
        },

        error : function() {
            alert("退出失败");
        }
    });
}


function readd()
{
	window.location.href="http://"+window.location.hostname+":"+window.location.port+"/baoliang/User/verify.jsp"; 
}
