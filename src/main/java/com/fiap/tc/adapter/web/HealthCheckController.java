package com.fiap.tc.adapter.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = URLMapping.ROOT_API_HEALTH)
@Api(tags = "HealthCheck API V1", produces = APPLICATION_JSON_VALUE)
public class HealthCheckController {

    @ApiOperation(value = "Perform a health check", notes = "(Public Administrative Endpoint) This endpoint is responsible for validating the application's health.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Application is healthy", response = String.class)
    })
    @GetMapping
    public ResponseEntity<String> ping() {
        return ok("Ready");
    }
}

