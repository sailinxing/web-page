<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	/* function deletePro(pid) {
		var con = confirm("确定要删除我吗？");
		if (con) {
			location.href = "${pageContext.request.contextPath }/adminDeleteProduct?pid="
					+ pid;
		}
	} */
	function addProduct() {
		location.href = "${pageContext.request.contextPath }/adminAddProduct";
	}
	window.onload = function() {
		var cid = "${condition.cid}"
			var sc = document.getElementById("sc");
			var options = sc.getElementsByTagName("option");
			for (var i = 0; i < options.length; i++) {
				if (cid == options[i].value) {
					options[i].selected = true;
				}
			}
			var is_hot = "${condition.is_hot}"
			var si = document.getElementById("si");
			var options1 = si.getElementsByTagName("option");
			for (var i = 0; i < options1.length; i++) {
				if (is_hot == options1[i].value) {
					options1[i].selected = true;
				}
			}
		/* allBox.onchange=function(){
			if(allBox.checked==true){
				var boxes = document.getElementsByName("deleteBox");
				for(var i=0;i<boxes.length;i++){
					boxes[i].checked = true;
				}
			}else{
				var boxes = document.getElementsByName("deleteBox");
				for(var i=0;i<boxes.length;i++){
					boxes[i].checked = false;
				}
			} */
		var allBox=document.getElementsByName("allBox");
		var boxes=document.getElementsByName("deleteBox");
		allBox[0].onchange=function(){
			if(allBox[0].checked){
				for(var i=0;i<boxes.length;i++){
					boxes[i].checked=true;
				}
			}
			else{
					for(var i=0;i<boxes.length;i++){
						boxes[i].checked=false;
				}					
			}
		}
		for(var i=0;i<boxes.length;i++){
			boxes[i].onchange=function(){
			var res=true;
			for(var j=0;j<boxes.length;j++){
					if(boxes[j].checked!=true){
						res=false;
					}
				}
			if(res){
				allBox[0].checked=true;
				}
			else{
				allBox[0].checked=false;
				}
			}			
		}
		/* function deleteBox(){
			var result=true;
			var boxes = document.getElementsByName("deleteBox");
			for(var i=0;i<boxes.length;i++){
				if(boxes[i].checked=false){
					result=false;
					break;
				}
			}
			if(result){
				allBox.checked = true;
			}else{
				allBox.checked = false;
			}
		} */
	}
</script>
</head>
<body>
	<table width="100%" cellpadding="0px" cellspacing="0px" border="1px">
		<caption>商品列表</caption>
		<tr>
			<td align="left" colspan="7" width="100%">
				<form
					action="${pageContext.request.contextPath }/adminSearchProductList"
					method="post">
					商品名称<input type="text" name="pname" value="${condition.pname }" />
					&nbsp; &nbsp; &nbsp; 是否热门<select name="is_hot" id="si">
						<option value="">--请选择--</option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>&nbsp; &nbsp; &nbsp; 所属分类 <select name="cid" id="sc">
						<option value="">--请选择--</option>
						<c:forEach items="${categoryList}" var="cat">
							<option value="${cat.cid }">${cat.cname }</option>
						</c:forEach>
					</select>&nbsp; &nbsp; &nbsp; <input type="submit" value="搜索" /> <a
						href="${pageContext.request.contextPath }/adminProductList">清除</a>
				</form>

			</td>
		</tr>
		<tr align="right">
			<td colspan="7" width="100%">
				<button onclick="addProduct()">添加商品</button>
			</td>
		</tr>
		<tr>
			<td align="center">序号</td>
			<td align="center">图片</td>
			<td align="center">名称</td>
			<td align="center">门店价格</td>
			<td align="center">是否热门</td>
			<td align="center">编辑</td>
			<td align="center">删除</td>
		</tr>
	<form action="${pageContext.request.contextPath }/adminDeleteAllProduct">
		<c:forEach items="${productList}" var="pro" varStatus="vs">
			<tr>
				<td align="center">${vs.count}</td>
				<td align="center"><img
					src="${pageContext.request.contextPath }/images/${pro.pimage }"
					width="40px" height="45px" /></td>
				<td align="center">${pro.pname}</td>
				<td align="center">${pro.shop_price}</td>
				<td align="center">${pro.is_hot==1?"是":"否"}</td>
				<td align="center"><a
					href="${pageContext.request.contextPath}/adminEditProduct?pid=${pro.pid }">编辑</a>
				</td>
				<%-- <td align="center"><a href="javascript:void(0)"
					onclick="deletePro('${pro.pid}')">删除</a></td> --%>
				<td align="center">删除<input type="checkbox" name="deleteBox"
					value="${pro.pid}" onclick="deleteBox()" /></td>
			</tr>
		</c:forEach>
		<tr align="right">
			<td colspan="7"><input id="allBox" type="checkBox" name="allBox" />
				全选/全不选 &nbsp; &nbsp; &nbsp; <input type="submit" value="删除" /></td>
		</tr>
	</form>
	</table>
</body>
</html>