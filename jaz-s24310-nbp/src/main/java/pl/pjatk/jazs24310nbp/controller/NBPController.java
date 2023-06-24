package pl.pjatk.jazs24310nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.jazs24310nbp.model.CurrencyData;
import pl.pjatk.jazs24310nbp.model.NBPResponse;
import pl.pjatk.jazs24310nbp.service.NBPService;

import java.time.LocalDate;

@RestController
@RequestMapping("/NBP")
public class NBPController {

    private final NBPService nbpService;


    public NBPController(NBPService nbpService) {
        this.nbpService = nbpService;
    }

    @Tag(name = "Kontroler", description = "kontroler mikroserwisu NBPService")
    @Operation(summary = "Pobiera średni kurs waluty dla podanego zakresu dat", description = "Najpierw podajemy datę początkową, następnie date końcową, jako currency należy wstawić trzy literowy kod waluty w standardzie ISO 4217")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Zapytanie wykonane pomyślnie", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CurrencyData.class)) }),
            @ApiResponse(responseCode = "400", description = "Niepoprawny kod waluty lub niepoprwany zakres dat", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nie znaleziono danych dla zapytania lub adres URL skonstruowany niepoprawnie", content = @Content),
            @ApiResponse(responseCode = "500", description = "Błąd serwera NBP lub błąd wewnętrzny", content = @Content)
    })
    @GetMapping("/rate/{currency}/{startDate}/{endDate}")
    public ResponseEntity<CurrencyData> getCurrencyRate(@PathVariable("currency") String currency, @PathVariable("startDate")LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        NBPResponse nbpResponse = nbpService.getCurrencyRates("A", currency, startDate, endDate);
        double averageRate = nbpService.calculateAverageRate(nbpResponse.getRates());
        CurrencyData item = nbpService.saveCurrencyData(currency, startDate, endDate, averageRate);

        return ResponseEntity.ok(item);
    }
}
