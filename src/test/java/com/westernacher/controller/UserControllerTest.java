package com.westernacher.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.westernacher.dto.UserRequestDTO;
import com.westernacher.service.UserService;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    private static final LocalDate validDateOfBirth = LocalDate.parse("1989-09-11");
    private static final LocalDate futureDateOfBirth = LocalDate.parse("9999-12-31");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void createUserByEmail_whenNullName() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO(null, "Borisov", "b@em.com",
            validDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserByEmail_whenEmptyName() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO("", "Borisov", "b@em.com", validDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserByEmail_whenBlankName() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO(" ", "Borisov", "b@em.com",
            validDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserByEmail_whenNotBlankName() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO("Kosi", "Borisov", "b@em.com",
            validDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk());
    }

    @Test
    public void createUserByEmail_whenNotValidEmail() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO("Kosi", "Bo", "opa", validDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserByEmail_whenFutureDate() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO("Kosi", "Bo", "opa@abv.bg",
            futureDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserByEmail_whenFirstNameIsLong() throws Exception {
        //given
        UserRequestDTO requestDTO = new UserRequestDTO(
            "KosiKosiKosiKosiKosiKosiKosiKosiKosiKosiKosiKosiKos", "Bo", "opa@abv.bg",
            validDateOfBirth);

        //when
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isBadRequest());
    }
}
