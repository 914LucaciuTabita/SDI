package ro.ubb.library.Tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.ubb.library.Controller.BorrowingController;
import ro.ubb.library.DTO.BorrowingWithCountDTO;
import ro.ubb.library.Service.IBorrowingService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BorrowingControllerTest {

    @Mock
    private IBorrowingService borrowingService;

    @InjectMocks
    private BorrowingController borrowingController;

    @Test
    public void testFindLibrariesSortedByBorrowings() {
        // create sample data
        List<BorrowingWithCountDTO> borrowingDTOs = new ArrayList<>();
        borrowingDTOs.add(new BorrowingWithCountDTO(2L, 2L, "2022-02-01", "2022-03-01", 20));
        borrowingDTOs.add(new BorrowingWithCountDTO(1L, 1L, "2022-01-01", "2022-02-01", 10));
        borrowingDTOs.add(new BorrowingWithCountDTO(2L, 1L, "2022-02-01", "2022-03-01", 10));

        // mock service method
        when(borrowingService.findBorrowingsSortedByLibraryNumberOfBorrowings()).thenReturn(borrowingDTOs);

        // perform controller method
        ResponseEntity<List<BorrowingWithCountDTO>> response = borrowingController.findLibrariesSortedByBorrowings();

        // assert response
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // assert that the response.getBody() list is sorted by countBorrowings in descending order as borrowingDTOs
        assertEquals(borrowingDTOs, response.getBody());

        //asserts
        assertThat(response.getBody()).hasSize(3);
        assertThat(response.getBody().get(0).getCountBorrowings()).isEqualTo(borrowingDTOs.get(0).getCountBorrowings());
        assertThat(response.getBody().get(1).getCountBorrowings()).isEqualTo(borrowingDTOs.get(1).getCountBorrowings());
        assertThat(response.getBody().get(2).getCountBorrowings()).isEqualTo(borrowingDTOs.get(2).getCountBorrowings());

        // verify service method was called
        verify(borrowingService, times(1)).findBorrowingsSortedByLibraryNumberOfBorrowings();

    }
}
