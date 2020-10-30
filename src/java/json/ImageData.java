package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageData {

    @SerializedName(value = "upload_id")
    @Expose
    private int imageId;

    @SerializedName(value = "time_stamp")
    @Expose
    private long timestamp;

    @SerializedName(value = "image_path")
    @Expose
    private String imagePath;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
