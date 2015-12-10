package Login;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toba.business.Account;
import toba.business.Transaction;

import toba.business.User;
import toba.data.AccountDB;
import toba.data.TransactionDB;
import toba.util.AccountType;


public class TransferServlet extends HttpServlet {
	
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/transfer.jsp";

        
	 HttpSession session = request.getSession();
         User user = (User) session.getAttribute("user");
         Account savingLookup = (Account)session.getAttribute("savingsLookup");
         Account checkingLookup = (Account)session.getAttribute("checkingLookup");
        
         /* get posted data*/
        String fromAccountType = request.getParameter("fromAccount");
        String toAccountType = request.getParameter("toAccount");
        String transferAmount = request.getParameter("transferAmount");
        
        // Remove the old recs (replace with new ones)
        AccountDB.delete( savingLookup);
        AccountDB.delete( checkingLookup);
        
        try {
            Long trAmt = Long.valueOf( transferAmount);
            BigDecimal transfer = new BigDecimal( trAmt);
            AccountType fromType = (fromAccountType.equals("saving") ? AccountType.SAVING : AccountType.CHECKING);
            AccountType toType = (toAccountType.equals("saving") ? AccountType.SAVING : AccountType.CHECKING);
        
            if ( fromType == AccountType.SAVING) {
                savingLookup.debit( transfer);
            }
            else if ( fromType == AccountType.CHECKING) {
                checkingLookup.debit( transfer);
            }
            
            if ( toType == AccountType.SAVING) {
                savingLookup.credit( transfer);
            }
            else if ( toType == AccountType.CHECKING) {
                checkingLookup.credit( transfer);
            }

            /* execute transfer */
            AccountDB.update( savingLookup);
            AccountDB.update( checkingLookup);
            
            // Build the Transaction
            Transaction newTrans = new Transaction( user, fromType, toType, transfer);
            TransactionDB.update( newTrans);
            
        } catch (Exception e) {}
        
        request.setAttribute( "savingLookup", savingLookup);
        request.setAttribute( "checkingLookup", checkingLookup);
        
        getServletContext().getRequestDispatcher(url).forward(request, response);

        }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
