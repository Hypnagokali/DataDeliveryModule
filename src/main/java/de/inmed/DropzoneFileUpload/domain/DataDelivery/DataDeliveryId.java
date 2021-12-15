package de.inmed.DropzoneFileUpload.domain.DataDelivery;

import java.util.Objects;

public class DataDeliveryId {

    public static DataDeliveryId from(String uniqueId) {
        return new DataDeliveryId(uniqueId);
    }

    private String uniqueId = "";

    public DataDeliveryId(String uniqueId) {
        if (uniqueId == null || uniqueId.isEmpty()) {
            throw new IllegalStateException("uniqueId darf nicht null oder leer sein");
        }

        this.uniqueId = uniqueId;
    }

    public String value() {
        return uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDeliveryId that = (DataDeliveryId) o;
        return uniqueId.equals(that.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueId);
    }
}
