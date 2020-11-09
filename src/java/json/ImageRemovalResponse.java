package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageRemovalResponse {

    @SerializedName("removed")
    @Expose
    private boolean isRemoved;

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

}
