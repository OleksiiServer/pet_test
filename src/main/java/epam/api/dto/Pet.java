package epam.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Pet {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("category")
    @Expose
    private Category category;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("photoUrls")
    @Expose
    private List<Object> photoUrls = null;

    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;

    @SerializedName("status")
    @Expose
    private String status;

}
