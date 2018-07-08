<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script type="text/javascript">
        
        $(document).ready(function() 
        		{ 
        		    $.ajax({
        		        type: "POST",
        		        contentType: 'application/json; charset=utf-8',
        		        url:"/secquesServlet",
        		        dataType: "json",
        		        async : false,
        		        success: function (data) {
        		            $.each(data.aaData,function(i,obj)
        		            {
        		             var div_data="<h4>"+obj.text+"</h4>";
        		            $(div_data).appendTo('#page1'); 
        		            });  
        		            },error : function(jqXHR, textStatus, errorThrown){
        		                   alert("Error message "+errorThrown);
        		            }
        		      });
        		});
        
        function gohome()
        {
           location.href="Home.jsp";
        }
        
        </script>
</head>
<body bgcolor="#99ccff">
<center> <h1>
        <img src="smartvault_banner.jpg" style="width: 728px; height: 90px" /></h1></center>
         <hr style="height: 6px; background-color: #000000;" /><br>
<form method="post" action="changepwdServlet">
<center>
<table border="0">
<tr>
  <td>current password :</td>
  <td><input type="password" name="oldpwd"></td>
</tr>
<tr>
  <td>new password :</td>
  <td><input type="password" name="newpwd"></td>
</tr>
<tr>
  <td>confirm password :</td>
  <td><input type="password" name="confpwd"></td>
</tr>
</table>

<p id="page1">
</p>
<input type="password" name="ans"><br>
<input type="submit" value="OK">&nbsp;&nbsp;<input type="button" value="cancel" onclick="gohome()">
</center>
</form>
</body>
</html>