package ca.pitt.camel.ce.dto;

public class CloudEventDTO {
    
    public static int count = 0;

    String data;

    public CloudEventDTO() {
        CloudEventDTO.count = CloudEventDTO.count + 1;
        this.data = CloudEventDTO.count + "";
    }

    public CloudEventDTO(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data: " + this.data;
    }

}
