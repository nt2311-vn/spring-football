package com.packt.football;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.packt.football.exceptions.NotFoundException;
import com.packt.football.model.Player;
import com.packt.football.services.FootballService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = PlayerController.class)
public class PlayerControllerTest {
  @Autowired private MockMvc mvc;
  @MockBean private FootballService footballService;

  @Test
  public void testListPlayers() throws Exception {
    Player player1 =
        new Player("1884823", 5, "Ivana ANDRES", "Defender", LocalDate.of(1994, 07, 13));

    Player player2 =
        new Player("325636", 11, "Alexia PUTELAS", "Midfielder", LocalDate.of(1994, 02, 04));

    List<Player> players = List.of(player1, player2);
    given(footballService.listPlayers()).willReturn(players);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());

    MvcResult result =
        mvc.perform(MockMvcRequestBuilders.get("/players").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
            .andReturn();

    String json = result.getResponse().getContentAsString();
    List<Player> returnedPlayers =
        mapper.readValue(
            json, mapper.getTypeFactory().constructCollectionType(List.class, Player.class));

    assertArrayEquals(players.toArray(), returnedPlayers.toArray());
  }

  @Test
  public void testReadPlayers_doesnt_exist() throws Exception {
    String id = "1884823";
    given(footballService.getPlayer(id)).willThrow(new NotFoundException("Player not found"));

    mvc.perform(MockMvcRequestBuilders.get("/players/" + id).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
