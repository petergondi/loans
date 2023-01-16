package com.project.loans.Web;

import com.project.loans.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerTest extends IntegrationTest {
    @Test
    void listContact() throws Exception {
        mvc.perform((get("/contacts", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[:1].fullName", hasItem("Peter Test")));
    }

    @Test
    void getContactDetails() throws Exception {
        mvc.perform((get("/contacts/{id}", 402)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.msisdn", equalTo("07586763456")));
    }

    @Test
    void getNonExistingContact() throws Exception {
        mvc.perform((get("/contacts/{id}", 1, 6666)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createContact() throws Exception {
        mvc.perform(
                        post("/contacts/create", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"fullName\":\"John Doe\",\"msisdn\":\"07646375\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id", greaterThan(0)));
    }
    @Test
    void contactMissing() throws Exception {
        mvc.perform(
                        post("/contacts/create", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }




}