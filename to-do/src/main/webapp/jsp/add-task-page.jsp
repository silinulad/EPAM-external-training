<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.example.intouch.constans.Constants"%>
<%@page import="com.example.intouch.constans.Controllers"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Add task</title>

<c:import url="parts-jsp/head-attributes.jsp" />
</head>
<body>
	<div class="additional-page-content-wrapper-add-task">
		<h1>Add a new task</h1>
		<h2>
			The task will be added to the section: 
			<strong> 
				<c:choose>
					<c:when test="${section_type eq 'TODAY'}">
	                    Today
	                </c:when>
					<c:when test="${section_type eq 'TOMORROW'}">
	                    Tomorrow
	                </c:when>
					<c:when test="${section_type eq 'SOMEDAY'}">
	                    Someday
	                </c:when>
				</c:choose>
			</strong>
		</h2>

		<div id="errBlock">
			<c:if test="${not empty errorMessage}">
				<div class="additional-page-main-error">
					<c:out value="${errorMessage}" />
				</div>
			</c:if>
		</div>
		<form name="add-task-form" method="POST" accept-charset="UTF-8"
			action="<c:url value='<%= Controllers.ADD_TASK_CONTROLLER %>'/>"
			onsubmit="return addTaskValidation('errBlock', '<%= Constants.TASK_CONTENT %>', '<%= Constants.TASK_DATE %>');">
			<table>
				<tbody>
					<c:if test="${section_type eq 'SOMEDAY'}">
						<tr>
							<td colspan="3" >
								<p><%= Constants.LABEL_TASK_DATE %>
								<input type="text" name="<%= Constants.TASK_DATE %>" id="<%= Constants.TASK_DATE %>"
								placeholder="2018-03-01" value="">&nbsp;(Date format: yyyy-mm-dd)</p> 
								<br/>
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="3">
							<textarea id="<%= Constants.TASK_CONTENT %>" name="<%= Constants.TASK_CONTENT %>"
								<c:choose>
									<c:when test="${section_type eq 'TODAY' || section_type eq 'TOMORROW'}">
										class="today-tommorow-textarea"
									</c:when>
									<c:when test="${section_type eq 'SOMEDAY'}">
										class="someday-textarea"
									</c:when>
								</c:choose>
								placeholder="Write task here" autofocus="autofocus" tabindex="1" ></textarea>
							<p>
						</td>
					</tr>
					<tr>
						<td><a href="<c:url value='<%=Controllers.DISTRIB_CONTROLLER%>'/>"
							class="button green" title="to the previous page" tabindex="3">Back</a></td>
						<td><input type="reset" name="reset" value="reset" tabindex="4"></td>
						<td><input type="submit" name="submit" value="submit" tabindex="2"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
