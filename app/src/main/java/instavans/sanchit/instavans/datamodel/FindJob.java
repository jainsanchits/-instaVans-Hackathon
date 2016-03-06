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
public class FindJob {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("job_number")
    private Integer jobNumber;
    @JsonProperty("shipper_id")
    private Integer shipperId;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("address")
    private String address;
    @JsonProperty("no_of_porters")
    private Integer noOfPorters;
    @JsonProperty("price")
    private String price;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("deleted_at")
    private String deletedAt;
    @JsonProperty("distance")
    private Double distance;

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
     * The jobNumber
     */
    @JsonProperty("job_number")
    public Integer getJobNumber() {
        return jobNumber;
    }

    /**
     *
     * @param jobNumber
     * The job_number
     */
    @JsonProperty("job_number")
    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     *
     * @return
     * The shipperId
     */
    @JsonProperty("shipper_id")
    public Integer getShipperId() {
        return shipperId;
    }

    /**
     *
     * @param shipperId
     * The shipper_id
     */
    @JsonProperty("shipper_id")
    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
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
     * The address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     * The noOfPorters
     */
    @JsonProperty("no_of_porters")
    public Integer getNoOfPorters() {
        return noOfPorters;
    }

    /**
     *
     * @param noOfPorters
     * The no_of_porters
     */
    @JsonProperty("no_of_porters")
    public void setNoOfPorters(Integer noOfPorters) {
        this.noOfPorters = noOfPorters;
    }

    /**
     *
     * @return
     * The price
     */
    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The startTime
     */
    @JsonProperty("start_time")
    public String getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     * The start_time
     */
    @JsonProperty("start_time")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     * The status
     */
    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     *
     * @return
     * The distance
     */
    @JsonProperty("distance")
    public Double getDistance() {
        return distance;
    }

    /**
     *
     * @param distance
     * The distance
     */
    @JsonProperty("distance")
    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
