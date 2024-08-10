package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ArgumentNotFound;
import com.app.dto.ApiResponse;
import com.app.dto.VaccineDto;
import com.app.entity.Vaccine;
import com.app.repository.VaccineRepository;

@Service
@Transactional
public class VaccineServiceImpl implements VaccineService {

	@Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public VaccineDto addNewVaccine(VaccineDto vaccineDto) {
        Vaccine vaccine = mapper.map(vaccineDto, Vaccine.class);
        vaccineRepository.save(vaccine);
        return mapper.map(vaccine, VaccineDto.class);
    }

    @Override
    public List<VaccineDto> getAllVaccines() {
        return vaccineRepository.findAll().stream()
                .map(vaccine -> mapper.map(vaccine, VaccineDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VaccineDto updateVaccine(Long id, VaccineDto vaccineUpdateDto) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new ArgumentNotFound("Vaccine ID is Invalid!!!"));

        mapper.map(vaccineUpdateDto, vaccine);
        vaccineRepository.save(vaccine);
        return mapper.map(vaccine, VaccineDto.class);
    }

    @Override
    public ApiResponse deleteVaccine(Long id) {
        vaccineRepository.deleteById(id);
        return new ApiResponse("Vaccine is deleted successfully with id " + id);
    }
}
