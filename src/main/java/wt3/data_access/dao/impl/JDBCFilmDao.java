package wt3.data_access.dao.impl;

import wt3.data_access.dao.FilmDao;
import wt3.data_access.dto.Film;
import wt3.data_access.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCFilmDao implements FilmDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Film> getFilms() {
        try {
            return jdbcTemplate.query(
                    "select * from films ORDER BY rating DESC",
                    new BeanPropertyRowMapper<>(Film.class)
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addFilm(String title, String description, String imgUrl) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO films (name, description, img) VALUES (?, ?, ?)",
                    title, description, imgUrl
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void editFilm(String title, String description, String imgUrl, int id) {
        try {
            jdbcTemplate.update(
                    "UPDATE films SET name = ?, description = ?, img = ? WHERE (id = ?)",
                    title, description, imgUrl, id
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Film getFilm(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from films where id = ?",
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(Film.class)
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Review> getReviews(int filmId, int userId) {
        try {
                List<Review> reviews = jdbcTemplate.query(
                    "SELECT r.id, " +
                            "u.id AS user_id, " +
                            "u.name AS user_name, " +
                            "u.rating AS user_rating, " +
                            "u.role AS user_role, " +
                            "r.text, " +
                            "r.rating " +
                            "FROM reviews r " +
                            "JOIN films f ON r.film_id = f.id " +
                            "JOIN users u ON r.user_id = u.id " +
                            "WHERE f.id = ?",
                    new Object[]{filmId},
                    new BeanPropertyRowMapper<>(Review.class)
            );
            for (Review review : reviews) {
                if (review.getUserId() == userId) {
                    List<Review> updatedReviews = new ArrayList<>();
                    updatedReviews.add(review);
                    reviews.remove(review);
                    updatedReviews.addAll(reviews);
                    return updatedReviews;
                }
            }
            return reviews;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addReview(int filmId, double rating, String text, int userId) {
        try {
            jdbcTemplate.update(
                    "INSERT INTO reviews (user_id, film_id, text, rating) VALUES (?, ?, ?, ?)",
                    userId, filmId, text, rating
            );
            this.updateFilmRating(filmId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteReview(int reviewId, int filmId) {
        try {
            jdbcTemplate.update("DELETE FROM reviews WHERE id = ?", reviewId);
            this.updateFilmRating(filmId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void updateFilmRating(int filmId) {
        try {
            Double newAvgRating = jdbcTemplate.queryForObject(
                    "SELECT AVG(rating) AS avg_rating FROM reviews WHERE film_id = ?",
                    Double.class, filmId
            );
            if (newAvgRating == null) {
                newAvgRating = 0.0;
            }
            jdbcTemplate.update(
                    "UPDATE films SET rating = ? WHERE id = ?",
                    newAvgRating, filmId
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
