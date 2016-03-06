package instavans.sanchit.instavans.datamodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by sanchitjain on 05/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Parcel(Parcel.Serialization.METHOD)
public class Signup {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("porter_id")
    private String porterId;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("contact")
    private String contact;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("remember_token")
    private String rememberToken;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("deleted_at")
    private String deletedAt;
    @JsonProperty("total_earning")
    private String total_earning;
    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The porterId
     */
    @JsonProperty("porter_id")
    public String getPorterId() {
        return porterId;
    }

    /**
     *
     * @param porterId
     * The porter_id
     */
    @JsonProperty("porter_id")
    public void setPorterId(String porterId) {
        this.porterId = porterId;
    }

    /**
     *
     * @return
     * The displayName
     */
    @JsonProperty("display_name")
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The display_name
     */
    @JsonProperty("display_name")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The contact
     */
    @JsonProperty("contact")
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     * The contact
     */
    @JsonProperty("contact")
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     * The latitude
     */
    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The rememberToken
     */
    @JsonProperty("remember_token")
    public String getRememberToken() {
        return rememberToken;
    }

    /**
     *
     * @param rememberToken
     * The remember_token
     */
    @JsonProperty("remember_token")
    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    /**
     *
     * @return
     * The createdAt
     */
    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The deletedAt
     */
    @JsonProperty("deleted_at")
    public String getDeletedAt() {
        return deletedAt;
    }

    /**
     *
     * @param deletedAt
     * The deleted_at
     */
    @JsonProperty("deleted_at")
    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    @JsonProperty("total_earning")
    public String getTotal_earning() {
        return total_earning;
    }

    @JsonProperty("total_earning")
    public void setTotal_earning(String total_earning) {
        this.total_earning = total_earning;
    }
}
