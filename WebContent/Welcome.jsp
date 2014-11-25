<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
<head>
<title>Welcome</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.11.1.js"></script>
<script src="js/jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<sj:head />
</head>
<body>
	<h2>
		Howdy,
		<s:property value="username" />
		...!
	</h2>
	<div class="wrapper">
		<div class="container">
			<div id="tabs">
				<ul>
					<li><a href="#login">Login</a></li>
					<li><a href="#register">Register</a></li>
				</ul>
				<div id="login">
					<h3>Login</h3>
				</div>
				<div id="register">
					<h3>Register</h3>
				</div>
			</div>
		</div>
	</div>
    <s:url var="logout" action="logout"/>
    <s:url var="dummy1" action="ajax2"/>
    <s:url var="search/VideoCaptureSearch" action="search/VideoCaptureSearchResultsAction"/>
    <s:url var="remoteurl4" action="ajax4"/>
    <s:url var="remoteurl5" action="echo"/>
    <sj:tabbedpanel id="remotetabs" selectedTab="1" show="true" hide="'fade'" >
        <sj:tab id="tab1" href="%{logout}" label="Logout"/>
        <sj:tab id="tab2" href="%{dummy1}" label="Remote Tab Two"/>
        <sj:tab id="tab3" href="VideoCaptureSearch.jsp" label="Search Results"/>
        <sj:tab id="tab4" href="%{remoteurl4}" label="Remote Tab Four"/>
        <sj:tab id="tab5" formIds="echoForm" href="%{remoteurl5}" label="Echo Tab"/>
    </sj:tabbedpanel>
</body>
</html>