<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${info}</h1>
</body>
</html> --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/myItemsList/">商品列表</a></li>
		<li class="active"><a href="${ctx}/cms/myItemForm?id=${itemsEntity.id}">商品<shiro:hasPermission name="cms:items:edit">${not empty itemsEntity.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cms:items:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="itemsEntity" action="${ctx}/cms/save" method="post" class="form-horizontal">
	    <form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品名称:</label>
			<div class="controls">
				<form:input path="itemName"  htmlEscape="false" maxlength="50" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品价格:</label>
			<div class="controls">
				<form:input path="price"  htmlEscape="false" maxlength="50" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">库存量:</label>
			<div class="controls">
				<form:input path="inventory"  htmlEscape="false" maxlength="50" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<c:if test="${not empty itemsEntity.id}">
			<div class="control-group">
				<label class="control-label">创建时间:</label>
				<div class="controls">
					<label class="lbl"><fmt:formatDate
							value="${itemsEntity.createDate}" type="both" dateStyle="full" /></label>
				</div>
			</div>
			
		</c:if>

		<div class="form-actions">
			<shiro:hasPermission name="cms:items:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	

	</form:form>

</body>
</html>