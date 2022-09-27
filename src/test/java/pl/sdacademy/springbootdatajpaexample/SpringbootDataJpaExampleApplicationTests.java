package pl.sdacademy.springbootdatajpaexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sdacademy.springbootdatajpaexample.entity.User;
import pl.sdacademy.springbootdatajpaexample.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class SpringbootDataJpaExampleApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @BeforeAll
    public static void beforeAll(@Autowired UserService userService) {
        userService.create(new User("admin", "admin", "admin", "admin", "ROLE_ADMIN"));
    }

    @Test
    public void shouldGetUserByGivenId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic YWRtaW46YWRtaW4=")
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.equalTo("admin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.equalTo("admin")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", CoreMatchers.equalTo("admin")));
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new User("Jan", "Kowalski", "jkowalski", "user", "ROLE_USER")))
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.equalTo("Jan")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", CoreMatchers.equalTo("Kowalski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login", CoreMatchers.equalTo("jkowalski")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.role", CoreMatchers.equalTo("ROLE_USER")));
    }


    @Test
    public void shouldDeleteUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/2")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46YWRtaW4="))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Assertions.assertThrows(RuntimeException.class, () -> userService.find(2));
    }
}
