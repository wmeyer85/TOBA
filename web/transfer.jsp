<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Account.Account"%>

<c:import url="/includes/header.html" />
<body>

<nav>
    <ul>
        <li> <a href="index.html">Home</a></li>  
        <li><a href="login.html">Login</a></li>
        <li><a href="new_customer.html">New Customer</a></li>
        <li><a href="transaction">Account activity</a></li>
        <li><a href="balance">Transactions</a></li>
    </ul> 

</nav>

    <%  Account savingLookup = (Account)request.getAttribute("savingLookup");
        Account checkingLookup = (Account)request.getAttribute("checkingLookup");
        String message = (String) request.getAttribute("msg");
            if (message != null) { %>
    <h1 style="color:red; font-size:12px"><%out.print(message);%></h1>
    <%}%>

    <h1 style="text-font:12px">Transfer Complete!</h1>
    <p>Savings Account Balance $<%out.print(savingLookup.getBalance().toString());%></p>
    <p>Checking Account Balance $<%out.print(checkingLookup.getBalance().toString());%></p>

</body>
</html>
<c:import url="/includes/footer.jsp" />