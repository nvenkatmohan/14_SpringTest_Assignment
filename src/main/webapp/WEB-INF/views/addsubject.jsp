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
					  			
					  			<h2>Add a Subject</h2>
					  			
					  			<br/>
					  			
					  			<jsp:include page="message.jsp"/>
					  			
					  			<form:form action="savesubject" modelAttribute="subject" class="form-horizontal">
					  			
					  				<div class="row">
				            			<div class="form-group col-md-12">
							                <label class="col-md-3 control-label" for="subjectId">Subject Id</label>
							                <div class="col-md-7">
							                    <form:input type="text" path="subjectId" id="subjectId" class="form-control input-sm"/>
							                    <div class="has-error">
							                        <form:errors path="subjectId" class="help-inline"/>
							                    </div>
							                </div>
				            			</div>
				        			</div>
					  			
					  				
					  				 <div class="row">
				            			<div class="form-group col-md-12">
							                <label class="col-md-3 control-label" for="subjectTitle">Subject Title</label>
							                <div class="col-md-7">
							                    <form:input type="text" path="subjectTitle" id="subjectTitle" class="form-control input-sm"/>
							                    <div class="has-error">
							                        <form:errors path="subjectTitle" class="help-inline"/>
							                    </div>
							                </div>
				            			</div>
				        			</div>
				 
							        <div class="row">
							            <div class="form-group col-md-12">
							                <label class="col-md-3 control-label" for="durationHours">Duration (in Hrs)</label>
							                <div class="col-md-7">
							                    <form:input type="text" path="durationHours" id="durationHours" class="form-control input-sm"/>
							                    <div class="has-error">
							                        <form:errors path="durationHours" class="help-inline"/>
							                    </div>
							                </div>
							            </div>
							        </div>
							        
							        <div class="row">
							            <div class="form-group col-md-12">
							                <label class="col-md-3 control-label" for="bookTitles">Book Titles</label>
							                <div class="col-md-7">
							                    <form:textarea path="bookTitles" id="bookTitles" class="form-control input-sm"/>
							                    <div class="has-error">
							                        <form:errors path="bookTitles" class="help-inline"/>
							                    </div>
							                </div>
							            </div>
							        </div>
					  			
							        
							         <div class="row">
				            			<div class="form-actions">
				                			<input type="submit" value="Add Subject" class="btn btn-primary btn-sm">
				            			</div>
				        			</div>
					  			
					  			</form:form>				  			
					  		
					  	</div>
				  	
				  	</div>
				  		
				  </div>
				  		 	
		 	</div>
	
			<jsp:include page="footer.jsp"/>
	
	</body>
	
	
</html>