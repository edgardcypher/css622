package manageBook;

import java.time.LocalDateTime;

// represent a book which is borrowed

public class BorrowedBook extends Book {
  private String bareCode;
  private LocalDateTime borrowedDate;
  private LocalDateTime dueDate;
  private String liberianId; // liberian id which issue the book
  private String memberId; // the memeber id which issue the book

	  public String getBareCode() {
		return bareCode;
	}
	public void setBareCode(String bareCode) {
		this.bareCode = bareCode;
	}
	public LocalDateTime getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(LocalDateTime borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public String getLiberianId() {
		return liberianId;
	}
	public void setLiberianId(String liberianId) {
		this.liberianId = liberianId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	  
  
}
