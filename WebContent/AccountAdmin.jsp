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
var date;
var room;
	$(function loadRoomNames() {
		$.post("search/ListRoomsAction.action",
				function(response, status, jqXHR) {
					if (status == "error") {
						var msg = "Sorry but there was an error: ";
						alert(msg + xhr.status + " " + xhr.statusText);
					} else {
						var roomSelect = $("#roomSelectInput");
						$.each(response.data, function(key, value) {
							var op = new Option();
							op.value = key + 2;
							op.text = value;
							roomSelect.append(op);
						});
					}
				});
	});

	$("#dateInput").change(
		function() {
			date = $("#dateInput").val();
			room = $("#roomSelectInput").val();
			console.log("date: " + date);
			console.log("room: " + room);
			var params = {
				"date" : $("#dateInput").val(),
				"room" : $("#roomSelectInput").val()
			}
			console.log("UserTableResults.jsp?" + $.param(params));
			$("#resultPage").load(
					"UserTableResults.jsp?" + $.param(params));
		});

	$("#roomSelectInput").change(
			function() {
				date = $("#dateInput").val();
				room = $("#roomSelectInput").val();
				var params = {
					"date" : $("#dateInput").val(),
					"room" : $("#roomSelectInput").val()
				}
				console.log("VideoCaptureSearchResults.jsp?" + $.param(params));
				$("#resultPage").load(
						"VideoCaptureSearchResults.jsp?" + $.param(params));
			});

	$(document).ready(function() {
		$("#dateInput").val(new Date().toDateInputValue());
		$("#dateInput").trigger('change');
		<%-- loadRoomNames(); --%>
	});
	
	Date.prototype.toDateInputValue = (function() {
	    var local = new Date(this);
	    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
	    return local.toJSON().slice(0,10);
	});
	
	function insertParam(key, value) {
        key = escape(key); value = escape(value);

        var kvp = document.location.search.substr(1).split('&');
        if (kvp == '') {
            document.location.search = '?' + key + '=' + value;
        }
        else {

            var i = kvp.length; var x; while (i--) {
                x = kvp[i].split('=');

                if (x[0] == key) {
                    x[1] = value;
                    kvp[i] = x.join('=');
                    break;
                }
            }

            if (i < 0) { kvp[kvp.length] = [key, value].join('='); }

            //this will reload the page, it's likely better to store this until finished
            document.location.search = kvp.join('&');
        }
    }
</script>
</head>
<body>
	<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
	</s:if>
	<h1>User Information</h1>
	<!-- <form name="videoCaptureSearchForm" method="get"
		action="UserTableResults.jsp">
		Find: <input type="date" name="dateInput" id="dateInput" />
		<select
			name="roomSelect" id="roomSelectInput" >
			<option id="all" value="All">All</option>
		</select>
	</form> -->
	<div id="resultPage"></div>
</body>
</html>