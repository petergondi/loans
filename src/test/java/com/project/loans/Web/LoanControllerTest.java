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
        mvc.perform((get("/loan/all", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[:1].contactId", hasItem(402)));
    }

    @Test
    void getLoanDetails() throws Exception {
        mvc.perform((get("/loan/{id}", 202)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.contactId", equalTo(452)));
    }

    @Test
    void getNonExistingLoan() throws Exception {
        mvc.perform((get("/loan/{id}", 1, 6666)))
                .andExpect(status().isNotFound());
    }
    @Test
    void disburseLoan() throws Exception {
        mvc.perform(
                        post("/loan/disburse", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"contactId\":\"402\",\"amount\":\"5000\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.contactId", greaterThan(0)));
    }

    @Test
    void repayLoan() throws Exception {
        mvc.perform(
                        post("/loan/repay", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"loanId\":\"202\",\"contactId\":\"452\",\"amount\":\"2500\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.contactId", greaterThan(0)));
    }
    @Test
    void requestLoanWithPendingLoan() throws Exception {
        mvc.perform(
                        post("/loan/disburse", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"contactId\":\"102\",\"amount\":\"5000\"}")
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }
    @Test
    void repayNonExistingLoan() throws Exception {
        mvc.perform(
                        post("/loan/repay", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"loanId\":\"400\",\"contactId\":\"452\",\"amount\":\"2500\"}")
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }
    @Test
    void loanIdMissing() throws Exception {
        mvc.perform(
                        post("/loan/disburse", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }




}
