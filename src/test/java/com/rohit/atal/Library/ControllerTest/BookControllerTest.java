package com.rohit.atal.Library.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rohit.atal.Library.Model.Book;
import com.rohit.atal.Library.controller.BookController;
import com.rohit.atal.Library.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    Book book1 = new Book(12, "python", "loverBabbar", true);
    Book book2 = new Book(13, "DSA", "Aman", true);

    @Before
    public void setUP() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getAllBooksTest() throws Exception {
        List<Book> Booklist = new ArrayList<>(Arrays.asList(book1, book2));
        Mockito.when(bookService.getAllBooks()).thenReturn(Booklist);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].bookName", is("DSA")));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        Mockito.when(bookService.getBookById(book1.getBookid())).thenReturn(Optional.ofNullable(book1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.bookName", is("python")));
    }

    @Test
    public void addBook() throws Exception {
        Book book = new Book(14, "data science", "S.R.DAS", true);

        Mockito.when(bookService.addBook(book)).thenReturn(book);

        String content = objectWriter.writeValueAsString(book);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.bookName", is("data science")));
    }

    @Test
    public void updatebookTest() throws Exception {
        Book updatedbook = new Book(12, "Machine Learning", "Dirk P. Kroese", true);

        Mockito.when(bookService.updatebook(12, updatedbook)).thenReturn(updatedbook);

        String updatedcontent = objectWriter.writeValueAsString(updatedbook);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/books/12")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedcontent);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.bookName", is("Machine Learning")));
    }

    @Test
    public void deleteBookTest() throws Exception {
        Mockito.when(bookService.deletebook(13)).thenReturn(book2);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/13")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
