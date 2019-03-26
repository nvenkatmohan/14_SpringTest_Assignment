<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${messages != null && !empty messages}">
 	<div class="alert alert-danger alert-dismissible">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<c:forEach var="errMsg" items="${messages}">
    		<strong>Error!</strong> ${errMsg}
    	</c:forEach>
 	</div>
</c:if>
<c:if test="${infoMsg != null && infoMsg != ''}">
	<div class="alert alert-info alert-dismissible">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<strong>INFO:</strong> ${infoMsg}    	
 	</div>
</c:if>
<c:if test="${successMsg != null && successMsg != ''}">
	<div class="alert alert-success alert-dismissible">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<strong>Success!</strong> ${successMsg}    	
 	</div>
</c:if>

