<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../Lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="../Lib/jquery.min.js"></script>
 <script src="../Lib/bootstrap/js/bootstrap.min.js"></script>
 <link href="css/DriverMa.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>

<div class="btn-group btntitle">
  <button type="button" onclick="showDriver()"class="btn btn-default driverlist">司机列表</button>
  <button type="button" onclick="addDriver()" class="btn btn-default adddriver">添加司机</button>
</div>
<div id="searchap" class="searchap">
		<div class="input-group">
      		<input id="searchinput" type="text" class="form-control" placeholder="Search for...">
     		 <span class="input-group-btn">
        		<button class="btn btn-default" type="button" onclick="search()">司机编号查找</button>
     		</span>
     		</div>
     		</div>
	<table class="table table-hover drivertable" id="drivertable">
		<tr>
		<td>司机编号</td>
		<td>姓名</td>
		<td>电话</td>
		<td>车牌号</td>
		<td>载重</td>
		<td>状态</td>
		<td>操作</td>
		</tr>
	</table>
	
	<form id="addtable">
		 <div class="form-group">
		 	<label>司机编号</label>
		 	<input  class="form-control"  readonly="readonly" placeholder="由系统统生成" value="由系统统生成">
		 </div>
		 <div class="form-group">
		 	<label>姓名</label>
		 	<input name="name" class="form-control"  placeholder="输入姓名">
		 </div>
		 <div class="form-group">
		 	<label>电话</label>
		 	<input name="phone" class="form-control"  placeholder="输入电话">
		 </div>
		 <div class="form-group">
		 	<label>车牌号</label>
		 	<input name="carnum" class="form-control"  placeholder="输入车牌号">
		 </div>
		<div class="form-group">
		 	<label>载重量</label>
		 	<input name="cargo" class="form-control"  placeholder="输入载重量">
		</div>
		<center><button type="button" class="btn btn-success" onclick="submitdriver()">提交</button></center>
	</form>
	<center id="pagemodel"><div>
	<button id="dshow1" type="button" class="btn btn-primary pages" onclick="pre()">上一页</button>
	<button id="sshow1" type="button" class="btn btn-primary pages" onclick="presearch()">上一页</button>
	<span class="label label-danger pages" id="pageshow">第1页</span>
	<button id="dshow2" type="button" class="btn btn-primary pages" onclick="next()">下一页</button>
	<button id="sshow2" type="button" class="btn btn-primary pages" onclick="nextsearch()">下一页</button></div></center>
	<script type="text/javascript" src="js/DriverMa.js"></script>
</body>
</html>