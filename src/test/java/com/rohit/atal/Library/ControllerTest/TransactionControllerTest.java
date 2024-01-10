package com.rohit.atal.Library.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.Model.Transaction;
import com.rohit.atal.Library.Model.User;
import com.rohit.atal.Library.controller.TransactionController;
import com.rohit.atal.Library.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    TransactionService transactionService;
    @InjectMocks
    TransactionController transactionController;

    Book book = new Book(12,"python","loverBabbar",true);
    User user = new User(21,"Rohit");
    Transaction transaction1 = new Transaction(31L, book,user, LocalDate.now(),LocalDate.now().plusWeeks(1));

    @Before
    public void setUP(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @Test
    public void getAllTransactionsTest() throws Exception {
        List<Transaction> Transactionlist = new ArrayList<>(Arrays.asList(transaction1));

        Mockito.when(transactionService.getAllTransactions()).thenReturn(Transactionlist);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].returndate",notNullValue()));
    }

    @Test
    public void getTransactionByIdTest() throws Exception {
        Mockito.when(transactionService.getTransactionById(31L)).thenReturn(Optional.ofNullable(transaction1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/transactions/31")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.returndate",notNullValue()));
    }

}
