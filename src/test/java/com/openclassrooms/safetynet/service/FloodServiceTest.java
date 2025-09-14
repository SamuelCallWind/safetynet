package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {
    

    @Mock
    RootRepository rootRepository;

    @Autowired
    FloodService floodService;


}
