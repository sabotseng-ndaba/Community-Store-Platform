package za.ac.cput.communitystoreplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import za.ac.cput.communitystoreplatform.domain.User;
import za.ac.cput.communitystoreplatform.service.IUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp() {

        user = new User.Builder()
                .setUserId("U001")
                .setFirstName("Lindo")
                .setLastName("Nanto")
                .build();
    }

    @Test
    void create() throws Exception {

        when(userService.create(any(User.class)))
                .thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("U001"))
                .andExpect(jsonPath("$.firstName").value("Lindo"))
                .andExpect(jsonPath("$.lastName").value("Nanto"));
    }

    @Test
    void getById() throws Exception {

        when(userService.read("U001"))
                .thenReturn(user);

        mockMvc.perform(get("/users/U001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("U001"))
                .andExpect(jsonPath("$.firstName").value("Lindo"));
    }

    @Test
    void getAll() throws Exception {

        when(userService.getAll())
                .thenReturn(java.util.List.of(user));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("U001"))
                .andExpect(jsonPath("$[0].firstName").value("Lindo"));
    }

    @Test
    void update() throws Exception {

        when(userService.update(any(User.class)))
                .thenReturn(user);

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("U001"));
    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(delete("/users/U001"))
                .andExpect(status().isOk());
    }

    @Test
    void findByFirstName() throws Exception {

        when(userService.findByFirstName("Lindo"))
                .thenReturn(java.util.List.of(user));

        mockMvc.perform(get("/users/firstName/Lindo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Lindo"));
    }

    @Test
    void findByLastName() throws Exception {

        when(userService.findByLastName("Nanto"))
                .thenReturn(java.util.List.of(user));

        mockMvc.perform(get("/users/lastName/Nanto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Nanto"));
    }
}