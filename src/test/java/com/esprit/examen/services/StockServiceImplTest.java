package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockServiceImplTest {

	@InjectMocks
	private StockServiceImpl stockService;

	@Mock
	private StockRepository stockRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddStock() {
		// Création d'un objet Stock factice
		Stock stock = new Stock();
		stock.setIdStock(1L);

		// Configurer le comportement simulé du repository
		when(stockRepository.save(stock)).thenReturn(stock);

		// Appeler la méthode à tester
		Stock savedStock = stockService.addStock(stock);

		// Vérifier que le résultat est correct
		assertEquals(stock.getIdStock(), savedStock.getIdStock());
	}

	@Test
	public void testRetrieveStock() {
		Long stockId = 1L;

		// Création d'un objet Stock factice
		Stock stock = new Stock();
		stock.setIdStock(stockId);

		// Configurer le comportement simulé du repository
		when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));

		// Appeler la méthode à tester
		Stock retrievedStock = stockService.retrieveStock(stockId);

		// Vérifier que le résultat est correct
		assertEquals(stock.getIdStock(), retrievedStock.getIdStock());
	}

	@Test
	public void testRetrieveStockNotFound() {
		Long stockId = 1L;

		// Configurer le comportement simulé du repository pour retourner un Optional vide
		when(stockRepository.findById(stockId)).thenReturn(Optional.empty());

		// Appeler la méthode à tester et vérifier qu'elle lève une exception
		assertThrows(NullPointerException.class, () -> stockService.retrieveStock(stockId));
	}

	// Ajoutez d'autres tests unitaires selon vos besoins

}
