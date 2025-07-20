package com.firstclub.membership;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstclub.membership.dto.*;
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
public class MembershipControllerFundamentalTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPlansAndTiersEndpoints() throws Exception {
        mockMvc.perform(get("/membership/plans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/membership/tiers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(get("/membership/tiers/1/benefits"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/membership/catalog"))
                .andExpect(status().isOk());
    }

    @Test
    void testSubscribeUpgradeDowngradeCancelRenewFlow() throws Exception {
        // Create user
        CreateUserRequest createRequest = new CreateUserRequest();
        createRequest.setName("Flow User");
        createRequest.setEmail("flowuser@example.com");
        MvcResult createResult = mockMvc.perform(post("/membership/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andReturn();
        UserDTO user = objectMapper.readValue(createResult.getResponse().getContentAsString(), UserDTO.class);

        // Get a plan (assume id=1 exists)
        MvcResult planResult = mockMvc.perform(get("/membership/plans"))
                .andExpect(status().isOk())
                .andReturn();
        PlanDTO[] plans = objectMapper.readValue(planResult.getResponse().getContentAsString(), PlanDTO[].class);
        Long planId = plans[0].getId();

        // Subscribe
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setUserId(user.getId());
        subscribeRequest.setPlanId(planId);
        subscribeRequest.setAutoRenew(true);
        subscribeRequest.setPaymentMethod("mock");
        MvcResult subscribeResult = mockMvc.perform(post("/membership/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subscribeRequest)))
                .andExpect(status().isOk())
                .andReturn();
        SubscribeResponse subscribeResponse = objectMapper.readValue(subscribeResult.getResponse().getContentAsString(), SubscribeResponse.class);
        assertThat(subscribeResponse.getStatus()).isEqualTo("active");

        // Upgrade (assume id=2 exists)
        if (plans.length > 1) {
            UpgradeRequest upgradeRequest = new UpgradeRequest();
            upgradeRequest.setUserId(user.getId());
            upgradeRequest.setTargetPlanId(plans[1].getId());
            MvcResult upgradeResult = mockMvc.perform(put("/membership/upgrade")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(upgradeRequest)))
                    .andExpect(status().isOk())
                    .andReturn();
            UpgradeResponse upgradeResponse = objectMapper.readValue(upgradeResult.getResponse().getContentAsString(), UpgradeResponse.class);
            assertThat(upgradeResponse.getStatus()).isEqualTo("UPGRADED");
        }

        // Downgrade (if more than 2 plans)
        if (plans.length > 2) {
            DowngradeRequest downgradeRequest = new DowngradeRequest();
            downgradeRequest.setUserId(user.getId());
            downgradeRequest.setTargetPlanId(plans[2].getId());
            MvcResult downgradeResult = mockMvc.perform(put("/membership/downgrade")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(downgradeRequest)))
                    .andExpect(status().isOk())
                    .andReturn();
            DowngradeResponse downgradeResponse = objectMapper.readValue(downgradeResult.getResponse().getContentAsString(), DowngradeResponse.class);
            assertThat(downgradeResponse.getStatus()).isEqualTo("DOWNGRADED");
        }

        // Cancel
        CancelRequest cancelRequest = new CancelRequest();
        cancelRequest.setUserId(user.getId());
        MvcResult cancelResult = mockMvc.perform(post("/membership/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cancelRequest)))
                .andExpect(status().isOk())
                .andReturn();
        CancelResponse cancelResponse = objectMapper.readValue(cancelResult.getResponse().getContentAsString(), CancelResponse.class);
        assertThat(cancelResponse.getStatus()).isEqualTo("CANCELLED");

        // Renew (assume membershipId from subscribeResponse)
        RenewRequest renewRequest = new RenewRequest();
        renewRequest.setUserId(user.getId());
        renewRequest.setMembershipId(subscribeResponse.getMembershipId());
        MvcResult renewResult = mockMvc.perform(post("/membership/renew")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(renewRequest)))
                .andExpect(status().isOk())
                .andReturn();
        RenewResponse renewResponse = objectMapper.readValue(renewResult.getResponse().getContentAsString(), RenewResponse.class);
        assertThat(renewResponse.getStatus()).isEqualTo("RENEWED");
    }
} 