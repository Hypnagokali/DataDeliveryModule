package de.inmed.DropzoneFileUpload.application.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.inmed.DropzoneFileUpload.common.SelfValidating;
import de.inmed.DropzoneFileUpload.domain.DataDelivery;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public interface CreateAndLoadDataDeliveryUseCase {

    DataDelivery findDataDelivery(DataDeliveryTransientId id);
    DataDelivery createDataDelivery();

    @Getter
    final class DataDeliveryTransientId extends SelfValidating<DataDeliveryTransientId> {

        @NotNull
        private String uuid;

        @JsonCreator
        public DataDeliveryTransientId(String uuid) {
            this.uuid = uuid;
            validateSelf();
        }
    }
}
