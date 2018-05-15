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

function commitapplication()
{
	if(verify())
{
	var parm = $("#tableleft").serialize();
	var longitudestart=document.getElementById("longitudestart").innerHTML;
	var latitudestart=document.getElementById("latitudestart").innerHTML;
	var longitudedestination=document.getElementById("longitudedestination").innerHTML;
	var latitudedestination=document.getElementById("latitudedestination").innerHTML;
	
	$.ajax({
		type:"post",
		url:"commitapplication",
		data:parm+"&longitudestart="+longitudestart+"&latitudestart="+latitudestart+"&longitudedestination="+longitudedestination+"&latitudedestination="+latitudedestination,
		dataType:"json",
		success:function(data)
		{
			alert("提交成功");
		},
		error:function(){
			
			alert("提交失败");
		}
		
	});
}else
	{
	
	}
}

function verify()
{
	var flag1=document.getElementById("boss").value;
	var flag2=document.getElementById("phone").value;
	var flag3=document.getElementById("goods").value;
	var flag4=document.getElementById("start").value;
	var flag5=document.getElementById("destination").value;
	var flag6=document.getElementById("weight").value;
	var flag7=document.getElementById("receiver").value;
	var flag8=document.getElementById("recephone").value;
	if(flag1.length==0||flag2.length==0||flag3.length==0||flag4.length==0||flag5.length==0||flag6.length==0||flag7.length==0||flag8.length==0)
		{
		
			alert("信息不全");
			return false;
		}else
			return true;
}

function openwin(index)
{
	switch(index)
	{
	case 1:
	var popup_url="http://"+window.location.hostname+":"+window.location.port+"/baoliang/Popwindow/mapforstart.jsp";
	window.open(popup_url,'newwindow','width='+500+',height='+350+',top='+100+',left='+300+',toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	break;
	case 2:
		var popup_url="http://"+window.location.hostname+":"+window.location.port+"/baoliang/Popwindow/mapfordestination.jsp";
		window.open(popup_url,'newwindow','width='+500+',height='+350+',top='+100+',left='+300+',toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
		break;
	}
	}


var page2=1;
var page3=1;
var page4=1;
var pagesum2;
var pagesum3;
var pagesum4;

$(function(){
	
	$('#form1').show();
	$('#form2').hide();
	$('#form3').hide();
	$('#form4').hide();
})
function select(index)
{
	switch(index)
	{
		case 1:
			$('#form1').show();
			$('#form2').hide();
			$('#form3').hide();
			$('#form4').hide();
			$('#form5').hide();
			break;
		case 2:
			$('#form1').hide();
			$('#form2').show();
			$('#form3').hide();
			$('#form4').hide();
			//$('#form5').hide();
			 adddata(2);
			break;
		case 3:
			$('#form1').hide();
			$('#form2').hide();
			$('#form3').show();
			$('#form4').hide();
			$('#form5').hide();
			 adddata(3);
			break;
		case 4:
			$('#form1').hide();
			$('#form2').hide();
			$('#form3').hide();
			$('#form4').show();
			//$('#form5').hide();
			 adddata(4);
			break;
		/*case 5:
			$('#form1').hide();
			$('#form2').hide();
			$('#form3').hide();
			$('#form4').hide();
			//$('#form5').show();
			 
			break;*///个人信息暂时不用
		
	}
}
var splitsum=10;
function adddata(index)
{
	switch(index)
	{
	case 2:
		$.ajax({
		type:"post",
		url:"getuserdata",
		data:"index="+index+"&phone="+document.getElementById("username").innerHTML,
		dataType:"json",
		success:function(data)
		{
			var str='<tr>'
				+'<td>d订单编号</td>'
				+'<td>货物</td>'
				+'<td>起始地</td>'
				+'<td>目的地</td>'
				+'<td>重量</td>'
				+'<td>操作</td>'
				+'</tr>';
			var mydata = eval('(' + data.jsonString + ')');
			pagesum2=mydata.length;
			var start=(page2-1)*splitsum;
			var end=start+splitsum;
			if(end>pagesum2)
				{
					end=pagesum2
				}
			var i;
			for(i=start;i<end;i++)
				{
					str=str+'<tr><td>d'+mydata[i].acnum+'</td>'
					+'<td>'+mydata[i].goods+'</td>'
					+'<td>'+mydata[i].start+'</td>'
					+'<td>'+mydata[i].destination+'</td>'
					+'<td>'+mydata[i].weight+'</td>'
					+'<td><input type="button" value="删除" onclick="deleted(\''+mydata[i].acnum+'\')"/></td>'
					+'</tr>'
					
				}
			
			document.getElementById("tablemidle").innerHTML=str;
			document.getElementById("pageshow2").innerHTML="第"+page2+"页";
			
		},
		error:function(){
			
			alert("获取数据失败");
		}
		
	});break;
	case 3:
		$.ajax({
			type:"post",
			url:"getuserdata",
			data:"index="+index+"&phone="+document.getElementById("username").innerHTML,
			dataType:"json",
			success:function(data)
			{
				var str='<tr>'
					+'<td>d订单编号</td>'
					+'<td>货物</td>'
					+'<td>起始地</td>'
					+'<td>目的地</td>'
					+'<td>司机编号</td>'
					+'<td>车牌</td>'
					+'<td>重量</td>'
					+'</tr>';
				var mydata = eval('(' + data.jsonString + ')');
				pagesum3=mydata.length;
				var start=(page3-1)*splitsum;
				var end=start+splitsum;
				if(end>pagesum3)
					{
						end=pagesum3
					}
				var i;
				for(i=start;i<end;i++)
					{
						str=str+'<tr><td>d'+mydata[i].acnum+'</td>'
						+'<td>'+mydata[i].goods+'</td>'
						+'<td>'+mydata[i].start+'</td>'
						+'<td>'+mydata[i].destination+'</td>'
						+'<td>'+mydata[i].drivernum+'</td>'
						+'<td>'+mydata[i].car+'</td>'
						+'<td>'+mydata[i].weight+'</td></tr>'
					}
				document.getElementById("tableright").innerHTML=str;
				document.getElementById("pageshow3").innerHTML="第"+page3+"页";
			},
			error:function(){
				
				alert("获取数据失败");
			}
			
		});
		break;
	case 4:
		$.ajax({
			type:"post",
			url:"getuserdata",
			data:"index="+index+"&phone="+document.getElementById("username").innerHTML,
			dataType:"json",
			success:function(data)
			{
				var str='<tr>'
					+'<td>d订单编号</td>'
					+'<td>货物</td>'
					+'<td>起始地</td>'
					+'<td>目的地</td>'
					+'<td>司机编号</td>'
					+'<td>车牌</td>'
					+'<td>重量</td>'
					
					+'</tr>';
				var mydata = eval('(' + data.jsonString + ')');
				pagesum4=mydata.length;
				var start=(page4-1)*splitsum;
				var end=start+splitsum;
				if(end>pagesum4)
					{
						end=pagesum4
					}
				var i;
				for(i=start;i<end;i++)
					{
						str=str+'<tr><td>d'+mydata[i].acnum+'</td>'
						+'<td>'+mydata[i].goods+'</td>'
						+'<td>'+mydata[i].start+'</td>'
						+'<td>'+mydata[i].destination+'</td>'
						+'<td>'+mydata[i].drivernum+'</td>'
						+'<td>'+mydata[i].car+'</td>'
						+'<td>'+mydata[i].weight+'</td>'
						
						+'</tr>'
					}
				document.getElementById("tableac").innerHTML=str;
				document.getElementById("pageshow4").innerHTML="第"+page4+"页";
			},
			error:function(){
				
				alert("获取数据失败");
			}
			
		});
		break;
	}//switch
	
}

function preview(index)
{
	switch(index)
	{
	case 2:
		if(page2>1)
			{
				page2--;
				adddata(index);
			
			}
		break;
	case 3:
		
		break;
	case 4:break;
	}
}

function next(index)
{pagesum2
	switch(index)
	{
	case 2:
		if(pagesum2>(page2*splitsum))
			{
				page2++;
				adddata(index);
			}
		break;
	case 3:break;
	case 4:break;
	}
}

function deleted(acnum)
{
	alert(acnum);
	$.ajax({
		type:"post",
		url:"deleteforuser",
		data:"index=2&acnum="+acnum,
		dataType:"json",
		success:function(data)
		{
			
			
			 	$('#form1').hide();
				$('#form2').show();
				$('#form3').hide();
				$('#form4').hide();
				 adddata(2);
				 alert("删除成功");
			 
		},
		error:function(){
			
			alert("删除失败");
			 
		}
		
	});
}