<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.example.intouch.constans.Constants"%>
<%@page import="com.example.intouch.constans.Controllers"%>
<%@page import="com.example.intouch.constans.PathJSP"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<title>Register</title>
<c:import url="parts-jsp/head-attributes.jsp" />
</head>
<body>
	<c:import url="parts-jsp/header.jsp" />
	<div class="additional-page-content-wrapper-register-page">
		<h1>Registration form</h1>
		<h2>Please, fill in the registration form</h2>

		<div id="errBlock">
			<c:if test="${not empty errorMessage}">
				<div class="additional-page-main-error">
					<c:out value="${errorMessage}" />
				</div>
			</c:if>
		</div>
		<form name="register-form" method="POST" accept-charset="UTF-8"
			action="<c:url value='<%=Controllers.REGISTER_CONTROLLER%>'/>"
			onsubmit="return registerValidation('errBlock', 'loginId', 
				'passId', 'retypePassId', 'emailId');">
			<table>
				<tbody>
					<tr>
						<td><%=Constants.LABEL_LOGIN%></td>
						<td colspan="3"><input type="text"
							name="<%=Constants.REGISTER_LOGIN%>" id="loginId"
							placeholder="Login (at least 3 characters)" tabindex="1"
							autofocus="autofocus"></td>
					</tr>
					<tr>
						<td><%=Constants.LABEL_PASSWORD%></td>
						<td colspan="3"><input type="password"
							name="<%=Constants.REGISTER_PASSWORD%>" id="passId" tabindex="2"
							placeholder="Password (at least 6 characters)"></td>
					</tr>
					<tr>
						<td><%=Constants.LABEL_RETYPE_PASSWORD%></td>
						<td colspan="3"><input type="password"
							name="<%=Constants.REGISTER_RETYPE_PASSWORD%>" id="retypePassId"
							tabindex="3" placeholder="Retype password"></td>
					</tr>
					<tr>
						<td><%=Constants.LABEL_EMAIL%></td>
						<td colspan="3"><input type="text"
							name="<%=Constants.REGISTER_EMAIL%>" id="emailId"
							placeholder="E-mail" tabindex="4"></td>
					</tr>
					<tr>
						<td></td>
						<td><a class="button green"
							href="<c:url value='<%=PathJSP.MAIN_PAGE%>'/>"
							tabindex="6" title="to the previous page">Back</a></td>
						<td><input type="reset" name="" value="Reset" tabindex="7"></td>
						<td><input type="submit" name="" value="Submit" tabindex="5"></td>
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
