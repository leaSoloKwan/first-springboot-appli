<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>

<head>
<title>First Web Application</title>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<form:form method="post" modelAttribute="todo">
			<form:hidden path="id"/><!-- sinon on a pas l'id pour le update pour bidding -->
			<fieldset class="form-group">
				<form:label path="desc">Description</form:label>
				<form:input path="desc" type="text"  class="form-control" required="required"/>
				<form:errors path="desc" cssClass="text-warning"/>
			</fieldset>
		
			<button type="submit" class="btn btn-success">Add</button>
		</form:form>
	</div>
	<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
   
</body>

</html>