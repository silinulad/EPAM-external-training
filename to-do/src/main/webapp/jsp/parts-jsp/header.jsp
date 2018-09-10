<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.example.intouch.constans.PathJSP"%>
<%@page import="com.example.intouch.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="header clearfix">
	<div class="header-footer-content-wrapper">
		<div class="header-footer-column float-left">
			<span> User:
				<strong>
					<c:out value="${currentUser.login}" default="Guest"/>
				</strong>
			</span>
		</div>
		<div class="header-footer-column float-right">
			<c:choose>
				<c:when test="${currentUser eq null}">
					<a class="button green"	href="<c:url value='<%=PathJSP.REGISTER_PAGE%>'/>"	title="Sing up"> Sign up </a>
				</c:when>
				<c:otherwise>
					<a class="button green"	href="<c:url value='<%=Controllers.LOGOUT_CONTROLLER%>'/>" title="Logout"> Logout </a>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="header-footer-column float-right">
			<c:choose>
				<c:when test="${currentUser eq null}">
					<a class="button green"	href="<c:url value='<%=PathJSP.LOGIN_PAGE%>'/>" title="Log in">Log in </a>
				</c:when>
				<c:otherwise>
					<a class="button green"	href="<c:url value='<%=PathJSP.PROFILE_PAGE%>'/>" title="Profile">Profile </a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</header>
