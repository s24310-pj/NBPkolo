package pl.pjatk.jazs24310nbp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.jazs24310nbp.model.CurrencyData;

public interface CurrencyDataRepository extends JpaRepository<CurrencyData, Long> {
}