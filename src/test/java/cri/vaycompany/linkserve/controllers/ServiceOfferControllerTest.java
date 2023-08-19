//package cri.vaycompany.linkserve.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cri.vaycompany.linkserve.dto.ServiceOfferDTO;
//import cri.vaycompany.linkserve.services.ServiceOfferService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class ServiceOfferControllerTest {
//
//    private ServiceOfferService serviceOfferService;
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        serviceOfferService = Mockito.mock(ServiceOfferService.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(new ServiceOfferController(serviceOfferService)).build();
//    }
//
//    @Test
//    public void testGetServiceOfferById() throws Exception {
//        ServiceOfferDTO serviceOfferDTO = new ServiceOfferDTO();
//        serviceOfferDTO.setId(1L);
//        when(serviceOfferService.getServiceOfferById(anyLong())).thenReturn(Optional.of(serviceOfferDTO));
//
//        mockMvc.perform(get("/service-offers/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    public void testCreateServiceOffer()throws Exception {
//        ServiceOfferDTO inputServiceOffer = new ServiceOfferDTO();
//        inputServiceOffer.setServiceName("Test Service");
//
//        ServiceOfferDTO returnedServiceOffer = new ServiceOfferDTO();
//        returnedServiceOffer.setId(1L);
//        returnedServiceOffer.setServiceName("Test Service");
//
//        when(serviceOfferService.createServiceOffer(any(ServiceOfferDTO.class))).thenReturn(returnedServiceOffer);
//
//        mockMvc.perform(post("/service-offers")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(inputServiceOffer)))
//                .andExpect(status().isCreated()) // Ожидаемый статус - 201 Created
//                .andExpect(header().string("Location", "http://localhost/service-offers/1"))
//                .andExpect(content().json(new ObjectMapper().writeValueAsString(returnedServiceOffer)));
//    }
//}
