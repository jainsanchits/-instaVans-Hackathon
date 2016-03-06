package instavans.sanchit.instavans.datamodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by sanchitjain on 06/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Parcel(Parcel.Serialization.METHOD)
public class Action {
    @JsonProperty("job_number")
    private Integer jobNumber;
    @JsonProperty("shipper_id")
    private Integer shipperId;
    @JsonProperty("porter_id")
    private Integer porterId;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("address")
    private String address;
    @JsonProperty("price")
    private String price;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("action")
    private Integer action;
    @JsonProperty("status")
    private Integer status;

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
     * The porterId
     */
    @JsonProperty("porter_id")
    public Integer getPorterId() {
        return porterId;
    }

    /**
     *
     * @param porterId
     * The porter_id
     */
    @JsonProperty("porter_id")
    public void setPorterId(Integer porterId) {
        this.porterId = porterId;
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
     * The action
     */
    @JsonProperty("action")
    public Integer getAction() {
        return action;
    }

    /**
     *
     * @param action
     * The action
     */
    @JsonProperty("action")
    public void setAction(Integer action) {
        this.action = action;
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

}
