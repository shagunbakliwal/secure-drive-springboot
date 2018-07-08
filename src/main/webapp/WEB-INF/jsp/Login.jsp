<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SmartVault_login</title>
<link rel="stylesheet" href="startup.css" type="text/css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
function check(){
	var name=document.getElementById('Uname').value;
	if(name==""){
		alert("please Enter Name")
		return false;
	}
	else{
		return true;
		document.getElementById('lg1').action="/forgpwdServlet";
		document.getElementById("lg1").submit();
	}
}
function checklogin(){
	var name=document.getElementById('Uname').value;
	var pwd=document.getElementById('pwd').value;
	if(name==""){
		alert("please Enter Name")
		return false;
	}
	else if(pwd==""){
		alert("please Enter password")
		return false;
	}
	else{
		return true;
		}
	}
</script>
<style type="text/css">
/*#fgpwd
[
background:none;
border:none;
padding:0;
font:inherit;
cursor:pointer;
]*/
.btn {
	padding: 7px 10px;
	font-size:16px;
	background-color: #6A5ACD;
	color: #FFFFFF;
	line-height: 1;
	border-radius: 3px;
}

body {
	font-size: 16px;
	margin: 0px;
	font-family: Verdana;
	left: 0px;
	top: 0px;
	right: 0px;
	bottom: 0px;
	width: 100%;
	height: 100%;
}
.banner{
background-color: #FFFFFF;
}
#signin { 
    background-color: #696969;
}
</style>
</head>
<body bgcolor="#99ccff">
	<center>
		<form name="login" id="lg1" method="post" action="loginServlet">
			<div id="banner" class="banner">
				<h1>
					<img src="smartvault_banner.jpg" style="width: 50px;hieght: 10px" />
				</h1>
			</div>
			<div id="signin" style="width:500px;height:400px;border:1px solid #000;">
				<br> <br> User Name:<input type="text" name="username"
					id="uname" value="" /> <br> <br> Password:<input
					type="password" name="password" id="pwd" value="" /> <br> <br>
				<input type="submit" class="btn" value="      sign in      " name="Sign In" /> <br><br>
				<input type="submit" class="btn" id="fgpwd" value="forgot password?"
					name="Sign In" onclick="Home.jsp">
				<h3>Don't have an account yet?</h3>
				<h3>Sign up here for free</h3>
				<a href="Signup.jsp">Sign up</a>
			</div>
		</form>
	</center>
</body>
</html>