<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<div class="searchuserdiv">
		<div>
			<!-- 保存用户的userid -->
			<input id="searchUserId" value="" type="hidden" />
			搜索用户：<input id="searchUserText" type="text" /> 输入后自动搜索
			<div id="serachresult" class="searchresult">
				
			</div>
		</div>
	</div>
	<div class="searchuserdiv ope">
		<ul>
			<li>操作类型：
			<select id="accounttype" name="accounttype">
				<option value="">--请选择--</option>
				<c:forEach items="${accountTypeList}" var="accounttype">
					<option value="${accounttype.configtypevalue}">${accounttype.configtypename}</option>
				</c:forEach>
			</select></li>
			
			<li class="important">操作资金：<input name="money" type="text" id="money"/> 
			<br/>重要提示：输入的资金数，<b>正数(1000)</b>为向账户增加1000元，<b>负数（-1000）</b>
			为向账户减少1000元，精确到小数点两位数</li>
			<li>操作备注：<br/><textarea id="memo" name="memo"></textarea></li>
			<li><input id="caiwuok" type="button" value="确定"/></li>
			<li class="tip">操作提示 : <span id="systemtip">输入的资金数，正数(1000)为向账户增加1000元，
			负数（-1000）为向账户减少1000元，精确到小数点两位数</span></li>
		</ul>
	</div>
</div>
<link href="css/account.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/account.js"></script>