<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과제 부여</title>
<link href="../../../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../../../resources/css/style.css" rel="stylesheet">
<script src="../../../resources/js/jquery.min.js"></script>
<script type="text/javascript"
	src="../../../resources/js/schedule/schedule.js"></script>
</head>
<body>
	<input id="taskId" name="taskId" type="hidden" value="">
	<a href="assignmentList.do">과제리스트 돌아가기</a>
	<h3>과제 부여</h3>

	<br>
	<form action="${pageContext.request.contextPath}/assignment/create.do" method="post">
		<table class="table">
			<colgroup>
				<col width="150">
				<col width="*">

			</colgroup>
			<tr>
				<th>제목</th>
				<td><input id="title" name="title"
					class="form-control" type="text" value="" placeholder="제목을 입력하세요."></td>
			</tr>
			<tr style="horizontal-align: left;">
				<th>제출자</th>
				<td>
					<ul>
						<li style="float: left; width: 70px;"><input type="checkbox"
							name="memberId" class="form-control"
							style="width: 20px; margin: 0px;" value="" />이은채</li>
						<li style="float: left; width: 70px;"><input type="checkbox"
							name="memberId" class="form-control"
							style="width: 20px; margin: 0px;" value="" />진민정</li>
						<li style="float: left; width: 70px;"><input type="checkbox"
							name="memberId" class="form-control"
							style="width: 20px; margin: 0px;" value="" />현대경</li>
						<li style="float: left; width: 70px;"><input type="checkbox"
							name="memberId" class="form-control"
							style="width: 20px; margin: 0px;" value="" />김수호</li>
						<li style="float: left; width: 70px;"><input type="checkbox"
							name="memberId" class="form-control"
							style="width: 20px; margin: 0px;" value="" />윤영민</li>
					</ul>
				</td>
			</tr>
			<tr>
				<th>제출 기한</th>
				<td>
					<input type="date" id="deadlineDay" name="deadlineDay"> 
					<input type="time" id="deadlineHour" name="deadlineHour"> 
					
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea id="contents" name="contents"
						class="form-control" rows="7" placeholder="부여할 과제의 내용을 입력하세요."></textarea></td>
			</tr>
		</table>
		<br>
		<div align="center">
			<a href="assignmentList.do"><input class="btn" type="reset" value="취소"></a> 
			<input class="btn btn-success" type="submit" value="저장"></a>
		</div>
	</form>
	<br>
</body>
</html>
