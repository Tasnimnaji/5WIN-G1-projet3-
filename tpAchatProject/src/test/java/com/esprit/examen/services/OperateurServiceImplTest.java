package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {
    @Mock
    OperateurRepository operateurRepository;

    @InjectMocks
    OperateurServiceImpl operateurService;
    @Test
    public void retrieveAllOperateursTest() {
        when (operateurRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Operateur(1L, "Tasnim", "NAJI", "tasnim123", null),
                        new Operateur(2L, "Abdessalem", "DRINE", "absou123", null),
                        new Operateur(3L, "Yosra", "CHEKIR", "yosra123", null)
                )
        );

        assertEquals(3, operateurService.retrieveAllOperateurs().size());
    }
    @Test
    public void addOperateurTest() {
        Operateur op = new Operateur(1L,"Tasnim","NAJI","tasnim123", null);
        when(operateurRepository.save(op)).thenReturn(op);
        assertEquals(op, operateurService.addOperateur(op));
    }
    @Test
    public void retreiveOperateurTest() {
        Operateur op = new Operateur(2L,"Abdessalem","DRINE","absou123", null);
        when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op));
        Operateur op1 = operateurService.retrieveOperateur(2L);
        Assertions.assertNotNull(op1);

    }
    @Test
    public void retrieveOperatorNotFoundTest() {
        when(operateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NullPointerException.class, () -> {
            operateurService.retrieveOperateur(2L);
        });
    }
    @Test
    public void deleteOperateurTest() {
        operateurService.deleteOperateur(1L);
        verify(operateurRepository).deleteById(1L);

    }
    @Test
    public void updatetOperateurTest() {
        Operateur op = new Operateur(1L,"Tasnim","NAJI","tasnim123", null) ;
        Mockito.when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(op);
        op.setPrenom("kenza");;
        Operateur exisitingOp= operateurService.updateOperateur(op) ;

        assertNotNull(exisitingOp);
        assertEquals("kenza", op.getPrenom());
    }








}
