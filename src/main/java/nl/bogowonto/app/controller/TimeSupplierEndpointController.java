package nl.bogowonto.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class TimeSupplierEndpointController {

    @GetMapping(value ="/time", produces = "application/json")
    public ZonedDateTime currentTime() {
        return ZonedDateTime.now();
    }

    @GetMapping(value ="/secrettime", produces = "application/json")
    public ZonedDateTime currentSecretTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

}
