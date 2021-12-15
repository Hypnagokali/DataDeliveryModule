package de.inmed.DropzoneFileUpload.application.service;

import de.inmed.DropzoneFileUpload.application.in.CreateOrLoadDataDeliveryUseCase;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class CreateAndLoadDataDeliveryServiceTest {

    @InjectMocks
    private CreateOrLoadDataDeliveryService createAndLoadDataDeliveryService;

    @Test
    void createNoDataDelivery_AndTryToFindSome_ExpectNotFoundException() throws Exception {
        assertThatThrownBy(() -> createAndLoadDataDeliveryService.findDataDelivery(new DataDeliveryId("123")))
                .isInstanceOf(CreateOrLoadDataDeliveryUseCase.NoSuchDataDeliveryException.class);

    }

    @Test
    void createTwoDataDeliveries_AndFindTheFirst() throws Exception {
        DataDelivery dataDelivery1 = createAndLoadDataDeliveryService.createDataDelivery();
        DataDelivery dataDelivery2 = createAndLoadDataDeliveryService.createDataDelivery();

        DataDelivery foundDataDelivery = createAndLoadDataDeliveryService.findDataDelivery(dataDelivery1.getId());

        assertThat(foundDataDelivery.getId()).isEqualTo(dataDelivery1.getId());
    }
}
