 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
        
<link rel="stylesheet" type="text/css" href="Uploader.css">

    </head>
 
    <body> 
    <div>
            <h3> Choose File to Upload in Server </h3>
       
            <form action="upload"  method="post" enctype="multipart/form-data"  style="font-color:red">
                
                <input type="file"  class="file_upload" accept="image" name="file" />
           
              <input  class="gobutton" type="submit" value="upload" />
              
            </form>  
        </div>    
       
    </body>
</html>