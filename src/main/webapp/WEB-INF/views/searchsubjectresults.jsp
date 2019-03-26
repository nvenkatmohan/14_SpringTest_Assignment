<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${subjects != null && !empty subjects}">

	<table class="table table-bordered">
		
		<thead>
			<tr>
				<th>Subject Id</th>
				<th>Subject Title</th>
				<th>Duration (In Hrs)</th>
				<th>Books Referenced</th>
			</tr>
		</thead>
		
		<c:forEach var="subject" items="${subjects}">
		
			<tr>
				<td>${subject.subjectId}</td>
				<td>${subject.subjectTitle}</td>
				<td>${subject.durationHours}</td>
				<td>${subject.bookTitles}</td>
			</tr>
		
		</c:forEach>		
		
	</table>

</c:if>