package br.com.pedrooliveira.vacancy_management.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedrooliveira.vacancy_management.modules.company.dto.CreateJobDTO;
import br.com.pedrooliveira.vacancy_management.modules.company.entities.JobEntity;
import br.com.pedrooliveira.vacancy_management.modules.company.useCases.CreateJobUseCase;
import br.com.pedrooliveira.vacancy_management.modules.company.useCases.ListAllJobsByCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {

        @Autowired
        private CreateJobUseCase createJobUseCase;

        @Autowired
        private ListAllJobsByCompanyUseCase listAllJobsByCompanyUseCase;

        @PostMapping("/")
        @PreAuthorize("hasRole('COMPANY')")
        @Tag(name = "Vacancies", description = "Vacancies informations")
        @Operation(summary = "Vacancy registration", description = "This function is responsible for registrate all vacancies in the company")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = JobEntity.class))
                        })
        })

        @SecurityRequirement(name = "jwt_auth")
        public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO createJobDTO,
                        HttpServletRequest request) {
                var companyId = request.getAttribute("company_id");

                try {
                        var jobEntity = JobEntity.builder()
                                        .benefits(createJobDTO.getBenefits())
                                        .companyId(UUID.fromString(companyId.toString()))
                                        .description(createJobDTO.getDescription())
                                        .level(createJobDTO.getLevel())
                                        .build();

                        var result = this.createJobUseCase.execute(jobEntity);
                        return ResponseEntity.ok().body(result);

                } catch (Exception e) {
                        return ResponseEntity.badRequest().body(e);
                }
        }

        @GetMapping("/")
        @PreAuthorize("hasRole('COMPANY')")
        @Tag(name = "Vacancies", description = "List of vacancies")
        @Operation(summary = "List of vacancies", description = "This function is responsible for list all vacancies in the company")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = JobEntity.class))
                        })
        })

        @SecurityRequirement(name = "jwt_auth")
        public ResponseEntity<Object> listByCompany(HttpServletRequest request) {
                var companyId = request.getAttribute("company_id");
                var result = this.listAllJobsByCompanyUseCase.execute(UUID.fromString(companyId.toString()));
                return ResponseEntity.ok().body(result);
        }

}
