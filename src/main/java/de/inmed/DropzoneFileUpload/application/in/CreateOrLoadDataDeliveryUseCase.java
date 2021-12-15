package de.inmed.DropzoneFileUpload.application.in;

import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.dataDelivery.DataDeliveryId;

public interface CreateOrLoadDataDeliveryUseCase {

    DataDelivery findDataDelivery(DataDeliveryId id);
    DataDelivery createDataDelivery();

    class NoSuchDataDeliveryException extends RuntimeException {

        public NoSuchDataDeliveryException(String value) {
            super("Keine Datenlieferung mit ID = " + value);
        }
    }
}
