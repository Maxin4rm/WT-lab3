package wt3.data_access.dao;

import wt3.data_access.dto.Film;
import wt3.data_access.dto.Review;

import java.util.List;

/**
 * Interface of dao with logic related to the films
 * @author dreizer
 * @version 1.0
 */
public interface FilmDao {
    /**
     * Method to get all films
     * @return List of all films
     */
    List<Film> getFilms();

    /**
     * Method to add new film
     * @param title New film title
     * @param description New film description
     * @param imgUrl New film image URL
     */
    void addFilm(String title, String description, String imgUrl);

    /**
     * Method to edit specific film
     * @param title Edited film title
     * @param description Edited film description
     * @param imgUrl Edited film image URL
     * @param id Edited film ID
     */
    void editFilm(String title, String description, String imgUrl, int id);

    /**
     * Method to get specific film
     * @param id Film ID
     * @return Film found by ID
     */
    Film getFilm(int id);

    /**
     * Method to get reviews for the film
     * @param filmId Film ID
     * @param userId ID of current user
     * @return List of reviews
     */
    List<Review> getReviews(int filmId, int userId);

    /**
     * Method to add new review for the specific film
     * @param filmId Film ID
     * @param userRating Film rating from user
     * @param reviewText Review text
     * @param userId Current user ID
     */
    void addReview(int filmId, double userRating, String reviewText, int userId);

    /**
     * Method to delete specific review
     * @param reviewId Review ID
     * @param filmId Film ID
     */
    void deleteReview(int reviewId, int filmId);
}
