package instavans.sanchit.instavans.restapi.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import instavans.sanchit.instavans.datamodel.ChatRecieve;

/**
 * Created by sanchitjain on 06/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Parcel(Parcel.Serialization.METHOD)
public class ChatRecieveAPI {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("response")
    private List<ChatRecieve> response = new ArrayList<ChatRecieve>();

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
    public List<ChatRecieve> getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    @JsonProperty("response")
    public void setResponse(List<ChatRecieve> response) {
        this.response = response;
    }

}
