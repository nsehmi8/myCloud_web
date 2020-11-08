package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageResponse {
    
    @SerializedName("id")
    @Expose
    int uploadId;

    @SerializedName("status")
    @Expose
    String uploadStatus;

    @SerializedName("timestamp")
    @Expose
    String uploadTimeStamp;

    public int getUploadId() {
        return uploadId;
    }

    public void setUploadId(int uploadId) {
        this.uploadId = uploadId;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getUploadTimeStamp() {
        return uploadTimeStamp;
    }

    public void setUploadTimeStamp(String uploadTimeStamp) {
        this.uploadTimeStamp = uploadTimeStamp;
    }
    
    
}
