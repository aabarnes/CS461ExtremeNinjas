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

<script type="text/javascript" charset="utf8" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" charset="utf8"
	src="js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf8"
	src="js/dataTables.tableTools.js"></script>
<script>

$(document).ready( function () {
    $('#table_id').DataTable( {
		"ajax": {
			"url": "search/VideoCaptureSearchResultsAction.action",
			"data":  {
					"datetime": getParameterByName("date"),
					"room": getParameterByName("room"),
					
				}
			},
		"columns": [
		  {		"data"	: "VideoID" 			},
		  {		"data"	: "CapturedVideoName" 	},
		  { 	"data"	: "CapturedDateTime" 	},
		  { 	"data"	: "DateAnalysisDone" 	},
		  { 	"data"	: "Length" 				},
		  { 	"data"	: "Size" 				},
		  { 	"data"	: "MachineIP" 			},
		  { 	"data"	: "AnalysisDirName" 	},
		  { 	"data"	: "RoomID" 				},
		  { 	"data"	: "UploadedFileName" 	}
		]<%--,
		initComplete: function () {
            var api = this.api();
 
            api.columns().indexes().flatten().each( function ( i ) {
                var column = api.column( i );
                var select = $('<select><option value=""></option></select>')
                    .appendTo( $(column.footer()).empty() )
                    .on( 'change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );
 
                        column
                            .search( val ? '^'+val+'$' : '', true, false )
                            .draw();
                    } );
 
                column.data().unique().sort().each( function ( d, j ) {
                    select.append( '<option value="'+d+'">'+d+'</option>' )
                } );
            } );
        }--%>,
        "oTableTools": {
        	"sSwfPath": "media/copy_csv_xls_pdf.swf"
        }
	} );
} );

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search); <%-- May need to changed to window.location.search --%>
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

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