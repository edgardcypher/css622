package manageBook;

import java.time.LocalDateTime;

public class BorrewedBook extends BookItem {
	  private LocalDateTime borrowedDate;
	  private LocalDateTime dueDate;
	  private String liberianId; // liberian id which issue the book
	  private String memberId; // the memeber id which issue the book

}
