<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Todo's of ${name}</title>
</head>

<body>
	<h1>Your todos are</h1>
	<table>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done ?</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.desc}</td>
				<td>${todo.targetDate}</td>
				<td>${todo.done}</td>
			</tr>
			</c:forEach>
			
			
		</tbody>
	</table>
	

	<a href="/add-todo">Add a Todo</a>
</body>

</html>