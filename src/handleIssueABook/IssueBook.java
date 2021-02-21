package handleIssueABook;

import java.util.ArrayList;

import Accounts.Liberian;
import Accounts.Member;
import Constants.AccountStatus;
import manageBook.BookItem;

public class IssueBook {
	public static void main(String[] args) {
		BookItem bookToBorrow = new BookItem();// sharable book object 
		bookToBorrow.setBookId("book123");
		ArrayList<Thread> allLiberiansIssuingABook = new ArrayList<Thread>();
		Liberian liberian;
		for(int i = 1 ;i <= 10 ; i++) {
			liberian = new Liberian("lib"+i,"alpha"+i,"Jmagaret"+i,AccountStatus.Active);
			Member member = new Member("memb"+i,"margared"+i,"Jora"+i,AccountStatus.Active);
			liberian.setBookToIssue(bookToBorrow);
			liberian.setMemberWhoBorrow(member);
			allLiberiansIssuingABook.add(new Thread(liberian));
		}
		
		for (Thread thread: allLiberiansIssuingABook) {
			thread.start();// start the thread
		}

        for (Thread thread: allLiberiansIssuingABook) {
			try {
				thread.join();// make sure all threads are done before running the main thread
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
	}
}
