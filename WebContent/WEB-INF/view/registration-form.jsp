<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>registration form</title>

<style>
.error {
	color: green
}
</style>
</head>


<body>

	<br>
	<br>

	<form:form action="processForm" modelAttribute="customer">
	
	<form:input path="firstName" placeholder="name"/>
		<form:errors path="firstName" cssClass="error" />
		<br>
		<br>
		
	<form:input path="lastName" placeholder="last name" />
		<form:errors path="lastName" cssClass="error" />

		<br>
		<br>
	<form:input path="email" placeholder="email"/>
		<form:errors path="email" cssClass="error" />
		<br>
		<br>
		
	<form:input type="password" path="password" id="myInput" placeholder="password"/>
			<form:errors path="password" cssClass="error" />
		<br>
		<br>
		<input type="checkbox" onclick="myFunction()">Show Password
<script>
	function myFunction() {
		var x = document.getElementById("myInput");
		if (x.type === "password") {
			x.type = "text";
		} else {
			x.type = "password";
		}
	}
</script>
	
		<br>
		<br>


		<input type="submit" value="Submit" />



		<input type="button" onclick="history.back();" value="Back">
	</form:form>

	<a href="/LogOnApp/"> <input type="button" value="home" /></a>


</body>

</html>










