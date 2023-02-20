package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ListPrimeFactorsServiceApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    public void pathDecimalShouldReturnValue() throws Exception {
        this.mockMvc.perform(get("/primefactors/2402"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("2 × 1201"))
                .andExpect(jsonPath("$.source").value("2402"));
    }

    @Test

    public void pathDecimalShouldReturnSuperscriptValue() throws Exception {
        this.mockMvc.perform(get("/primefactors/4096"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("2¹²"))
                .andExpect(jsonPath("$.source").value("4096"));
    }


    @Test
    public void getPrimeLimits() throws Exception {
        this.mockMvc.perform(get("/primeLimits/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.lowerLimit").value("2"))
                .andExpect(jsonPath("$.upperLimit").value("4294967296"));
    }
}

