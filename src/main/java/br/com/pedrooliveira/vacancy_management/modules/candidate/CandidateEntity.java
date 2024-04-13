package br.com.pedrooliveira.vacancy_management.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Pedro Oliveira", requiredMode = RequiredMode.REQUIRED, description = "Candidate name")
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "The field cannot have space")
    @Schema(example = "pedro", requiredMode = RequiredMode.REQUIRED, description = "Candidate username")
    private String username;

    @Email(message = "The field is not valid")
    @Schema(example = "pedro@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "Candidate email")
    private String email;

    @Length(min = 10, max = 100, message = "The password must contain 10 to 100 characters")
    @Schema(example = "admin@1234", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Candidate password")
    private String password;
    
    @Schema(example = "Java Developer", requiredMode = RequiredMode.REQUIRED, description = "Brief description of the candidate")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
