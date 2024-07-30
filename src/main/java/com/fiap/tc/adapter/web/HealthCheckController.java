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
@RequestMapping(path = URLMapping.ROOT_API_PING)
@Api(tags = "Ping", produces = APPLICATION_JSON_VALUE)
public class PingController {


    @ApiOperation(value = "Ping")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pong", response = String.class)
    })
    @GetMapping
    public ResponseEntity<String> ping() {
        return ok("Pong");
    }
}


