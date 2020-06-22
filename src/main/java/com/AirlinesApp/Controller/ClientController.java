package com.AirlinesApp.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Klasa kontrolera klientów, mapowanego pod adresem "/api/clients/".
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController{}
