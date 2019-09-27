<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細画面</title>
<link rel="stylesheet" href="./css/product.css">
<link rel="stylesheet" href="./css/error.css">
<link rel="stylesheet" href="./css/olive.css">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/table.css">
<link rel="stylesheet" href="./css/form.css">

</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<h1>商品詳細画面</h1>
		<s:if test="productInfoDTO != null">
			<div class="details-list">
				<div class="left">
					<img
						src='<s:property value="productInfoDTO.imageFilePath" />/<s:property value="productInfoDTO.imageFileName" />' /><br>
				</div>
				<s:form action="AddCartAction">

					<div class="right">
						<table class="vertical-list-mini">
							<tr>
								<th><s:label value="商品名" /></th>
								<td><s:property value="productInfoDTO.productName" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品名ふりがな" /></th>
								<td><s:property value="productInfoDTO.productNameKana" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="値段" /></th>
								<td><s:property value="productInfoDTO.price" />円</td>
							</tr>
							<tr>
								<th scope="row"><s:label value="購入個数" /></th>
								<td><s:select name="productCount"
										list="%{productCountList}" />個</td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売会社名" /></th>
								<td><s:property value="productInfoDTO.releaseCompany" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売年月日" /></th>
								<td><s:property value="productInfoDTO.releaseDate" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品詳細情報" /></th>
								<td><s:property value="productInfoDTO.productDescription" /></td>
							</tr>
						</table>
						<br>
						<s:hidden name="productId" value="%{productInfoDTO.productId}" />
						<div class="cart_button">
							<s:submit class="button" value="カートに追加" />
						</div>
					</div>
				</s:form>
			</div>

			<s:if
				test="relatedProductList != null && relatedProductList.size()>0">
				<div class="related_list">
					<s:iterator value="relatedProductList">
						<div class="related_box">
							<a
								href='<s:url action="ProductDetailsAction">
     <s:param name="productId" value="%{productId}" />
     </s:url>'><img
								src='<s:property value="imageFilePath" />/<s:property value="imageFileName"/>'
								class="relared_image" /> <s:property value="productName" /> </a>
						</div>
					</s:iterator>
				</div>
			</s:if>
		</s:if>
		<s:else>
			<div class="info">商品の詳細情報がありません。</div>
		</s:else>
	</div>
</body>
</html>