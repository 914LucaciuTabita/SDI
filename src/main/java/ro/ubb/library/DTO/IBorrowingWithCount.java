package ro.ubb.library.DTO;

import ro.ubb.library.Model.Borrowing;

public interface IBorrowingWithCount {
    Borrowing getBorrowing();
    Integer getCount();
}
