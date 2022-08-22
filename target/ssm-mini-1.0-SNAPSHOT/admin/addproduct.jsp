<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/addBook.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/ajaxfileupload.js"></script>
	</head>
    <script type="text/javascript">
		function fileChange(){

			$.ajaxFileUpload({
				url:"${pageContext.request.contextPath}/prod/ajaxImg.action",
				secureuri: false,
				fileElementId: 'pimage',
				dataType: 'json',
				success: function(obj)
				{
					$("#imgDiv").append(imgObj);
					var imgObj = $("<img>");
					imgObj.attr("src","${pageContext.request.contextPath}/static/image_big/"+obj.imgurl);
					imgObj.attr("width","100px");
					imgObj.attr("height","100px");
					$("#imgDiv").append(imgObj);

				},
				error: function (e)
				{
					alert(e.message);
				}
			});
		}
    </script>
	<body>

		<div id="addAll">
			<div id="nav">
				<p>Products Management>Add Products</p>
			</div>

			<div id="table">
				<form  id="myform" action="${pageContext.request.contextPath}/prod/save.action">
					<table>
						<tr>
							<td class="one">Name</td>
							<td><input type="text" name="pName" class="two"></td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span id="pnameerr"></span></td>
						</tr>
						<tr>
							<td class="one">Description</td>
							<td><input type="text" name="pContent" class="two"></td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span id="pcontenterr"></span></td>
						</tr>
						<tr>
							<td class="one">Price</td>
							<td><input type="number" name="pPrice" class="two"></td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span id="priceerr"></span></td>
						</tr>
						
						<tr>
							<td class="three">Picture</td>
                            <td> <br><div id="imgDiv" style="display:block; width: 40px; height: 50px;"></div><br><br><br><br>
                            <input type="file" id="pimage" name="pimage" onchange="fileChange()" >
                                <span id="imgName" ></span><br>
                            </td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>
						
						<tr>
							<td class="one">Inventory</td>
							<td><input type="number" name="pNumber" class="two"></td>
						</tr>

						<tr class="three">
							<td class="four"></td>
							<td><span id="numerr"></span></td>
						</tr>

						<tr>
							<td class="one">Category</td>
							<td>
								<select name="typeId" >
									<c:forEach items="${typeList}" var="type">
										<option value="${type.typeId}">${type.typeName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>

						<tr>
							<td>
								<input type="submit" value="SUBMIT" class="btn btn-success">
							</td>
							<td>
								<input type="reset" value="CANCEL" class="btn btn-default" onclick="myclose(${param.page})">
								<script type="text/javascript">
									function myclose(ispage) {
										window.location="${pageContext.request.contextPath}/prod/split.action?page="+ispage;
									}
								</script>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>