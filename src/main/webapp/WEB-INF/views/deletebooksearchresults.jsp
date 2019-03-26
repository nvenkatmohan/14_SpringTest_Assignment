<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${books != null && !empty books}">

	<table class="table table-bordered">
		
		<thead>
			<tr>
				<th>Book Id</th>
				<th>Title</th>
				<th>Price</th>
				<th>Volume</th>
				<th>Publish Date</th>
				<th>Delete</th>
			</tr>
		</thead>
		
		<c:forEach var="book" items="${books}">
		
			<tr>
				<td>${book.bookId}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>${book.volume}</td>
				<td>${book.publishDate}</td>
				<td>
					<a href="${pageContext.request.contextPath}/books/deletebook/${book.bookId}" 
								class= "btn btn-primary">Delete Book</a>					
				</td>
			</tr>
		
		</c:forEach>		
		
	</table>
	


</c:if>