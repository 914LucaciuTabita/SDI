package ro.ubb.library.DTO;

import ro.ubb.library.Model.Author;

public interface IAuthorWithAVG {
    Author getAuthor();
    Double getAveragePages();
}
