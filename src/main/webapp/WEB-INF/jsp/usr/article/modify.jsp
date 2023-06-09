<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Article MODIFY" />
<%@ include file="../common/head.jspf"%>
<hr />
<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<form action="../article/doModify" method="POST">
				<table border="1">
					<colgroup>
						<col width="200" />
						<col width="200" />
					</colgroup>
					<tbody>
						<input type="hidden" name="id" value="${article.id}" />
						<tr>
							<th>번호</th>
							<td>${article.id }</td>
						</tr>
						<tr>
							<th>작성 날짜</th>
							<td>${article.regDate }</td>
						</tr>
						<tr>
							<th>수정 날짜</th>
							<td>${article.updateDate }</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${article.extra__writer }</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" value="${article.title}" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="body" cols="30" rows="10">${article.body}</textarea></td>
						</tr>
						<tr>
							<th></th>
							<td><button type="submit">등록</button></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="btns">
			<button class="btn-text-link" type="button" onclick="history.back()">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>