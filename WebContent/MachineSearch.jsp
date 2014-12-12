<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" charset="utf8" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
function loadMachineIps() {
	$.post("search/ListMachineIPsAction.action",
			function(response, status, jqXHR) {
				if (status == "error") {
					var msg = "Sorry but there was an error: ";
					alert(msg + xhr.status + " " + xhr.statusText);
				} else {
					var roomSelect = $("#MachineSelectInput");
					$.each(response.data, function(key, value) {
						var op = new Option();
						op.value = key + 2;
						op.text = value;
						roomSelect.append(op);
					});
				}
			});
}

	$("#dateInput").change(function() {
		$("#machineSelectInput").css('visibility', 'visible');
	});

	$("#roomSelectInput").change(
			function() {
				$("#resultPage").load("MachineSearchResults.jsp");
			});
	
	Date.prototype.toDateInputValue = (function() {
	    var local = new Date(this);
	    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
	    return local.toJSON().slice(0,10);
	});
</script>
</head>
<body>
	<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
	</s:if>
	<form name="machineSearchForm" method="get"
		action="machineSearchResults.jsp">
		<select
			name="MachineSelect" id="MachineSelectInput">
			<option id="all" value="All">All</option>
		</select>
	</form>
	<div id="resultPage"></div>
</body>
</html>