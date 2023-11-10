package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.esprit.examen.services.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Slf4j
class StockServiceImplTest {

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private StockServiceImpl stockService;

	@Test
	void retrieveAllStocksTest() {
		// Arrange
		List<Stock> mockStocks = new ArrayList<>();
		mockStocks.add(new Stock(/* provide necessary parameters */));
		mockStocks.add(new Stock(/* provide necessary parameters */));

		when(stockRepository.findAll()).thenReturn(mockStocks);

		// Act
		List<Stock> result = stockService.retrieveAllStocks();

		// Assert
		assertNotNull(result);
		assertEquals(2, result.size());
		// Add more assertions based on your business logic

		verify(stockRepository, times(1)).findAll();
	}

	@Test
	void addStockTest() {
		// Arrange
		Stock mockStock = new Stock(/* provide necessary parameters */);

		when(stockRepository.save(any(Stock.class))).thenReturn(mockStock);

		// Act
		Stock result = stockService.addStock(mockStock);

		// Assert
		assertNotNull(result);
		// Add more assertions based on your business logic

		verify(stockRepository, times(1)).save(any(Stock.class));
	}

	// Add similar test methods for other service methods

}
