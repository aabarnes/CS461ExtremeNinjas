<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Room Utilization</title>
<link rel="stylesheet" type="text/css" href="css/jquery.jqChart.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.jqRangeSlider.css" />

<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="js/jquery.mousewheel.js" type="text/javascript"></script>
<script src="js/jquery.jqChart.min.js" type="text/javascript"></script>
<script src="js/jquery.jqRangeSlider.min.js" type="text/javascript"></script>
<!--[if IE]><script lang="javascript" type="text/javascript" src="js/excanvas.js"></script><![endif]-->

<script lang="javascript" type="text/javascript">
	$(document).ready(function () {
		$('#jqChart').jqChart({
	        title: { text: 'Room Video Capture Schedule' },
	        legend: { visible: false },
	        animation: { duration: .5 },
	        shadows: {
	            enabled: true
	        },
	        border: {
	            cornerRadius: 1,
	            strokeStyle: 'gray',
	            padding: {
	                top: 8,
	                left: 8,
	                bottom: 20,
	                right: 8
	            }
	        }, series: [
	            {
	                type: 'gantt',
	                fillStyle: '#418CF0',
	            	data: [
	            		['Room 1', new Date(2010, 0, 1, 3, 45, 32, 4), new Date(2010, 0, 20, 5, 3, 14, 56)],
	            		['Room 2', new Date(2010, 0, 21), new Date(2010, 1, 15)],
	            		['Room 3', new Date(2010, 1, 16), new Date(2010, 1, 28)],
	            		['Room 4', new Date(2010, 2, 1), new Date(2010, 2, 20)],
	            		['Room 5', new Date(2010, 2, 21), new Date(2010, 3, 10)],
	            		['Room 6', new Date(2010, 0, 21), new Date(2010, 1, 15)],
	            		['Room 5', new Date(2010, 1, 16), new Date(2010, 1, 28)],
	            		['Room 3', new Date(2010, 2, 1), new Date(2010, 2, 20)],
	            		['Room 4', new Date(2010, 2, 21), new Date(2010, 3, 10)],
	            		['Room 1', new Date(2010, 1, 16), new Date(2010, 1, 28)],
	            		['Room 2', new Date(2010, 3, 11), new Date(2010, 4, 1)],
	            	]
	            }
	        ]
	    });
		
		 // get current series
        var series = $('#jqChart').jqChart('option', 'series')

        // get the data from the first series
        var data = series[0].data

        for(i = 0; i < 1; i++) {
        	// add new data item
			data.push(['Room 6', new Date(2010, 1, 16), new Date(2010, 1, 20)]);
        }

        // update (redraw) the chart
        $('#jqChart').jqChart('update');
	});
</script>
</head>
<body>
	<div id="jqChart" style="width: 800px; height: 700px;">
	</div>
</body>
</html>