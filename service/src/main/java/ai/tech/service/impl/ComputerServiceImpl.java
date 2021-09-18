package ai.tech.service.impl;

import ai.tech.domain.model.Computer;
import ai.tech.repository.ComputerRepository;
import ai.tech.service.ComputerService;
import ai.tech.service.mapper.ComputerMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ComputerServiceImpl implements ComputerService {
  private final ComputerRepository computerRepository;
  private final ComputerMapper COMPUTER_MAPPER = ComputerMapper.INSTANCE;

  @Autowired
  public ComputerServiceImpl(final ComputerRepository computerRepository) {
    this.computerRepository = computerRepository;
  }

  @Override
  public Optional<ai.tech.openApi.domain.model.Computer> addComputer(final ai.tech.openApi.domain.model.Computer computerDto) {
    Computer newComputer = new Computer();
    newComputer.setModel(computerDto.getModel());
    newComputer.setYearProduced(computerDto.getYearProduced().intValue());
    newComputer.setPrice(computerDto.getPrice());
    this.computerRepository.save(newComputer);

    return Optional.of(this.COMPUTER_MAPPER.toDto(newComputer));
  }

  @Override
  public Optional<List<ai.tech.openApi.domain.model.Computer>> findAll() {
    return Optional.of(this.computerRepository.findAll()
            .stream().map(computer -> this.COMPUTER_MAPPER.toDto(computer)).collect(Collectors.toList())
    );
  }

  @Override
  public Page<ai.tech.openApi.domain.model.Computer> findAllPaginated(final int page, final int size, final String sortField, final String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
    Pageable pageable = PageRequest.of(page - 1, size, sort);
    Page<Computer> computersPaginated = this.computerRepository.findAll(pageable);

    return computersPaginated.map(computer -> this.COMPUTER_MAPPER.toDto(computer));
  }

  @Override
  public Optional<ai.tech.openApi.domain.model.Computer> findById(final long id) {
    return Optional.of(this.COMPUTER_MAPPER.toDto(this.computerRepository.findById(id).get()));
  }

  @Override
  public Optional<ai.tech.openApi.domain.model.Computer> updateById(final long id, final ai.tech.openApi.domain.model.Computer computerDto) {
    Optional<Computer> computerOptional = computerRepository.findById(id);
    if (!computerOptional.isEmpty()) {
      this.computerRepository.save(COMPUTER_MAPPER.mergeComputer(computerDto, computerOptional.get()));
      return Optional.of(this.COMPUTER_MAPPER.toDto(computerOptional.get()));
    }
    return Optional.of(this.COMPUTER_MAPPER.toDto(computerOptional.get()));
  }

  @Override
  public void deleteById(final long id) {
    this.computerRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    this.computerRepository.deleteAll();
  }
}