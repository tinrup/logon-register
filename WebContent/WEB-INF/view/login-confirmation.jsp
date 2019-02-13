<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<title>User Login </title>
</head>

<body>

Welcome ${customer.firstName}, to the first page of server starting program !

<br><br>

<i> :)</i>	

<br><br>

<p id="demo"></p>

<script>
var d = new Date();
document.getElementById("demo").innerHTML = d.toDateString();
</script>
<br><br>

  		<a href="/LogOnApp/"> <input type="button" value="home" /></a>
</body>

</html>










