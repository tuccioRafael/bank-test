package com.bank.test.controllers;
import com.bank.test.services.TransferService;
import com.bank.test.services.dto.TransferRequest;
import com.bank.test.services.dto.TransferResponse;
import com.bank.test.services.dto.TransferUpdateRequest;
import com.bank.test.services.dto.TransfersDetailsResponse;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferService transferService;

    private final TransferResponse transferResponse = new TransferResponse(
            1L,
            "1231231231",
            "1231231233",
            BigDecimal.valueOf(100),
            BigDecimal.valueOf(5.50),
            LocalDate.of(2024, 9, 11),
            LocalDate.now()
    );

    private final TransfersDetailsResponse transferenseDetailsResponse =  new TransfersDetailsResponse(
            1L,
            "1231231231",
            "1231231233",
            BigDecimal.valueOf(100),
            BigDecimal.valueOf(5.50),
            LocalDate.of(2024, 9, 11),
            LocalDate.now(),
            LocalDate.now()
    );
    private final TransferRequest transferRequest = new TransferRequest(
            "1231231231",
            "1231231233",
            BigDecimal.valueOf(100),
            LocalDate.of(2024,9,11)
    );

    private final TransferUpdateRequest updateRequest = new TransferUpdateRequest(
            1L,
            "1231231231",
            "1231231233",
            BigDecimal.valueOf(100),
            LocalDate.of(2024, 9, 11)
    );

    @Test
    void shouldReturnAllTransfers() throws Exception {
        Page<TransferResponse> transfersPage = new PageImpl<>(List.of(this.transferResponse));
        Mockito.when(this.transferService.findAllTransfers(Mockito.any(PageRequest.class)))
                .thenReturn(transfersPage);

        this.mockMvc.perform(get("/transfers").param("page", "0").param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0]").exists());
    }

    @Test
    void shouldReturnTransferDetails() throws Exception {
        Mockito.when(this.transferService.getTransfersById(1L))
                .thenReturn(this.transferenseDetailsResponse);

        mockMvc.perform(get("/transfers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldCreateNewTransfer() throws Exception {

        Mockito.when(transferService.createTransfers(transferRequest))
                .thenReturn(transferenseDetailsResponse);

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"transferValue\": \"100\", \"destinationAccount\": \"1231231233\", \"originAccount\" : \"1231231231\", \"transferDate\": \"2024-09-11\" }"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/transfers/1"));  // Verificando o header 'Location' para o novo recurso
    }

    @Test
    void shouldReturnMessagesWhenCreateTransfersWithOriginAccountIsInvalid() throws Exception {

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"transferValue\": \"100\", \"destinationAccount\": \"1231231234\", \"originAccount\" : \"12312312\", \"transferDate\": \"2024-09-11\" }"))
                .andExpect(status().isBadRequest())  // Espera um status 400
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Espera que o conteúdo seja JSON
                .andExpect(jsonPath("$[0].field").value("originAccount"))  // Verifica o campo da primeira mensagem de erro
                .andExpect(jsonPath("$[0].message").value("Número da conta origem invalido."));  // Verifica a mensagem da primeira mensagem de erro

    }

    @Test
    void shouldReturnMessagesWhenCreateTransfersWithDestinationAccountIsNotValid() throws Exception {
        // Prepare invalid request data
        String requestContent = "{ \"transferValue\": \"100\", \"destinationAccount\": \"12312312\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-11\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())  // Espera um status 400
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Espera que o conteúdo seja JSON
                .andExpect(jsonPath("$[0].field").value("destinationAccount"))  // Verifica o campo da primeira mensagem de erro
                .andExpect(jsonPath("$[0].message").value("Número da conta destino invalido."));  // Verifica a mensagem da primeira mensagem de erro

    }

    @Test
    void shouldReturnMessagesWhenCreateTransfersWithTransferValueIsNull() throws Exception {
        // Prepare invalid request data
        String requestContent = "{ \"transferValue\": \"null\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-11\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferValue"))
                .andExpect(jsonPath("$[0].message").value("O valor não pode ser vazio."));

    }

    @Test
    void shouldReturnMessagesWhenCreateTransfersWithTransferValueIsZERO() throws Exception {
        // Prepare invalid request data
        String requestContent = "{ \"transferValue\": \"0\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-11\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferValue"))
                .andExpect(jsonPath("$[0].message").value("O valor da transferencia tem que ser maior do que zero."));

    }

    @Test
    void shouldReturnMessagesWhenCreateTransfersWithTransferDateIsInPast() throws Exception {
        // Prepare invalid request data
        String requestContent = "{ \"transferValue\": \"10\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-10\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferDate"))
                .andExpect(jsonPath("$[0].message").value("A data de transferência precisa ser hoje ou uma data futura"));

    }

    @Test
    void shouldReturnMessagesWhenTransCreateTransfersWithferDateIsGreaterThan50days() throws Exception {
        // Prepare invalid request data
        var fiftyDays = LocalDate.now().plusDays(51);

        String requestContent = "{ \"transferValue\": \"10\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"" + fiftyDays + "\"  }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferDate"))
                .andExpect(jsonPath("$[0].message").value("A data de transferencia precisa ser de até 50 dias"));

    }


    @Test
    void shouldReturnMessagesWhenUpdateTransfersWithOriginAccountIsInvalid() throws Exception {
        mockMvc.perform(put("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"1\", \"transferValue\": \"100\", \"destinationAccount\": \"1231231234\", \"originAccount\" : \"12312312\", \"transferDate\": \"2024-09-11\" }"))
                .andExpect(status().isBadRequest())  // Espera um status 400
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Espera que o conteúdo seja JSON
                .andExpect(jsonPath("$[0].field").value("originAccount"))  // Verifica o campo da primeira mensagem de erro
                .andExpect(jsonPath("$[0].message").value("Número da conta origem invalido."));  // Verifica a mensagem da primeira mensagem de erro

    }

    @Test
    void shouldReturnMessagesWhenUpdateTransfersWithDestinationAccountIsNotValid() throws Exception {
        // Prepare invalid request data
        String requestContent = "{\"id\": \"1\", \"transferValue\": \"100\", \"destinationAccount\": \"12312312\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-11\" }";

        mockMvc.perform(put("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())  // Espera um status 400
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Espera que o conteúdo seja JSON
                .andExpect(jsonPath("$[0].field").value("destinationAccount"))  // Verifica o campo da primeira mensagem de erro
                .andExpect(jsonPath("$[0].message").value("Número da conta destino invalido."));  // Verifica a mensagem da primeira mensagem de erro

    }

    @Test
    void shouldReturnMessagesWhenUpdateTransfersWithTransferValueIsNull() throws Exception {
        // Prepare invalid request data
        String requestContent = "{ \"id\": \"1\", \"transferValue\": \"null\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-11\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferValue"))
                .andExpect(jsonPath("$[0].message").value("O valor não pode ser vazio."));

    }

    @Test
    void shouldReturnMessagesWhenUpdateTransfersWithTransferValueIsZERO() throws Exception {
        // Prepare invalid request data
        String requestContent = "{\"id\": \"1\", \"transferValue\": \"0\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-11\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferValue"))
                .andExpect(jsonPath("$[0].message").value("O valor da transferencia tem que ser maior do que zero."));

    }

    @Test
    void shouldReturnMessagesWhenUpdateTransfersWithTransferDateIsInPast() throws Exception {
        // Prepare invalid request data
        String requestContent = "{\"id\": \"1\",  \"transferValue\": \"10\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"2024-09-10\" }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferDate"))
                .andExpect(jsonPath("$[0].message").value("A data de transferência precisa ser hoje ou uma data futura"));

    }

    @Test
    void shouldReturnMessagesWhenTransferDateIsGreaterThan50days() throws Exception {
        // Prepare invalid request data
        var fiftyDays = LocalDate.now().plusDays(51);

        String requestContent = "{\"id\": \"1\", \"transferValue\": \"10\", \"destinationAccount\": \"1234567890\", \"originAccount\" : \"1231231230\", \"transferDate\": \"" + fiftyDays + "\"  }";

        mockMvc.perform(post("/transfers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field").value("transferDate"))
                .andExpect(jsonPath("$[0].message").value("A data de transferencia precisa ser de até 50 dias"));

    }
}
