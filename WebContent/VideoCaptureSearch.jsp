<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" charset="utf8" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript">
	function loadRoomNames() {
		$("#roomSelectInput").load("search/ListRoomsAction.action",
				function(response, status, jqXHR) {
					if (status == "error") {
						var msg = "Sorry but there was an error: ";
						alert(msg + xhr.status + " " + xhr.statusText);
					} else {
						var op = new Option();
						op.value = 1;
						op.text = "All";
						$("#roomSelectInput").options.add(op);
						$.each(response, function(key, value) {
							var op = new Option();
							op.value = key + 2;
							op.text = value;
							$("#roomSelectInput").options.add(op);
						});
					}
				});
	}

	$("#dateInput").change(function() {
		$("#roomSelectInput").css('visibility', 'visible');
	});

	$("#roomSelectInput").change(
			function() {
				var params = {
					"date" : $("#dateInput").val(),
					"room" : $("#roomSelectInput").val()
				}
				$("#resultPage").load(
						"VideoCaptureSearchResults.jsp" + $.param(params));
			});

	$(document).ready(function() {
		$("#dateInput").val(new Date().toDateInputValue());
		loadRoomNames();
	});
</script>
</head>
<body>
	<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
	</s:if>
	<form name="videoCaptureSearchForm" method="get"
		action="VideoCaptureSearchResults.jsp">
		Find: <input type="date" name="dateInput" id="dateInput" /> <select
			name="roomSelect" id="roomSelectInput" style="visibility: hidden;">
		</select>
	</form>
	<div id="resultPage"></div>
</body>
</html>