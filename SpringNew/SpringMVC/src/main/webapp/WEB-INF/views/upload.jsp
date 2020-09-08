<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<title>Upload</title>
</head>
<body>
	<div>
		<p>請選擇檔案:</p>
	    <form id="uploadForm">
            <input name="file" id="file" type="file">
        </form>
        <p>
        	<button id="button">上傳</button>
        </p>
    </div>
</body>
<script>
	$("#button").on("click",function(){
		var file = $("#file").val();

        //檢測是否有選擇檔案
        if (file == "" || file == null) {
            alert("請選擇檔案");
            return;
        } 

        //檢測副檔名是否為.pdf
        var subname = file.substring(file.lastIndexOf('.'));
        if (subname == ".xls" || subname == ".xlsx") {
			var str = "";
        }else {
            alert("檔案不是Execl格式，請重新上傳");
            return;
        }
        
        var formData = new FormData($("#uploadForm")[0]);
        
        $.ajax({
            url: 'uploadFile',
            contentType: false,
            async: true,
            type: "POST",
            cache: false,
            processData: false,
            data: formData,
            error: function (jqXHR, textStatus, errorThrown) {
                alert("系統可能哪裡出了問題，上傳失敗，請重新開啟畫面再試");
            },
            success: function (response) {
                alert(response.message);
            }
        })
	})
</script>
</html>