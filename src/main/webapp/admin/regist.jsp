<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
		<title></title>
		
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="/static/images/cloud.jpg" /><span>REGIST</span>
			</div>
			<div id="bottom">
				<form action="main.jsp" method="get">
					<table border="0px" id="table">
						<tr>
							<td class="td1">Username：</td>
							<td><input type="text" placeholder="Username" class="td2" name="myname"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">Password：</td>
							<td><input type="password" placeholder="Password" class="td2" name="mypwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="Submit" class="td3">
								<input type="reset" value="Cancel" class="td3	">
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</body>

</html>