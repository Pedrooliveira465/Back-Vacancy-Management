package br.com.pedrooliveira.vacancy_management.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException() {
        super("Company not found");
    }
}
