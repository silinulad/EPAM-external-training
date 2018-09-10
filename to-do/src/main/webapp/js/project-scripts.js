var EMPTY_STRING = "";

function clearUnnecessaryFileFields(FormName, idFileField, idTaskUpload) {
	if (!document.forms[FormName]) {
		return;
	}

	var form = document.forms[FormName];
	var objFileFields = form.querySelectorAll(idFileField);

	if (!objFileFields) {
		return;
	}

	var selectUploadId = document.forms[FormName].elements[idTaskUpload].value;
	var countFileFields = objFileFields.length;

	for (var i = 0; i < countFileFields; i++) {
		if (selectUploadId != objFileFields[i].name) {
			objFileFields[i].value = EMPTY_STRING;
		}
	}
}

function setAllCheckBoxes(FormName, fieldName, isChecked) {

	if (!document.forms[FormName]) {
		return;
	}

	var objCheckBoxes = document.forms[FormName].elements[fieldName];

	if (!objCheckBoxes) {
		return;
	}

	var countCheckBoxes = objCheckBoxes.length;

	if (!countCheckBoxes) {
		objCheckBoxes.checked = isChecked;
	} else {
		// set the check value for all check boxes
		for (var i = 0; i < countCheckBoxes; i++)
			objCheckBoxes[i].checked = isChecked;
	}

}

function setBinaryEnctype(formName) {
	if (!document.forms[formName]) {
		return;
	}
	document.forms[formName].enctype = 'multipart/form-data';
}

function setFieldValue(formName, fieldName, fieldValue) {
	if (!document.forms[formName])
		return;
	document.forms[formName][fieldName].value = fieldValue;
}

function submitToController(formName, controller) {
	if (!document.forms[formName]) {
		return;
	}
	document.forms[formName].action = controller;
	document.forms[formName].submit();
}

function trimStr(str) {
	var SPACE_PATTERN = /^(\s|\u00A0)+|(\s|\u00A0)+$/g;
	return str.replace(SPACE_PATTERN, EMPTY_STRING);
}

function addTaskValidation(idErrBlock, idContent, idDate) {
	
	var errBlock = document.getElementById(idErrBlock);
	errBlock.innerHTML = EMPTY_STRING;
	
	var HEAD = '<div class="additional-page-main-error"> You need to enter';
	var TAIL = 'to the field';	
	var DATE_PATTERN = /^2\d{3}(-)(0[1-9]|1?[012])\1(0[1-9]|[12]\d|3[01])$/g;

	var taskContent = document.getElementById(idContent);
	var trimmedContent = trimStr(taskContent.value);
	if (trimmedContent == EMPTY_STRING) {
		errBlock.innerHTML = HEAD + ' text ' + TAIL;
		taskContent.value = trimmedContent;
		return false;
	}
	
	var taskDate = document.getElementById(idDate);
	var trimmedDate = trimStr(taskDate.value);
	if (trimmedDate == EMPTY_STRING) {
		errBlock.innerHTML =  HEAD + ' date ' + TAIL;
		taskDate.value = trimmedDate;
		return false;
	}
	
	var dateRegExp = new RegExp(DATE_PATTERN);

	if (!dateRegExp.test(trimmedDate)) {
		errBlock.innerHTML = HEAD + ' date on correct format ' + TAIL;
		return false;
	}
	return true;
}
