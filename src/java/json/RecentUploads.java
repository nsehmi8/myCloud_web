package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentUploads {

    @SerializedName("total")
    @Expose
    private int totalImages;

    @SerializedName("images")
    @Expose
    private ImageData[] images;

    public ImageData[] getImages() {
        return images;
    }

    public void setImages(ImageData[] images) {
        this.images = images;
    }

    public int getTotalImages() {
        return totalImages;
    }

    public void setTotalImages(int totalImages) {
        this.totalImages = totalImages;
    }
}
