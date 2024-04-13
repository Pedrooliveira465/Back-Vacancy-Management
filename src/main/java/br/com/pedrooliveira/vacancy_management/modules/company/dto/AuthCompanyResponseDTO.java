package br.com.pedrooliveira.vacancy_management.modules.company.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCompanyResponseDTO {
    
    private String access_token;
    private Long expires_in;
    private List<String> roles;

}
