<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>EM Login</title>
<style type="text/css">
<!--
	body {
		width: 100%;
		margin: 0px;
	}
	header {
		width: 70%;
		height: 200px;
		margin: 0 auto;
		background-color: blue;
	}
	
	#content_container {
		width: 70%;
		height: 400px;
		margin: 0 auto;
		padding: 25px 0px;
		background-color: #efefef;
		text-align: center;
	}
	
	#loginForm {
		margin: 0 auto;
		width: 20%;
	}
-->
</style>
</head>

<body>
	<header>
	<img alt="Mayo Building" src="css/images/mayo-clinic-building.jpg" height="100%" width="100%" />
	</header>
	<div id="content_container">
		<div id="loginForm">
			<s:if test="hasActionErrors()">
				<div class="errors">
					<s:actionerror />
				</div>
			</s:if>
			<s:form action="login" method="post">
				<s:textfield name="username" key="label.username" size="20" />
				<s:password name="password" key="label.password" size="20" />
				<s:submit method="execute" key="label.login" align="center" />
			</s:form>
		</div>
	</div>
</body>
</html>