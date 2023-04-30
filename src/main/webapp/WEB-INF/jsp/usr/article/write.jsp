<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE WRITE" />
<%@ include file="../common/head.jspf"%>
<hr />

<section class="mt-8 text-xl">
	<div class="container mx-auto px-3">
		<div class="table-box-type-1">
			<form action="../article/doWrite" method="POST">
				<table border="1">
					<colgroup>
						<col width="200" />
					</colgroup>
					<tbody>
						<tr>
							<th>게시판 선택</th>
							<td>
							<select name="boardId">
								<option value="1">공지사항</option>
								<option value="2" selected>자유게시</option>
								<option value="3">질의응답</option>
							</select>
							</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input autocomplete="off" type="text" name="title" /></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea name="body" cols="30" rows="10"></textarea></td>
						</tr>
						<tr>
							<th></th>
							<td>
								<button type="submit">등록</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<button class="btn-text-link" type="button" onclick="history.back()">뒤로가기</button>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>