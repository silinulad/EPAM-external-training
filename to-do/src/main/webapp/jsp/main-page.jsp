<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.example.intouch.constans.Controllers"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Main page</title>
	<%@include file="parts-jsp/head-attributes.jsp"%>
</head>
<body>

	<c:if test="${currentUser != null}">
		<form style="display: none;" action="distribution-controller" id="distrib-today"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="today"></form>
		<form style="display: none;" action="distribution-controller" id="distrib-tomorrow"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="tomorrow"></form>
		<form style="display: none;" action="distribution-controller" id="distrib-someday"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="someday"></form>
		<form style="display: none;" action="distribution-controller" id="distrib-fixed"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="fixed"></form>
		<form style="display: none;" action="distribution-controller" id="distrib-recycle"
		method="POST" accept-charset="UTF-8"><input type="hidden" name="recycle"></form>
	</c:if>
	
	<form id="mainFormId" style="display: inline;" name="bodyForm"	method="POST" accept-charset="UTF-8"
		action="<c:url value='<%=Controllers.ACTION_CONTROLLER%>'/>">
		<input type="hidden" id="idTaskUpload" name="idTaskUpload" value="" /> 
		<input type="hidden" id="downloadId" name="downloadTaskId" value="" /> 
		<input type="hidden" id="deleteFileId" name="deleteFileTaskId" value="" />
		<div class="wrapper">
			<c:import url="parts-jsp/header.jsp" />
			<c:choose>
				<c:when test="${currentUser != null}">
					<div class="content-with-action">
						<%@include file="parts-jsp/navigation-menu.jsp"%>
						<div class="current-message">
							<hr>
							Current section: <strong><c:out value="${section_type}" /></strong>
							<hr>
						</div>
						<c:if test="${not empty errorMessage}">
							<div class="error-page-block">
								<strong><c:out value="${errorMessage}" /></strong>
							</div>
						</c:if>
						<%@include file="parts-jsp/task-ltable.jsp"%>

					</div>
				</c:when>
				<c:otherwise>
					<%@include file="parts-html/welcome-message.html"%>
				</c:otherwise>
			</c:choose>
		</div>
		<c:choose>
			<c:when test="${currentUser != null}">
				<footer class="footer-with-action">
					<%@include file="parts-jsp/footer-action.jsp"%>
					<%@include file="parts-html/footer.html"%>
				</footer>
			</c:when>
			<c:otherwise>
				<footer class="footer clearfix">
					<%@include file="parts-html/footer.html"%>
				</footer>
			</c:otherwise>
		</c:choose>
	</form>
</body>
</html>