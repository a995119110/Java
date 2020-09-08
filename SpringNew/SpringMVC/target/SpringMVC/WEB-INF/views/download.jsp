<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<title>Download</title>
</head>
<body>
	<div>
		<p>下載:</p>
	    <p>
        	<button id="button" onClick = "download();">下載</button>
        </p>
    </div>
</body>
<script>
		function download(){
			$.ajax({
				url:'downloadFile',
				type:"Post",
				success : function (response) {
					alert(response.message);			
					load();	
				}
			})
		}
</script>
</html>