package com.AirlinesApp.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler Osób, mapowany na adres "/api/people"
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/people")
public class PersonController{}