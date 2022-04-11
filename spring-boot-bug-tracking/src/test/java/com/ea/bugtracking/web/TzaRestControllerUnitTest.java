package com.ea.bugtracking.web;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ea.bugtracking.service.ApplicationService;
import com.ea.bugtracking.service.TicketService;
import com.ea.bugtracking.web.TzaRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(TzaRestController.class)
public class TzaRestControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApplicationService applicationService;

    @MockBean
    TicketService ticketService;
    
    @Test
    public void getAllApplications() throws Exception {
        mockMvc.perform(get("/tza/applications/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(applicationService, times(1)).listApplications();
    }

    @Test
    public void getAllTickets() throws Exception {
        mockMvc.perform(get("/tza/tickets/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(ticketService, times(1)).listTickets();
    }
}
