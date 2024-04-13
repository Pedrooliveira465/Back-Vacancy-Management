package br.com.pedrooliveira.vacancy_management.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    
    @Schema(example = "Java Developer")
    private String description;

    @Schema(example = "Pedro")
    private String username;

    @Schema(example = "pedro@gmail.com")
    private String email;
    private UUID id;

    @Schema(example = "Pedro Oliveira")
    private String name;

}
