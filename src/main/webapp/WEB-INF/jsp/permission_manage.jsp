<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
	<ul>
		<li class="jslist">
			<ul>
				<c:forEach items="${roles }" var="role">
					<li>
						<div id="sidebar">
							<a href="javascript:;" onclick="getFunctionsByRoleId(${role.id},'${ role.rolename}')">
								+${ role.rolename} </a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</li>
		<li class="iframeli">
			<table id="permission_dg"></table>
		</li>
	</ul>
</div>
<link href="css/permission.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/permission.js"></script>