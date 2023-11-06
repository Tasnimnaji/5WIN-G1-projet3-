package com.esprit.examen.services;
import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {
    @Mock
    private FournisseurRepository fournisseurRepository;

    @Mock
    private DetailFournisseurRepository detailFournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Test
    public void retrieveAllFournisseursTest() {
        // Arrange
        List<Fournisseur> fournisseurs = new ArrayList<>();
        fournisseurs.add(createSampleFournisseur());
        fournisseurs.add(createSampleFournisseur());
        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        // Act
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Assert
        assertEquals(2, result.size());
        // You can add more assertions based on your specific use case
    }

    @Test
    public void addFournisseurTest() {
        // Arrange
        Fournisseur fournisseur = createSampleFournisseur();
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        // Act
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Assert
        assertEquals(fournisseur, result);
        // You can add more assertions based on your specific use case
    }

    @Test
    public void updateFournisseurTest() {
        // Arrange
        Fournisseur fournisseur = createSampleFournisseur();
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        when(detailFournisseurRepository.save(fournisseur.getDetailFournisseur())).thenReturn(fournisseur.getDetailFournisseur());

        // Act
        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        // Assert
        assertEquals(fournisseur, result);
        // You can add more assertions based on your specific use case
    }

    @Test
    public void deleteFournisseurTest() {
        // Arrange
        Long fournisseurId = 1L;

        // Act
        fournisseurService.deleteFournisseur(fournisseurId);

        // Assert
        verify(fournisseurRepository).deleteById(fournisseurId);
    }

    @Test
    public void retrieveFournisseurTest() {
        // Arrange
        Long fournisseurId = 1L;
        Fournisseur fournisseur = createSampleFournisseur();
        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));

        // Act
        Fournisseur result = fournisseurService.retrieveFournisseur(fournisseurId);

        // Assert
        assertEquals(fournisseur, result);
        // You can add more assertions based on your specific use case
    }

    private Fournisseur createSampleFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setCode("ABC123");
        fournisseur.setLibelle("Sample Fournisseur");
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        // Set other properties as needed
        return fournisseur;
    }



}
