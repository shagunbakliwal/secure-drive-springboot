<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
       <title>Sign Up</title>
        <style>
            #signup{
               
                font-size: medium; 
                font-weight: bold;
            }
            .btn {
	padding: 7px 10px;
	font-size:16px;
	background-color: #6A5ACD;
	color: #FFFFFF;
	line-height: 1;
	border-radius: 3px;
}
            body
				{
   					font-size: 9px;
    				margin: 0px;
    				font-family: Verdana;
    				left: 0px;
    				top: 0px;
   					right: 0px;
    				bottom: 0px;
    				width: 100%;
   				    height: 100%;
					}
            
            table{
                text-align:left;
                border-spacing: 5px;
            }
            
            <script type="text/jscript">
     function checkmail()
     {
 var email=document.getElementById('email').value;
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!filter.test(email))
       {
          alert('Please provide a valid email address');
          return false;
      }
      else
      {
      return true;
      }
     }
     
     function checkpwd()
     {
      var pwd=document.getElementById('pwd').value;
      if(pwd.length<6 || pwd.length>12)
    {
     alert("password length should be between 6 and 12 characters");
     return false;
    }
    else
    {
    return true;
    }
     }
     funtion checkpwdmatch()
     {
     var pwd=document.getElementById('pwd').value;
     var conpwd=document.getElementById('conpwd').value;
     if(pwd!=conpwd){
     alert("The password and the confirmed password does not match please try again");
     return false;
     } 
     else{
     return true;
     }
     }
     
     function checkcontact()
     {
      var con=document.getElementById('con').value;
      if(con.length<10 || con.length>10)
    {
     alert("please enter valid contact number");
     return false;
     
    }
    else
    {
    return true;
    }
     }
     
     function validateall()
     {
       var email=document.getElementById('email').value;
       var name=document.getElementById('name').value;
       var con=document.getElementById('con').value;
       var pwd=document.getElementById('pwd').value;
      var conpwd=document.getElementById('conpwd').value;
      var ans1=document.getElementById('ans1').value;
       var ans2=document.getElementById('ans2').value;
        var ans3=document.getElementById('ans3').value;
        
        if(email=="" || name=="" || con=="" || pwd== "" || conpwd== "" ans1=="" || ans2=="" || ans3=="")
        {
         alert("Please fill all the fields");
         return false;
        }
        else
        {
         return true;
        }
     }
     
     </script>
        </style> 
</head>
<body bgcolor="#99ccff">
<form action="signupServlet" method="POST" name="signup">
         <center> <h1>
        <img src="smartvault_banner.jpg" style="width: 50px;hieght: 10px"/></h1></center>
        <div id="signup">
        <center>
           <table border="0" width="80%" bgcolor="#85A6F3">
            <col width="50%" align="left" />
            <col width="30%" align="center" />
            
                    
                    <tr>
                        <td>User name: </td>
                        <td><input type="text" name="uname" id="name" value="" /></td>
                    </tr>
                    <tr>
                        <td> Password: </td>
                        <td> <input type="password" name="pwd" id="pwd" value="" onblur="return checkpwd()" /></td>
                    </tr>
                    <tr>
                        <td> Confirm your password: </td>
                        <td><input type="password" name="confpwd" id="conpwd" value="" onblur="return checkpwdmatch()"/></td>
                    </tr>
                    <tr>
                        <td>Email address: </td>
                        <td> <input type="text" name="email" id="email" value="" onblur="return checkmail()" /></td>
                    </tr>
                    <tr>
                        <td> Contact No: </td>
                        <td><input type="text" name="contact" id="con" value="" onblur="return checkcontact()"/></td>
                    </tr>
                   </table>
                   <br>
                   <hr style="height: 6px; background-color: #000000;" /><br>
                   <br>
                   
                   <center><h5>Let's take a moment and answer this security questions</h5> </center>
                   <table border="0" width="80%" bgcolor="#85A6F3">
            <col width="50%" align="left" />
            <col width="30%" align="center" />
                   <tr>
                        <td>What time of the day were you born?</td>
                        <td> <input type="text" name="ans1" id="ans1" value=""  /></td>
                    </tr>
                    <tr>
                        <td>What is the middle name of your youngest cousin?</td>
                        <td> <input type="text" name="ans2" value="" id="ans2"  /></td>
                    </tr>
                    <tr>
                        <td>What was the last name of your first grade teacher</td>
                        <td> <input type="text" name="ans1" value="" id="ans3" /></td>
                    </tr>
                   
                   </table>
                   <input type="submit" class="btn" name="btn1" value="Sign Up" onclick="return validateall()"/>
                    </center>                   
        </div>            
        </form>

</body>
</html>