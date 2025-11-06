package com.packt.football;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/players")
@RestController
public class PlayerController {
  @GetMapping
  public List<String> listPlayers() {
    return List.of("Ivana ANDES", "Alexia PUTELLAS");
  }
}
