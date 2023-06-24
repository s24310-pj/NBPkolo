package pl.pjatk.jazs24310nbp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class CurrencyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unikalne ID dla każdego rekordu")
    private Long id;
    @Schema(description = "Waluta dla której sprawdzamy kurs")
    private String currency;
    @Schema(description = "Data początkowa od której pobieramy wyniki")
    private LocalDate startDate;
    @Schema(description = "Data końcowa do której pobieramy wyniki")
    private LocalDate endDate;
    @Schema(description = "Obliczony średni kurs waluty za podany przedział czasowy")
    private double averageRate;
    @Schema(description = "Aktualna data i czas podczas wykonywania zapytania")
    private LocalDateTime queryDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public LocalDateTime getQueryDateTime() {
        return queryDateTime;
    }

    public void setQueryDateTime(LocalDateTime queryDateTime) {
        this.queryDateTime = queryDateTime;
    }
}
