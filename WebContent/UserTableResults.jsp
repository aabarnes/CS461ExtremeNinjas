<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>

<link href="css/jquery-ui.css" rel="stylesheet" />
<link href="css/jquery.dataTables.css" rel="stylesheet" />
<link href="css/dataTables.tableTools.css" rel="stylesheet" />

<script type="text/javascript" charset="utf8" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" charset="utf8"
	src="js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8"
	src="js/dataTables.tableTools.js"></script>
<script>

$(document).ready( function () {
    var table = $('#users_table_id').DataTable( {
		"ajax": {
			"url": "search/ListUsersAction.action",
			},
		"columns": [
		  {		"data"	: "id" 			},
		  {		"data"	: "lastname" 	},
		  { 	"data"	: "firstname" 		},

		  { 	"data"	: "username" 	},
		  
		  { 	"data"	: "email" 	},
		  { 	"data"	: "pos.description" 	},
		],
		"dom": 'T<"clear">lfrtip',
		"oTableTools": {
        	"sSwfPath": "media/copy_csv_xls_pdf.swf",
        	"aButtons": [
        	                "copy",
        	                "print",
        	                {
        	                    "sExtends":    "collection",
        	                    "sButtonText": "Save",
        	                    "aButtons":    [ "csv", "xls", "pdf" ]
        	                }
        	            ]
        }
	} );
} );

</script>

</head>
<body>
	<s:if test="hasActionErrors()">
		<div class="errors">
			<s:actionerror />
		</div>
	</s:if>
	<table id="users_table_id" class="display">
		<thead>
			<tr>
				<th>User ID</th>
				<th>Last Name</th>
				<th>First Name</th>
				<th>Username</th>
				<th>Email address</th>
				<th>Role</th>
			</tr>
		</thead>
	</table>
</body>
</html>