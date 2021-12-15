package de.inmed.DropzoneFileUpload.application.service;

import de.inmed.DropzoneFileUpload.application.in.CreateOrLoadDataDeliveryUseCase;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class CreateOrLoadDataDeliveryService implements CreateOrLoadDataDeliveryUseCase {

    private final Map<DataDeliveryId, DataDelivery> dataDeliveryLookupMap = new HashMap<>();


    @Override
    public DataDelivery findDataDelivery(DataDeliveryId id) {
        DataDelivery dataDelivery = dataDeliveryLookupMap.get(id);

        if (dataDelivery == null) {
            throw new NoSuchDataDeliveryException(id.value());
        }

        return dataDelivery;
    }

    @Override
    public DataDelivery createDataDelivery() {
        DataDelivery dataDelivery = new DataDelivery(new DataDeliveryId(UUID.randomUUID().toString()));
        dataDeliveryLookupMap.put(dataDelivery.getId(), dataDelivery);

        return dataDelivery;
    }

}
