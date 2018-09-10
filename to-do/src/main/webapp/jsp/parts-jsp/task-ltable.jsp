<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:formatDate pattern="yyyy-MM-dd" value="${todayDate}" var="escapeDateFormat"/>

<table class="task-list">
	<thead>
		<tr class="head">
			<th class="date-column">Date</th>
			<th class="file-column">File</th>
			<th>Task</th>
			<th class="small-column">Mark</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="task" items="${taskList}" varStatus="index">
			<tr>
				<c:choose>
					<c:when test="${task.date lt escapeDateFormat}">
						<td class="date-column-overdue">${task.date}<small><br>overdue</small></td>
					</c:when>
					<c:otherwise>
						<td class="date-column">${task.date}</td>
					</c:otherwise>
				</c:choose>
				<td class="file-column clearfix">
					<c:choose>
						<c:when test="${task.file eq null}">
							<input class="float-left upload-delete-button" name="upl${task.id}" id="${task.id}" type="button" value="Upload"
								onclick="setFieldValue('bodyForm', 'idTaskUpload', ${task.id});
										clearUnnecessaryFileFields('bodyForm', 'ff', 'idTaskUpload'); 
										setBinaryEnctype('bodyForm'); 
										submitToController('bodyForm', 'upload-controller');" />

							<label class="float-left button-choice-file"
								onclick="document.getElementById('fileinput${task.id}').click();">
								Choose file 
							</label>
							<input id="fileinput${task.id}" class="invisible ff" type="file" name="${task.id}"
								onchange="document.getElementById('filenameblock${task.id}').innerHTML = 
										document.getElementById('fileinput${task.id}').value;" />
							<div id="filenameblock${task.id}" class="filename-block float-left">
								file is not selected
							</div>
						</c:when>
						<c:otherwise>
							<input class="float-left upload-delete-button" type="button"
								name="upl${task.id}" value="Delete" id="${task.id}"
								onclick="setFieldValue('bodyForm', 'deleteFileId', ${task.id}); 
									submitToController('bodyForm', 'delete-controller');" /><a href="#" class="float-left" title="${task.file.name}"
								onclick="setFieldValue('bodyForm', 'downloadId', ${task.id}); 
									submitToController('bodyForm', 'download-controller');">${task.file.name}</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					${task.content}
				</td>
				<td class="small-column"><input type="checkbox" 
					name="selectedItem" value="${task.id}">
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>