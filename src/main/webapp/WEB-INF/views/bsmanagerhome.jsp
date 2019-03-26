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
		<script src="js/jquery-3.3.1.slim.min.js"></script>
		<script src="js/bootstrap.min.js"></script>    
	    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
	    <link rel="stylesheet" href="css/styles.css" type="text/css"   />    
	</head>

	<body>
		
		 
		 	<jsp:include page="header.jsp"/>	
		 	
		 	<div class ="container">	 	
		 	
		 		<div class="row">
		 	  	
		 	  		<div class="col-md-2">
			  			<jsp:include page="sidebar.jsp"/>
			  		</div>
			  	  	
			  	  	<div class="col-md-10">
				  		
				  			<jsp:include page="message.jsp"/>
				  		
					  		<div class="container">
					  			
					  			<p>Book And Subject Manager allows you to perform following actions.</p>
	  				
					  				<ul>
					  					<li>Add a Book - User can add a New Book</li>
					  					<li>Search For a Book - User can search for an existing Book</li>
					  					<li>Delete a Book - User can Delete an existing Book</li>
					  					<li>Add a Subject - User can add a New Subject</li>
					  					<li>Search For a Subject - User can search for an existing Subject</li>
					  					<li>Delete a Subject - User can Delete an existing Subject</li>
					  				</ul>
	  				
	  						<p> Please click on corresponding links listed on left side of the page. </p>			  							  			
					  		
					  	</div>
				  	
				  	</div>
				  		
				  </div>
				  		 	
		 	</div>
		 	
		 	<jsp:include page="footer.jsp"/>
	
	
	</body>
	
	
</html>