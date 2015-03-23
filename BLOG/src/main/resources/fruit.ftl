<html>
<head>
	<title>Fruit Picker </title>
</head>
<body>
	<form action="/favourite_fruit" method="POST">
	<#list fruits as fruit>
		<p>
		  <input type = "radio" name="fruit" value="${fruit}">${fruit}</input>
		</p>
	</#list>
	<input type = "Submit" value="Submit" />
	</form>
</body>

</html>