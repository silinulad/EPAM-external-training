<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.example.intouch.constans.PathJSP"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="action-list">
	<ul class="clearfix">
		<c:choose>
			<c:when	test="${section_type eq 'TODAY' || section_type eq 'TOMORROW' || section_type eq 'SOMEDAY'}">
				<li class="float-left">
					<a href="<c:url value='<%=PathJSP.ADD_TASK_PAGE%>'/>" title="Add task">
						Add task 
					</a>
				</li>
				<li class="float-center">
					<input type="submit" name="fix" value="Fix" title="Fix" class="link">
				</li>
				<li class="float-right">
					<input type="submit" name="recycle"	value="Remove to recycle" title="Remove to recycle" class="link">
				</li>
			</c:when>
			<c:when test="${section_type eq 'FIXED'}">
				<li class="float-center">
					<input type="submit" name="recycle" value="Remove to recycle" class="link">
				</li>
			</c:when>
			<c:when test="${section_type eq 'RECYCLE'}">
				<li class="float-left">
					<input type="submit" name="restore"	value="Restore" title="Restore to the appropriate section"	class="link">
				</li>
				<li class="float-center">
					<input type="submit" name="remove"	value="Remove" title="Remove" class="link">
				</li>
				<li class="float-right">
					<input type="submit" name="remove"	value="Remove all" title="Remove all" class="link"
							onclick="setAllCheckBoxes('bodyForm', 'selectedItem', true);">
				</li>
			</c:when>
		</c:choose>
	</ul>
</div>
