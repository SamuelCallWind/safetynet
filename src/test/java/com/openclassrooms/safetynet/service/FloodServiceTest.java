package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FloodResponse;
import com.openclassrooms.safetynet.dto.StationDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FloodServiceTest {

    private RootRepository rootRepository;
    private FloodService floodService;

    @BeforeEach
    void setUp() {
        rootRepository = mock(RootRepository.class);
        floodService = new FloodService(rootRepository);
    }

    @Test
    void getFloodResponse_returnsExpectedData() {
        Person john = new Person("John", "Doe", "123 Street",
                "City", "111-222-3333", "john@mail.com", 55555);

        try (MockedStatic<PersonService> personServiceMock = mockStatic(PersonService.class);
             MockedStatic<MedicalRecordService> medicalRecordServiceMock = mockStatic(MedicalRecordService.class);
             MockedStatic<FirestationService> firestationServiceMock = mockStatic(FirestationService.class);
             MockedStatic<DateUtils> dateUtilsMock = mockStatic(DateUtils.class)) {


            personServiceMock.when(() -> PersonService.getPersonsByFirestationNumber(1, rootRepository))
                    .thenReturn(List.of(john));

            medicalRecordServiceMock.when(() ->
                            MedicalRecordService.getMedicationFromFullName("John", "Doe", rootRepository))
                    .thenReturn(List.of("med1"));

            medicalRecordServiceMock.when(() ->
                            MedicalRecordService.getAllergiesFromFullName("John", "Doe", rootRepository))
                    .thenReturn(List.of("pollen"));

            medicalRecordServiceMock.when(() ->
                            MedicalRecordService.getBirthdateFromPersonsName("John", "Doe", rootRepository))
                    .thenReturn("01/01/1990");

            dateUtilsMock.when(() -> DateUtils.calculateAge("01/01/1990"))
                    .thenReturn(35);

            firestationServiceMock.when(() ->
                            FirestationService.getFirestationAddressFromNumber(1, rootRepository))
                    .thenReturn("123 Street");

            // Run
            FloodResponse response = floodService.getFloodResponse(List.of(1));

            // Verify
            assertNotNull(response);
            assertEquals(1, response.getStationDtos().size());

            StationDto stationDto = response.getStationDtos().get(0);
            assertEquals("123 Street", stationDto.getAddress());

            assertEquals(1, stationDto.getHouseholdDtos().size());
            assertEquals(1, stationDto.getHouseholdDtos().get(0).getFloodPersonDtos().size());

            var personDto = stationDto.getHouseholdDtos().get(0).getFloodPersonDtos().get(0);
            assertEquals("John", personDto.getFirstName());
            assertEquals("Doe", personDto.getLastName());
            assertEquals("111-222-3333", personDto.getPhoneNumber());
            assertEquals(List.of("med1"), personDto.getMedications());
            assertEquals(List.of("pollen"), personDto.getAllergies());
            assertEquals(35, personDto.getAge());
        }
    }
}
