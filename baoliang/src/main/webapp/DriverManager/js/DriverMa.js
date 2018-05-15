function showDriver()
{
	page=1;
	$('#drivertable').show();
	$('#addtable').hide();
	$('#searchap').show();
	$('#pagemodel').show();
	$('#dshow1').show();
	$('#dshow2').show();
	$('#sshow1').hide();
	$('#sshow2').hide();
	
	getData();
}

function addDriver()
{
	$('#searchap').hide();
	$('#drivertable').hide();
	$('#addtable').show();
	$('#pagemodel').hide();
	$('#dshow1').hide();
	$('#dshow2').hide();
	$('#sshow1').hide();
	$('#sshow2').hide();
	
	
}







$(function(){
	$('#drivertable').show();
	$('#addtable').hide();
	$('#dshow1').show();
	$('#dshow2').show();
	$('#sshow1').hide();
	$('#sshow2').hide();
	getData();
})
var start=1;
var end;
var str;
var page=1;
var pagesum=10;
var datalength;
function getData()
{
	$.ajax({
		type:"get",
		url:"getdriversdata",
		dataType:"json",
		success:function(data)
		{
		
		var mydata = eval('(' + data.jsonString + ')');
		
			str='<tr>'
		+'<td>司机编号</td>'
		+'<td>姓名</td>'
		+'<td>电话</td>'
		+'<td>车牌号</td>'
		+'<td>载重</td>'
		+'<td>状态</td>'
		+'<td>操作</td>'
		+'</tr>';
		var i;
		var statue;
		datalength=mydata.length;
		var start= (page-1)*pagesum;
		var end= start+pagesum;
		if((end+1)>datalength)
			{
				end=datalength;
			}
		for(i=start;i<end;i++)
			{
			if(mydata[i].statue=='1')
				{
					statue='未分配';
					
				}else if(mydata[i].statue=='2')
					{
						statue='整修中';
					
					}if(mydata[i].statue=='3')
						{
							statue='运送中'
						
						}
			str=str+'<tr>'
			+'<td>'+mydata[i].drivernums+'</td>'
			+'<td>'+mydata[i].name+'</td>'
			+'<td>'+mydata[i].phone+'</td>'
			+'<td>'+mydata[i].carnum+'</td>'
			+'<td>'+mydata[i].cargo+'</td>'
			+'<td>'+statue+'</td>'
			+'<td>'+'<input type="button" value="删除" onclick="del(\''+mydata[i].drivernums+'\')"/>'
			+'<input type="button" value="编辑" onclick="edit('
			+'\''+mydata[i].drivernums+'\','
			+'\''+mydata[i].name+'\','
			+'\''+mydata[i].phone+'\','
			+'\''+mydata[i].carnum+'\','
			+'\''+mydata[i].cargo+'\','
			+'\''+statue+'\''
			+')"/>'
			+'</td>'
			+'</tr>';
			}
		document.getElementById("drivertable").innerHTML=str;
		document.getElementById("pageshow").innerHTML="第"+page+"页";
		
		},
		error:function(){
			alert("获取数据错误");
			
		}
		
	});
}

function getsearch()
{
	var parm= document.getElementById("searchinput").value;
	$('#dshow1').hide();
	$('#dshow2').hide();
	$('#sshow1').show();
	$('#sshow2').show();
	$.ajax({
		type:"get",
		url:"searchdriver",
		data:"keys="+parm,
		dataType:"json",
		success:function(data)
		{
		
		var mydata = eval('(' + data.searchdriver + ')');
		
			str='<tr>'
		+'<td>司机编号</td>'
		+'<td>姓名</td>'
		+'<td>电话</td>'
		+'<td>车牌号</td>'
		+'<td>载重</td>'
		+'<td>状态</td>'
		+'<td>操作</td>'
		+'</tr>';
		var i;
		var statue;
		datalength=mydata.length;
		var start= (page-1)*pagesum;
		var end= start+pagesum;
		if((end+1)>datalength)
			{
				end=datalength;
			}
		for(i=start;i<end;i++)
			{
			if(mydata[i].statue=='1')
				{
					statue='未分配';
					
				}else if(mydata[i].statue=='2')
					{
						statue='整修中';
					
					}if(mydata[i].statue=='3')
						{
							statue='运送中'
						
						}
			str=str+'<tr>'
			+'<td>'+mydata[i].drivernums+'</td>'
			+'<td>'+mydata[i].name+'</td>'
			+'<td>'+mydata[i].phone+'</td>'
			+'<td>'+mydata[i].carnum+'</td>'
			+'<td>'+mydata[i].cargo+'</td>'
			+'<td>'+statue+'</td>'
			+'<td>'+'<input type="button" value="删除" onclick="del(\''+mydata[i].drivernums+'\')"/>'
			+'<input type="button" value="编辑" onclick="edit('
			+'\''+mydata[i].drivernums+'\','
			+'\''+mydata[i].name+'\','
			+'\''+mydata[i].phone+'\','
			+'\''+mydata[i].carnum+'\','
			+'\''+mydata[i].cargo+'\','
			+'\''+statue+'\''
			+')"/>'
			+'</td>'
			+'</tr>';
			}
		document.getElementById("drivertable").innerHTML=str;
		document.getElementById("pageshow").innerHTML="第"+page+"页";
		
		},
		error:function(){
			alert("获取数据错误");
			
		}
		
	});
}

function search()
{
	page=1;
	getsearch();
	
}


function pre()
{
	
	if(page>1)
	{
	
		page--;
		getData();
	}
}

function next()
{
	if(datalength>(page*pagesum))
	{
		page++;
		getData();
	}
	
	
	
}



function presearch()
{
	
	if(page>1)
	{
	
		page--;
		getsearch();
	}
}

function nextsearch()
{
	if(datalength>(page*pagesum))
	{
		page++;
		getsearch();
	}
	
	
	
}
//function del(acnum)
//{
//	$.ajax({
//		type:"post",
//		url:"deldriver",
//		data:"acnum="+acnum,
//		dataType:"json",
//		success:function(data)
//		{
//			alert("删除成功");
//		},
//		error:function(){
//			
//			alert("删除错误");
//		}
//		
//	});
//}
function edit(drivernums,name,phone,carnum,cargo,statue)
{
	var openstr="http://"+window.location.hostname+":"+window.location.port+"/baoliang/editedriver?drivernums="+drivernums+"&name="+name+"&phone="+phone+"&carnum="+carnum+"&cargo="+cargo+"&statue="+statue;
	location.href=openstr;
}

function del(drivernums)
{
	$.ajax({
		type:"post",
		url:"deldriver",
		data:"drivernums="+drivernums,
		dataType:"json",
		success:function(data)
		{
			alert("删除成功");
			document.location.reload();
		},
		error:function(){
			
			alert("删除错误");
		}
		
	});
}




function submitdriver()
{
	 var parm = $("#addtable").serialize();
	$.ajax({
		type:"post",
		url:"submitdriver",
		data:parm,
		dataType:"json",
		success:function(data)
		{
			alert("提交成功");
		},
		error:function(){
			
			alert("提交失败");
		}
		
	});
}

