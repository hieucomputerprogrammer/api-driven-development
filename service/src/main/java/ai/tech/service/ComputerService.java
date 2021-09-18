package ai.tech.service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ComputerService {
  Optional<ai.tech.openApi.domain.model.Computer> addComputer(ai.tech.openApi.domain.model.Computer computerDto);
  Optional<List<ai.tech.openApi.domain.model.Computer>> findAll();
  Page<ai.tech.openApi.domain.model.Computer> findAllPaginated(int page, int size, String sortField, String sortDirection);
  Optional<ai.tech.openApi.domain.model.Computer> findById(long id);
  Optional<ai.tech.openApi.domain.model.Computer> updateById(long id, ai.tech.openApi.domain.model.Computer computerDto);
  void deleteById(long id);
  void deleteAll();
}