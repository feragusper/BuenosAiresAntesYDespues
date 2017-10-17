package com.feragusper.buenosairesantesydespues.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Fernando.Perez
 * @since 1.3
 * <p>
 * CustomFields Entity used in the data layer.
 */
class CustomFieldsEntity {
    @SerializedName("creditos_ahora")
    private List<String> creditsNow;

    @SerializedName("creditos_antigua")
    private List<String> creditsBefore;

    @SerializedName("descripcion")
    private List<String> description;

    @SerializedName("anio_antes")
    private List<String> yearBefore;

    @SerializedName("direccion")
    private List<String> address;

    @SerializedName("barrio")
    private List<String> neighborhood;

    @SerializedName("geo")
    private List<String> geo;

    String getCreditsNow() {
        return creditsNow.get(0);
    }

    public String getCreditsBefore() {
        return creditsBefore.get(0);
    }

    String getDescription() {
        return description.get(0);
    }

    String getGeo() {
        return geo.get(0);
    }

    String getYearBefore() {
        return yearBefore.get(0);
    }

    String getAddress() {
        return address.get(0);
    }

    String getNeighborhood() {
        return neighborhood.get(0);
    }

}
