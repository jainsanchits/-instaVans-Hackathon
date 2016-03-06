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
public class Chat {
    @JsonProperty("job_number")
    private String jobNumber;
    @JsonProperty("message")
    private String message;
    @JsonProperty("triggered_at")
    private String triggeredAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("id")
    private Integer id;

    /**
     *
     * @return
     * The jobNumber
     */
    @JsonProperty("job_number")
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     *
     * @param jobNumber
     * The job_number
     */
    @JsonProperty("job_number")
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     *
     * @return
     * The message
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The triggeredAt
     */
    @JsonProperty("triggered_at")
    public String getTriggeredAt() {
        return triggeredAt;
    }

    /**
     *
     * @param triggeredAt
     * The triggered_at
     */
    @JsonProperty("triggered_at")
    public void setTriggeredAt(String triggeredAt) {
        this.triggeredAt = triggeredAt;
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


}
