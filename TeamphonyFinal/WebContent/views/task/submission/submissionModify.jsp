<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>제출 과제 수정</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/schedule/schedule.js"></script>
</head>
<body>
	<a href="assignmentList.do">과제리스트 돌아가기</a>
	<h3>부여 과제 수정</h3>

	<br>
	<form action="${pageContext.request.contextPath}/submission/revise.do" method="post" enctype="multipart/form-data">
		<input id="taskId" name="taskId" type="hidden" value="${task.taskId }">
		<table class="table">
			<colgroup>
				<col width="150">
				<col width="*">

			</colgroup>
			<tr>
				<th>제목</th>
				<td>
					<input id="title" name="title" class="form-control" type="text" value="" placeholder="${task.title }">
				</td>
			</tr>
			<tr style="horizontal-align: left;">
				<th>제출자</th>
				<td>
					<ul>
						<li style="float: left; width: 70px;">이은채</li>
						<li style="float: left; width: 70px;">진민정</li>
						<li style="float: left; width: 70px;">김수호</li>
						<li style="float: left; width: 70px;">윤영민</li>
						<li style="float: left; width: 70px;">미구현</li>
					</ul>
				</td>
			</tr>
			
			<tr>
				<th>내용</th>	
					<td>
						<textarea id="contents" name="contents" class="form-control" rows="7" placeholder="${task.contents }"></textarea>
					</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input style="width: 250px" ; type="file" name="attchFile" class="form-control">
					아직 미구현
					<input type="hidden" name="flag" value="1" />
				</td>
			</tr>
		</table>
		<br>
		<div align="center">
				<a href="assignmentList.do"><input class="btn" type="reset" value="취소"></a> 
				<input class="btn btn-success" type="submit" value="완료">
		</div>
	</form>
	<br>
</body>
</html>
