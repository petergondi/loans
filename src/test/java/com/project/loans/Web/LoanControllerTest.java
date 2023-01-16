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

public class LoanControllerTest extends IntegrationTest {
    @Test
    void listLoans() throws Exception {
        mvc.perform((get("/loans", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].contact_id", hasItem("402")));
    }

    @Test
    void getLoanDetails() throws Exception {
        mvc.perform((get("/loan/{id}", 1, 202)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contact_id", equalTo("452")));
    }

    @Test
    void getNonExistingLoan() throws Exception {
        mvc.perform((get("/loan/{id}", 1, 6666)))
                .andExpect(status().isNotFound());
    }
    @Test
    void disburseLoan() throws Exception {
        mvc.perform(
                        post("/loan/create", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"contactId\":\"3\",\"amount\":\"5000\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void repayLoan() throws Exception {
        mvc.perform(
                        post("/loan/repay", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"loanId\":\"1\",\"contactId\":\"230\",\"amount\":\"2500\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }
    @Test
    void loanIdMissing() throws Exception {
        mvc.perform(
                        post("/loan/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }
    @Test
    void contentMissing() throws Exception {
        mvc.perform(
                        post("/disburse")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\":\"something missing\"}")
                )
                .andExpect(status().isBadRequest());
    }



}
