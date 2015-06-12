<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<a href="http://localhost:8080/esapi/protect.html">Attack</a>
<br>
<a href="http://localhost:8080/esapi/protect.html?<csrf:token uri="protect.html"/>">Protect</a>

<br/>
<%
    out.print(request.getParameter("name"));
%>
</body>
</html>