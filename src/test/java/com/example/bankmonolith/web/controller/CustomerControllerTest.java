package com.example.bankmonolith.web.controller;

import com.example.bankmonolith.domain.Customer;
import com.example.bankmonolith.service.CustomerService;
import com.example.bankmonolith.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController controller;

    MockMvc mockMvc;
    UUID uuid;



    @BeforeEach
    void setUp() {
        final String id = "493410b3-dd0b-4b78-97bf-289f50f6e74f";
        uuid = UUID.fromString(id);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void findCustomers() {
    }

    @Test
    void showCustomer() throws Exception {
        when(customerService.findById(uuid)).thenReturn(CustomerDto.builder().id(uuid).build());
        mockMvc.perform(get("/customers/"+uuid))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/customerDetails"))
                .andExpect(model().attribute("customer",hasProperty("id",is(uuid))));

    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/createCustomer"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    void processCreationForm() throws Exception {
        when(customerService.saveCustomer(ArgumentMatchers.any())).thenReturn(CustomerDto.builder().id(uuid).build());
        mockMvc.perform(post("/customers/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/"+ uuid));
    }

    @Test
    void initUpdateForm() throws Exception {
        when(customerService.findById(uuid)).thenReturn(CustomerDto.builder().id(uuid).build());
        mockMvc.perform(get("/customers/"+uuid+"/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/createOrUpdateCustomer"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    void processUpdateForm() throws Exception {
        when(customerService.updateCustomer(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(CustomerDto.builder().id(uuid).build());
        mockMvc.perform(post("/customers/"+uuid+"/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/"+ uuid));
    }
}