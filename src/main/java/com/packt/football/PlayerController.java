package com.packt.football;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    return String.format("Player %s created", name);
  }

  @GetMapping("/{name}")
  public String readPlayer(@PathVariable String name) {
    return name;
  }

  @DeleteMapping("/{name}")
  public String deletePlayer(@PathVariable String name) {
    return String.format("Player %s deleted", name);
  }

  @PutMapping("/{name}")
  public String updatePlayer(@PathVariable String name, @RequestBody String newName) {
    return String.format("Player %s updated to %s", name, newName);
  }
}
