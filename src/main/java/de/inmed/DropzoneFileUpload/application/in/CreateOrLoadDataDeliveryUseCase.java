package de.inmed.DropzoneFileUpload.application.in;

import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDelivery;
import de.inmed.DropzoneFileUpload.domain.DataDelivery.DataDeliveryId;

public interface CreateOrLoadDataDeliveryUseCase {

    DataDelivery findDataDelivery(DataDeliveryId id);
    DataDelivery createDataDelivery();

    class NoSuchDataDeliveryException extends RuntimeException {

        public NoSuchDataDeliveryException(String value) {
            super("Keine Datenlieferung mit ID = " + value);
        }
    }
}
