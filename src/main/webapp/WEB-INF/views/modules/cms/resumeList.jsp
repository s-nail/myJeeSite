<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>商品管理</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出商品数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action", "${ctx}/cms/export");
					$("#searchForm").submit();
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
		$("#btnImport").click(function() {
			$.jBox($("#importBox").html(), {
				title : "导入数据",
				buttons : {
					"关闭" : true
				},
				bottomText : "导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"
			});
		});
	});
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/cms/myItemsList");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>

	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/cms/import" method="post"
			enctype="multipart/form-data" class="form-search"
			style="padding-left: 20px; text-align: center;"
			onsubmit="loading('正在导入，请稍等...');">
			<br /> <input id="uploadFile" name="file" type="file"
				style="width: 330px" /><br /> <br /> <input id="btnImportSubmit"
				class="btn btn-primary" type="submit" value="   导    入   " /> <a
				href="${ctx}/cms/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/resume/list/">简历列表</a></li>
		<%-- <shiro:hasPermission name="cms:items:edit">
			<li><a href="${ctx}/cms/myItemForm">商品添加</a></li>
		</shiro:hasPermission> --%>
	</ul>

	<form:form id="searchForm" modelAttribute="resume"
		action="${ctx}/sys/user/list" method="post"
		class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
		<ul class="ul-form">
			<%-- <li><label>归属公司：</label><sys:treeselect id="itemName" name="itemsEntity.id" value="${itemsEntity.id}" labelName="itemName" labelValue="${itemsEntity.itemName}" 
				title="商品" url="/sys/office/treeData?type=1" cssClass="input-small" allowClear="true"/></li> --%>
			<%-- <li><label>商品名称：</label> <form:input path="itemName"
					htmlEscape="false" maxlength="50" class="input-medium" /></li> --%>
			<!-- <li class="clearfix"></li> -->
			<%-- 	<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			 --%>
			 
		<!-- 	<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" onclick="return page();" /> <input
				id="btnExport" class="btn btn-primary" type="button" value="导出" />
				<input id="btnImport" class="btn btn-primary" type="button"
				value="导入" /></li>
			<li class="clearfix"></li> -->
		</ul>
	</form:form>


	<sys:message content="${message}" />

	<table id="treeTable"
		class="table table-striped table-bordered table-condensed">
		<tbody id="treeTableList"></tbody>
	</table>

	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>电话</th>
				<th>email</th>
				<th>简历编号</th>
				<th>简历来源</th>
				<th>投递时间</th>
				<th>是否已读</th>
				<th>简历详情</th>
				<shiro:hasPermission name="cms:items:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td>${fn:trim(item.name)}</td>
					<td>${fn:trim(item.sex)}</td>
					<td>${fn:trim(item.age)}</td>
					<td>${fn:trim(item.phone)}</td>
					<td>${item.email}</td>
					<td>${item.resumeNo}</td>
					<td>${item.source}</td>
					<td>${item.sendTime}</td>
				    <td>${item.unread}</td> 
					<%-- <td>${item.resumeURL}</td> --%> 					
				    <td><a href="${item.resumeURL}">简历详情</a></td> 

					<shiro:hasPermission name="cms:items:edit">
						<td><a href="${ctx}/cms/myItemForm?id=${item.id}">修改</a> <a
							href="${ctx}/cms/delete?id=${item.id}"
							onclick="return confirmx('确认要删除该用户吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

</body>
</html>