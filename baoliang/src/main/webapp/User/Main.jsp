<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="Lib/jquery.min.js"></script>
<link href="Lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 <script src="Lib/bootstrap/js/bootstrap.min.js"></script>
 <link href="User/css/Main.css" rel="stylesheet">
<title>用户主页</title>
</head>
<body>
<div class="row navigator">
 <button type="button" class="btn btn-primary logout" onclick="logout()">退出</button>
<span id="username" class="label label-default usernamelabel"><s:property value="username" /></span>
</div>
<div class="row">
<div class="btn-group btn-group-justified">
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-primary" onclick="select(1)">提交申请</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-primary" onclick="select(2)">未完成订单</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-primary" onclick="select(3)">完成订单</button>
  </div>
  <div class="btn-group" role="group">
    <button type="button" class="btn btn-primary" onclick="select(4)">已分配订单</button>
  </div>
  <!-- 修改个人信息暂时不用 <div class="btn-group" role="group">
    <button type="button" class="btn btn-primary" onclick="select(5)">修改个人信息</button>
  </div>-->
</div>
</div>
<div class="row">
<div id="form1">
<form class="tableleft" id="tableleft">
  <div class="form-group">
	<label class="la">姓名</label>
	<input type="text" id="boss" name="boss" class="form-control" value='<s:property value="name"/>' readonly="readonly"/>
	</div>
	 <div class="form-group">
	<label class="la">电话</label>
	<input type="text" id="phone" name="phone" class="form-control" value='<s:property value="username"/>' readonly="readonly"/>
	</div>
	 <div class="form-group">
	<label class="la">货物</label>
	<input type="text" id="goods" name="goods" class="form-control"/>
	</div>
	<div class="form-group">
	<label class="la">接货人</label>
	<input type="text" id="receiver" name="receiver" class="form-control"/>
	</div>
	<div class="form-group">
	<label class="la">接货人电话</label>
	<input type="text" id="recephone" name="recephone" class="form-control"/>
	</div>
	<div class="form-group">
	<label class="la">起始点</label>
	<input type="text" id="start" name="start" class="form-control" onclick="openstart()" readonly="readonly"/>
	 经度:<span id="longitudestart"></span>纬度:<span id="latitudestart"></span>
	<input type="button" onclick="openwin(1)" value="打开地图"/>
	</div>
	<div class="form-group">
	<label class="la">目的地</label>
	<input type="text" id="destination" name="destination" class="form-control" onclick="opendestination()" readonly="readonly"/>
	经度:<span id="longitudedestination"></span>纬度:<span id="latitudedestination"></span>
	<input type="button" onclick="openwin(2)" value="打开地图"/>
	</div>
	<div class="form-group">
	<label class="la">重量(吨)</label>
	<input type="text" id="weight" name="weight" class="form-control" onclick="opendestination()"/>
	</div>
	<div class="form-group">
	<input type="button" value="提交" onclick="commitapplication()" class="btn btn-primary form-control"/>
	</div>
</form>
</div>
<div id="form2">
<table id="tablemidle" class="table table-hover tablemidle"></table>
	<button type="button" class="btn btn-default" onclick="preview(2)">上一页</button>
	<button type="button" class="btn btn-default" onclick="next(2)">下一页</button>
	<span class="label label-default" id="pageshow2">第1页</span>
</div>
<div id="form3">
<table id="tableright" class="table table-hover tableright"></table>

<button type="button" class="btn btn-default" onclick="preview(3)">上一页</button>
<button type="button" class="btn btn-default" onclick="next(3)">下一页</button>
<span class="label label-default" id="pageshow3">第1页</span>
</div>
<div id="form4">
<table id="tableac" class="table table-hover tableac"></table>
	<button type="button" class="btn btn-default" onclick="preview(4)">上一页</button>
	<button type="button" class="btn btn-default" onclick="next(4)">下一页</button>
	<span class="label label-default" id="pageshow4">第1页</span>
</div>
<!-- 修改个人信息暂时不用 <div id="form5">
 <form class="personinfo">
	 <div class="form-group">
		<label class="la">个人姓名</label>
		<input type="text" id="boss" name="boss" class="form-control" value='<s:property value="name"/>' readonly="readonly"/>
	</div>
 </form>
</div>-->
</div>
<script type="text/javascript" src="User/js/Main.js"></script>
</body>
</html>