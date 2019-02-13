<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>login Form</title>

<style>
.error {
	color: green
}
</style>
</head>
<a href="/LogOnApp/"> <input type="button" value="home" /></a>
<body>
	<br>
	<br>

	<form:form action="processLoginForm" modelAttribute="loginCustomer">

		<form:input path="email" placeholder="email" />
		<form:errors path="email" cssClass="error" />

		<br>
		<br>
		<form:input type="password" path="password" id="myInput"
			placeholder="password" />
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



		<input type="button" onclick="history.back();" value="back">
	</form:form>



</body>

</html>










