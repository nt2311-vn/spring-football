package com.packt.football;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/players")
@RestController
public class PlayerController {
  @GetMapping
  public List<String> listPlayers() {
    return List.of("Ivana ANDES", "Alexia PUTELLAS");
  }

  @PostMapping
  public String createPlayer(@RequestBody String name) {
    return System.out.format("Player %s created", name).toString();
  }

  @GetMapping("/{name}")
  public String readPlayer(@PathVariable String name) {
    return name;
  }
}
