package wt3.data_access.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private int id;
    private int userId;
    private String userName;
    private int userRating;
    private String userRole;
    private String text;
    private double rating;
}

