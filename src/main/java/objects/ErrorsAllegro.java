package objects;

import java.util.List;

public class ErrorsAllegro {

    private String code;

    private String message;

    private String path;

    private String userMessage;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }


    private List<ErrorsAllegro> details;

    public List<ErrorsAllegro> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorsAllegro> details) {
        this.details = details;
    }


}