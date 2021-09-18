package ai.tech.web.api;

import ai.tech.openApi.api.ComputersApi;
import ai.tech.openApi.domain.model.Computer;
import ai.tech.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ComputerRestApi implements ComputersApi {
  private final ComputerService computerService;

  @Override
  public ResponseEntity<Computer> addComputer(@Valid Computer computerDto) {
    return new ResponseEntity<Computer>(computerService.addComputer(computerDto).get(), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<List<Computer>> getComputers() {
    return ResponseEntity.ok(computerService.findAll().get());
  }

  @Override
  public ResponseEntity<Computer> getComputerById(Long id) {
    return ResponseEntity.ok(computerService.findById(id).get());
  }
}