package pl.pjatk.jazs24310nbp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.jazs24310nbp.model.CurrencyData;
import pl.pjatk.jazs24310nbp.model.NBPResponse;
import pl.pjatk.jazs24310nbp.repository.CurrencyDataRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NBPService {

    private final RestTemplate restTemplate;

    private final CurrencyDataRepository currencyDataRepository;

    private static final String NBPurl = "http://api.nbp.pl/api/exchangerates/rates";

    public NBPService(RestTemplate restTemplate, CurrencyDataRepository currencyDataRepository) {
        this.restTemplate = restTemplate;
        this.currencyDataRepository = currencyDataRepository;
    }

    public NBPResponse getCurrencyRates(String table, String code, LocalDate startDate, LocalDate endDate) {
        String url = String.format("%s/%s/%s/%s/%s", NBPurl, table, code, startDate, endDate);
        return restTemplate.getForObject(url, NBPResponse.class);
    }

    public double calculateAverageRate(List<NBPResponse.Rate> rates) {
        return rates.stream().mapToDouble(NBPResponse.Rate::getMid).average()
                .orElseThrow(() -> new RuntimeException("Nie można obliczyć średniego kursu"));
    }

    public CurrencyData saveCurrencyData(String currency, LocalDate startDate, LocalDate endDate, double averageRate) {
        CurrencyData item = new CurrencyData();
        item.setCurrency(currency);
        item.setStartDate(startDate);
        item.setEndDate(endDate);
        item.setAverageRate(averageRate);
        item.setQueryDateTime(LocalDateTime.now());

        currencyDataRepository.save(item);

        return item;
    }



}
