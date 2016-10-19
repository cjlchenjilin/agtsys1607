<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代理商管理系统</title>
<%@ include file="head.html"%>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body class="easyui-layout" fit="true">
	<!-- 页面头部 -->
	<div data-options="region:'north',title:''"
		style="height: 122px; background: url('images/top_1px.gif') repeat-x; overflow: hidden;">
		<!-- 背景图片 -->
		<div
			style="height: 120px; background: url('images/top_bg.gif') no-repeat;">
			<!-- 系统菜单 -->
			<div style="float: right;">
				<a href="javascript:void(0)" id="mb" class="easyui-menubutton"
					data-options="menu:'#mm',iconCls:'icon-edit'">系统菜单</a>
				<div id="mm" style="width: 150px;">
					<div data-options="iconCls:'icon-edit'">修改密码</div>
					<div class="menu-sep"></div>
					<div data-options="iconCls:'icon-remove'">退出系统</div>
				</div>
			</div>
			<!-- 欢迎区块 -->
			<div style="height: 100%; line-height: 110px; margin-left: 600px;">欢迎你，${loginkey.username}！！</div>
		</div>
	</div>
	<!-- 页面底部 -->
	<div data-options="region:'south',title:'',split:false"
		style="height: 50px;">
		<!-- 版权申明 -->
		<div style="text-align: center; line-height: 48px;">汇道科技公司版权所有，违权必究!</div>
	</div>
	<!-- 去掉右边的区域 -->
	<!-- <div data-options="region:'east',title:'East',split:true"
		style="width: 100px;"></div> -->
	<!-- 左边区域 -->
	<div data-options="region:'west',title:'导航菜单',split:true"
		style="width: 150px;">
		<!-- 树形功能菜单 -->
		<div>
			<!-- javascript 实现远程加载菜单数据 -->
			<ul id="tree">
			</ul>
		</div>
	</div>
	<!-- 中心区域 -->
	<div data-options="region:'center',title:''"
		style="padding: 5px; background: #eee;">
		<!-- 通过js代码动态创建子选项卡 -->
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<!-- <div title="Tab1" style="padding: 20px; display: none;">tab1</div>
			<div title="Tab2" data-options="closable:true"
				style="overflow: auto; padding: 20px; display: none;">tab2</div>
			<div title="Tab3" data-options="iconCls:'icon-reload',closable:true"
				style="padding: 20px; display: none;">tab3</div> -->
		</div>
	</div>
	<!-- 公共的表单容器 -->
	<div id="formbox"></div>
</body>
</html>