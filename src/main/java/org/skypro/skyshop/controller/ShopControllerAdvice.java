package org.skypro.skyshop.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.skypro.skyshop.exeptions.NoSuchProductException;
import org.skypro.skyshop.exeptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> noSuchProduct(NoSuchProductException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShopError());
    }
}
