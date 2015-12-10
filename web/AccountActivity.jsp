<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.lang.Object"%>
<%@page import="java.util.List"%>
<%@page import="toba.business.Account"%>
<%@page import="toba.business.Transaction"%>

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
    
    <%  String totalSavingBalance = (String)request.getAttribute("totalSavingBalance");
        String totalCheckingBalance = (String)request.getAttribute("totalCheckingBalance");
        
        List<Transaction> activity = (List)request.getAttribute("transBlob");
     %>
        <p>Savings Account Balance $<%out.print(totalSavingBalance);%></p>
        <p>Checking Account Balance $<%out.print(totalCheckingBalance);%></p>
        <br>
    
        <%  for ( Transaction aTrans : activity) {
                String outStr = "From  : " + aTrans.getFromType() + " " +
                                "To    : " + aTrans.getToType()   + " " +
                                "Amount: " + aTrans.getAmount().toString();
            %>
                <p><%out.print(outStr);%></p>
            <%}
        %>
</body>
</html>
<c:import url="/includes/footer.jsp" />