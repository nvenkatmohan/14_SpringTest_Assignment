<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Book And Subject Manager</title>
		<script src="<c:url value="/js/jquery-3.3.1.slim.min.js"/>" ></script>
		<script src="<c:url value="/js/bootstrap.min.js"/>" > </script>    
	    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>" type="text/css" />
	    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css"/>    
	</head>

	<body>
		 
		 	<jsp:include page="header.jsp"/>	
		 	
		 	<div class ="container">	 	
		 	
		 		<div class="row">
		 	  	
		 	  		<div class="col-md-2">
			  			<jsp:include page="sidebar.jsp"/>
			  		</div>
			  	  	
			  	  	<div class="col-md-10">			  			
				  		
					  		<div class="form-container">
					  			
					  			<h2>Search a Book</h2>
					  			
					  			<br/>
					  			
					  			<jsp:include page="message.jsp"/>
					  			
					  			<form:form action="retrievebook" modelAttribute="criteria" class="form-inline">
					  				
					  				<div class="form-group">
							        
							        	<label class="control-label" for="searchBy">Search By &nbsp;</label>
							                
						            	<form:select path="searchBy" id="searchBy" class="form-control">
						                	<form:option value="bookId">Book Id</form:option>
						                    <form:option value="bookTitle">Book Title</form:option>
						                </form:select>
							               
							        </div>
							        
							        <span>&nbsp;</span>
							           
							        <div class="form-group">     
							        	<form:input type="text" path="inputValue" id="inputValue" class="form-control"/>
							        </div>
							        
							        <span>&nbsp;</span><span>&nbsp;</span>
							            
							        <div class="form-group"> 
							       		<input type="submit" value="Search" class="btn btn-primary">			            		  				
					  				</div>
					  			
					  			</form:form>
					  			
					  			<br/>
					  			
					  			<jsp:include page="searchbookresults.jsp"/>	  			
					  		
					  	</div>
				  	
				  	</div>
				  		
				  </div>
				  		 	
		 	</div>
		 	
		 	<jsp:include page="footer.jsp"/>	
	
	</body>
	
	
</html>