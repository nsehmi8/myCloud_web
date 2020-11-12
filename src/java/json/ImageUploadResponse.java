package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUploadResponse {

    @SerializedName("status")
    @Expose
    private boolean uplaodStatus;
    
    @SerializedName("image_path")
    @Expose
    private String imagePath;

    public boolean isUplaodStatus() {
        return uplaodStatus;
    }

    public void setUplaodStatus(boolean uplaodStatus) {
        this.uplaodStatus = uplaodStatus;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    
}
