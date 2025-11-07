package com.packt.football;

import com.packt.football.exceptions.AlreadyExistsException;
import com.packt.football.exceptions.NotFoundException;
import com.packt.football.model.Player;
import com.packt.football.services.FootballService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/players")
@RestController
public class PlayerController {
  private FootballService footballService;

  public PlayerController(FootballService footballService) {
    this.footballService = footballService;
  }

  @GetMapping
  public List<Player> listPlayers() {
    return footballService.listPlayers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Player> readPlayer(@PathVariable String id) {
    Player player = footballService.getPlayer(id);
    return new ResponseEntity<>(player, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
    Player addedPlayer = footballService.addPlayer(player);
    return new ResponseEntity<>(addedPlayer, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public void deletePlayer(@PathVariable String id) {
    footballService.deletePlayer(id);
  }

  @PutMapping("/{id}")
  public void updatePlayer(@PathVariable String id, @RequestBody Player player) {
    footballService.updatePlayer(player);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
  @ExceptionHandler(NotFoundException.class)
  public void notFoundHandler() {}

  @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already exists")
  @ExceptionHandler(AlreadyExistsException.class)
  public void alreadyExistsHandler() {}
}
