package br.com.pedrooliveira.vacancy_management.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException() {
        super("Job not found");
    }
}
