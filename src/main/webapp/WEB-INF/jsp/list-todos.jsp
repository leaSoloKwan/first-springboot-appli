<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<h1>Your todos are</h1>
	<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done ?</th>
				<th> Update </th>
				<th> Delete</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.desc}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${todo.targetDate}" /></td>
				<td>${todo.done}</td>
				<td><a type="button" class="btn btn-primary" href="/update-todo?id=${todo.id}">Update</a></td>
				<td><a type="button" class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
			</tr>
			</c:forEach>
			
			
		</tbody>
	</table>
	<div>
		<a class="btn btn-primary" href="/add-todo">Add a Todo</a>
	</div>
	
	
	</div>
<%@ include file="common/footer.jspf" %>