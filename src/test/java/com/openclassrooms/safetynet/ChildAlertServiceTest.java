package com.openclassrooms.safetynet;

import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.ChildAlertService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ChildAlertServiceTest {

    @Mock
    RootRepository rootRepository;

    @Test
    public void testGetChildAlert_ShouldReturnAnEmptyList() {
        ChildAlertService childAlertService = new ChildAlertService(rootRepository);
    }

}
