package hcmut.hospitalmanagement.models;


// This class is used to store reponses of User's Request 
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    // Constructor

    public ResponseObject() {}

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }


    // Getters and Setters

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
