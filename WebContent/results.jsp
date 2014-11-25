<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>

<link href="css/jquery-ui.css" rel="stylesheet" />
<link href="css/jquery.dataTables.css" rel="stylesheet" />

<script type="text/javascript" charset="utf8" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" charset="utf8"
	src="js/jquery.dataTables.js"></script>
<script>

$(document).ready( function () {
    $('#table_id').DataTable( {
		"ajax": "search/getJSONResult.action",
		"type": "POST",
		"columns": [
		  { "data": "id" },
		  { "data": "name" },
		  { "data": "password" }
		]
	} );
} );

</script>

</head>
<body>
	<table id="table_id" class="display">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
        </tr>
    </thead>

	</table>

</body>
</html>