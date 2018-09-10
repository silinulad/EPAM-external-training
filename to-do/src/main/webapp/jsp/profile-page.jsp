<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.example.intouch.constans.Constants"%>
<%@page import="com.example.intouch.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${currentUser == null}">
	<c:redirect url="<%=Controllers.DISTRIB_CONTROLLER%>"/>
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Profile</title>
	<c:import url="parts-jsp/head-attributes.jsp"/>
</head>
<body>
	<c:import url="parts-jsp/header.jsp" />
	<div class="additional-page-content-wrapper-profile">
		<h1>Information about the current user</h1>
		<table >
			<tbody>
				<tr class="row-underline">
					<td class="label-column"><%= Constants.LABEL_LOGIN %></td>
					<td><c:out
							value="${currentUser eq null ? 'Error querying data': currentUser.login }" />
					</td>
				</tr>
				<tr class="row-underline">
					<td class="label-column"><%= Constants.LABEL_PASSWORD %>
					</td>
					<td><c:out
							value="${currentUser eq null ? 'Error querying data': currentUser.password}" />
					</td>
				</tr>
				<tr class="row-underline">
					<td class="label-column"><%= Constants.LABEL_EMAIL %>
					</td>
					<td><c:out
							value="${currentUser eq null ? 'Information is absent': currentUser.email}" />
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<a class="button green"
			href="<c:url value='<%=Controllers.DISTRIB_CONTROLLER%>'/>"
			title="to the previous page">Back</a>
	</div>
	<div class="footer clearfix">
		<c:import url="parts-html/footer.html" />
	</div>
</body>
</html>
