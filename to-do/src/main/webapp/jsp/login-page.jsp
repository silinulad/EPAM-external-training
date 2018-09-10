<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.example.intouch.constans.Constants"%>
<%@page import="com.example.intouch.constans.Controllers"%>
<%@page import="com.example.intouch.constans.PathJSP"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<c:import url="parts-jsp/head-attributes.jsp" />
</head>
<body>
	<c:import url="parts-jsp/header.jsp" />
	<div class="additional-page-content-wrapper-login-page">
		<h1>Login form</h1>
		<h2>Please, fill in the form to access the site</h2>
		
		<div id="errBlock">
			<c:if test="${not empty errorMessage}">
				<div class="additional-page-main-error">
					<c:out value="${errorMessage}" />
				</div>
			</c:if>
		</div>	
		<form method="POST" accept-charset="UTF-8"
			action="<c:url value='<%= Controllers.LOGIN_CONTROLLER %>'/>"
			onsubmit="return loginValidation('errBlock', 'loginId', 'login', 'passId', 'password');">
			<table>
				<tbody>
					<tr>
						<td>
							<%= Constants.LABEL_LOGIN %>
						</td>
						<td colspan="3">
							<input type="text" name="<%= Constants.ENTRY_LOGIN %>"
								id="loginId" placeholder="Login (at least 3 characters)" tabindex="1" autofocus="autofocus">
						</td>
					</tr>
					<tr>
						<td>
							<%= Constants.LABEL_PASSWORD %>
						</td>
						<td colspan="3">
							<input type="password" name="<%= Constants.ENTRY_PASSWORD %>"
								id="passId" placeholder="Password (at least 6 characters)" tabindex="2" >
						</td>
					</tr>
					<tr>
						<td></td>
						<td><a class="button green" href="<c:url value='<%=PathJSP.MAIN_PAGE%>'/>" 
							tabindex="5" title="to the previous page">Back</a></td>
						<td><input type="Reset" name="" value="Reset" tabindex="4"></td>
						<td><input type="submit" name="" value="Submit" tabindex="3"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div class="footer clearfix">
		<c:import url="parts-html/footer.html" />
	</div>
</body>
</html>
