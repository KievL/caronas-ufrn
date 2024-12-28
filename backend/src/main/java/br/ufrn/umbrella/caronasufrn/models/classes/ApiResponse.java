package br.ufrn.umbrella.caronasufrn.models.classes;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{
    private HttpStatus status;
    private String message;
    private T data;

    public ApiResponse<T> of(HttpStatus status_, String message_, T data_){
        this.status=status_;
        this.message = message_;
        this.data = data_;

        return this;

    }
}
