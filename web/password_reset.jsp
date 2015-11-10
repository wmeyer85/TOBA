<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />


    <div>Please enter new Username and Password Below:</div>
    <p><i>${message}</i></p>
        <form action="success.html" method="post">
            Username: <input type="text" name="username" value="${user.userName}" required>
            Password: <input type="password" name="password" value="${user.password}" required>
            <input type="submit" value="Submit">
            <a href="account_activity.html"></a>
        </form>