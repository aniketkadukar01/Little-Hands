package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.VaccineDto;

public interface VaccineService {

	VaccineDto addNewVaccine(VaccineDto vaccineDto);

	List<VaccineDto> getAllVaccines();

	VaccineDto updateVaccine(Long id, VaccineDto vaccineUpdateDto);

	ApiResponse deleteVaccine(Long id);

}
