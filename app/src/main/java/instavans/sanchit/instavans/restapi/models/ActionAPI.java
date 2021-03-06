package instavans.sanchit.instavans.restapi.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

import instavans.sanchit.instavans.datamodel.Action;

/**
 * Created by sanchitjain on 06/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Parcel(Parcel.Serialization.METHOD)
public class ActionAPI {
    private Boolean success;
    @JsonProperty("response")
    private Action response;
    @JsonProperty("error")
    private String error;
    @JsonProperty("errors")
    private String errors;

    /**
     *
     * @return
     * The success
     */
    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    /**
     *
     * @param success
     * The success
     */
    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     * The response
     */
    @JsonProperty("response")
    public Action getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    @JsonProperty("response")
    public void setResponse(Action response) {
        this.response = response;
    }
    @JsonProperty("error")
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The errors
     */
    @JsonProperty("errors")
    public String getErrors() {
        return errors;
    }

    /**
     *
     * @param errors
     * The errors
     */
    @JsonProperty("errors")
    public void setErrors(String errors) {
        this.errors = errors;
    }

}
