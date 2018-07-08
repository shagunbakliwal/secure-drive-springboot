<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Smartvault_home</title>
        <style>
            body{
                width: 100%;
                
            }
            
            #downbtt
            {
            
             background-color: #8A8CE6;
             padding:0;
             width:200px;
             height:130px;
             font-weight: bold;
             background-position: center bottom;
             border: medium groove #000000; 
             background-image: url('Download-icon.png');
             background-repeat: no-repeat;
             font-family: "Times New Roman", Georgia, Serif;
             cursor: pointer;
            }
            
        </style>  
       <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> 
        <script type="text/javascript">
        
$(document).ready(function() 
{ 
    $.ajax({
        type: "POST",
        contentType: 'application/json; charset=utf-8',
        url:"/homeServlet",
        dataType: "json",
        async : false,
        success: function (data) {
            $.each(data.aaData,function(i,obj)
            {
             var div_data="<option value="+obj.value+">"+obj.text+"</option>";
            $(div_data).appendTo('#ch_user1'); 
            });  
            },error : function(jqXHR, textStatus, errorThrown){
                   alert("Error message "+errorThrown);
            }
      });
});

$(document).ready(function() 
		{ 
		    $.ajax({
		        type: "POST",
		        contentType: 'application/json; charset=utf-8',
		        url:"/homeServlet",
		        dataType: "json",
		        async : false,
		        success: function (data) {
		            $.each(data.aaData,function(i,obj)
		            {
		             var list_data="<li>"+obj.text+"</li>";
		            $(list_data).appendTo('#folder_list'); 
		            });  
		            },error : function(jqXHR, textStatus, errorThrown){
		                   alert("Error message "+errorThrown);
		            }
		      });
		});

$(document).ready(function(){
	
		$.ajax({
	        type: "POST",
	        contentType: 'application/json; charset=utf-8',
	        url:"/dispfileServlet",
	        dataType: "json",
	        async : false,
	        success: function (data) {
	            $.each(data.fdata,function(i,obj)
	            {
	            	var $btn=$('<input/>').attr({ type: 'submit', name:'btn',value:obj.text, id:'downbtt'});
	            	$("#showfiles").append($btn);
	            	//$("#showfiles").append("<br>");
	            });  
	            //},error : function(jqXHR, textStatus, errorThrown){
	                   alert("Error message "+errorThrown);
	            }
	      });
});

function checkdd()
  {
	var val=document.getElementById("ch_user1").value;
	if(val=="select")
		{
		alert("please select a folder first");
		return false;
		}
	else
		return true;
	}
	
	function checkup()
	{
		var val=document.getElementById("ch_user1").value;
		var val1=document.getElementById("fdup").value;
		if(val=="select")
			{
			alert("please select a folder first");
			return false;
			}
		else if(val1=="")
			{
			alert("please select a file first");
			return false;
			}
		else
			return true;
	}
	</script>        
</head>
<body bgcolor="#99ccff">
<div id="header">
        <center> <h1>
        <img src="smartvault_banner.jpg" style="width: 728px; height: 90px" /></h1></center>
            <table  border="1" style="width: 100%;height:10%">
                
                <tr>
                    <td><center style="height: 24px">Welcome <%= session.getAttribute( "user" ) %> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    <a href="/logoutServlet">Logout</a></center> </td>
                </tr>
                <tr>
                    <td><center><a href="changepwd.jsp">settings</a>&nbsp;&nbsp;|
                    &nbsp;&nbsp;<a href="faq.jsp">FAQ</a>&nbsp;&nbsp;
                    |&nbsp;&nbsp;<a href="contact.jsp">Contact Us</a>&nbsp;&nbsp;|
                    <a href="about.jsp">About</a></center></td>
                </tr>
            </table>
        </div>
        
         <hr style="height: 6px; background-color: #000000;" /><br>
         <form name="home" method="post" action="databaseopServlet">
         <center>Create New Folder<br> 
         Folder Name :<input type="text" name="folder_ip"/>&nbsp;
         <input type="submit" value="create" name="button"/>&nbsp;
          </center>
         </form>
         
         <form name="home1" method="post" action="uploadServlet" enctype="multipart/form-data">
         <center>
         <select id="ch_user1" name="combo1">
            <option value="select">Select folder</option>
        </select>
        <input type="submit" value="open" name="button" id="btt1" onclick="return checkdd()"/>
          <input type="submit" value="upload" name="button" onclick="return checkup()"/>
          <input type="file" name="folder_up" id="fdup">
          </center>
        </form>
       
       <form method="post" action="downloadServlet">
    <div id="main">
    <table id="foldertable" border="1" style="width:100%;height: 441px">
    <tr>
    <td style="width: 161px;" valign="top">
    <h4>Folders :-</h4>
     <ul id="folder_list"></ul>
    </td>
    <td valign="top">
    <h4>Files in <%=session.getAttribute("fold_name")%> :-</h4>
      <div id="showfiles">
      </div>
    </td>
    </tr>
    <tr>
    </tr>
    </table>
    </div>
    
    <div id="bottom">
    <center><h5>designed by:Paresh Vengurlekar & Pooja Kathkar</h5></center>
    </div>
    </form>    
</body>
</html>