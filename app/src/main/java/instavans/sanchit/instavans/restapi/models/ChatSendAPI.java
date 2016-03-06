package instavans.sanchit.instavans.restapi.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

import instavans.sanchit.instavans.datamodel.Chat;

/**
 * Created by sanchitjain on 06/03/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Parcel(Parcel.Serialization.METHOD)
public class ChatSendAPI {
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("response")
    private Chat response;

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
     * The Chat
     */
    @JsonProperty("response")
    public Chat getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    @JsonProperty("response")
    public void setResponse(Chat response) {
        this.response = response;
    }

}
