<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.code}" />
<%@ include file="../common/head.jspf"%>

<section class="main-box-1">
	<div class="list-box">
		<div class="list-article-box">
			<div class="info">
				<div>${articlesCount}개</div>
				<div>
					<form action="../article/list">
						<input type="hidden" name="boardId" value="${boardId}"/> 
						<select data-value="${param.searchKeywordTypeCode}" name="searchKeywordTypeCode">
							<option value="title" selected>제목</option>
							<option value="body">내용</option>
							<option value="title,body">제목 + 내용</option>
						</select>
						<input type="text" name="searchKeyword" value="${param.searchKeyword}"/>
						<button class="searchBtn">검색</button>
					</form>
				</div>
			</div>
			<table>
				<colgroup>
					<col width="" />
					<col width="" />
					<col width="" />
					<col width="" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>날짜</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles}">
						<tr>
							<td>${article.id }</td>
							<td>${article.regDate.substring(2, 16) }</td>
							<td><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a></td>
							<td>${article.extra__writer }</td>
							<td>${article.hitCount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="list-page-box">
			<c:set var="baseUri" value="?boardId=${boardId}"/>
			<c:set var="baseUri" value="${baseUri}&searchKeywordTypeCode=${searchKeywordTypeCode}"/>
			<c:set var="baseUri" value="${baseUri}&searchKeyword=${searchKeyword}"/>
		
			<c:set var="pageRange" value="5" />
			<c:set var="start" value="${page - pageRange < 1 ? 1 : page - pageRange}" />
			<c:set var="end"
				value="${page + pageRange > totalPage ? totalPage : page + pageRange}" />

			<c:if test="${page > 1}">
				<a href="${baseUri}&page=1"> << </a>
			</c:if>
			<c:forEach begin="${start}" end="${end}" var="i">
				<a class="${page == i ? 'btn-active' : ''}"
					href="${baseUri}&page=${i}">${i}</a>
			</c:forEach>
			<c:if test="${page < totalPage}">
				<a href="${baseUri}&page=${totalPage}"> >> </a>
			</c:if>
		</div>
	</div>
</section>
<%@ include file="../common/foot.jspf"%>