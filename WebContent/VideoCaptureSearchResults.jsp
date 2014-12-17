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
    var table = $('#video_capture_search_table_id').DataTable( {
		"ajax": {
			"url": "search/VideoCaptureSearchResultsAction.action",
			"data":  {
					"datetime": date,
					"roomIDString": room
					<%--"datetime": getParameterByName("date"),
					"roomIDString": getParameterByName("room")--%>
				}
			},
		"columns": [
		  {		"data"	: "videoID" 			},
		  {		"data"	: "capturedVideoName" 	},
		  { 	"data"	: "room.roomID" 		},

		  { 	"data"	: "capturedDateTime" 	},
		  
		  { 	"data"	: "dateAnalysisDone" 	},
		  { 	"data"	: "length" 				},
		  { 	"data"	: "size" 				},
		  { 	"data"	: "machine.machineIP" 	},

		  { 	"data"	: "analysisDirName" 	},

		  { 	"data"	: "uploadedFileName" 	}
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
	<table id="video_capture_search_table_id" class="display">
		<thead>
			<tr>
				<th>Video ID</th>
				<th>Captured Video Name</th>
				<th>Room ID</th>
				<th>Captured DateTime</th>
				<th>Date Analysis Done</th>
				<th>Length</th>
				<th>Size</th>
				<th>Machine IP</th>
				<th>Analysis DirName</th>
				<th>Uploaded FileName</th>
			</tr>
		</thead>
	</table>
</body>
</html>