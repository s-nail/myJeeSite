<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量导入Word简历</title>
</head>
<body>

	<form name="myform" action="${ctx}/cms/resume/save" method="POST"
		enctype="multipart/form-data">
		Your name: <br> <input type="text" name="name" size="15"><br>
		File:<br> <input type="file" name="files"><br>
		File:<br> <input type="file" name="files"><br>
		File:<br> <input type="file" name="files"><br> <br>
		<input type="submit" name="submit" value="Commit">
	</form>

</body>
</html>