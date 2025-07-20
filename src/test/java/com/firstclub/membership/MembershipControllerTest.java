package com.firstclub.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstclub.membership.dto.CreateUserRequest;
import com.firstclub.membership.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MembershipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndGetAndUpdateAndDeleteUser() throws Exception {
        // Create user
        CreateUserRequest createRequest = new CreateUserRequest();
        createRequest.setName("Test User");
        createRequest.setEmail("testuser@example.com");
        MvcResult createResult = mockMvc.perform(post("/membership/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andReturn();
        UserDTO createdUser = objectMapper.readValue(createResult.getResponse().getContentAsString(), UserDTO.class);
        assertThat(createdUser.getId()).isNotNull();
        assertThat(createdUser.getName()).isEqualTo("Test User");
        assertThat(createdUser.getEmail()).isEqualTo("testuser@example.com");

        // Get all users
        mockMvc.perform(get("/membership/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // Get user by ID
        mockMvc.perform(get("/membership/users/" + createdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"));

        // Update user
        UserDTO updateRequest = new UserDTO();
        updateRequest.setName("Updated User");
        updateRequest.setEmail("updateduser@example.com");
        mockMvc.perform(put("/membership/users/" + createdUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated User"));

        // Delete user
        mockMvc.perform(delete("/membership/users/" + createdUser.getId()))
                .andExpect(status().isNoContent());

        // Confirm user is deleted
        mockMvc.perform(get("/membership/users/" + createdUser.getId()))
                .andExpect(status().isNotFound());
    }
} 