package wt3.web;

import wt3.data_access.dao.FilmDao;
import wt3.data_access.dto.Film;
import wt3.data_access.dto.Review;
import wt3.data_access.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmsController {
    @Autowired
    private FilmDao filmDao;

    @GetMapping
    public String getFilmsPage(Model model) {
        model.addAttribute("films", filmDao.getFilms());
        return "films";
    }

    @PostMapping("/add")
    public String addFilm(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String imgUrl,
            Model model
    ) {
        filmDao.addFilm(title, description, imgUrl);
        return this.getFilmsPage(model);
    }

    @PostMapping("/edit")
    public String editFilm(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String imgUrl,
            @RequestParam int filmId,
            Model model
    ) {
        filmDao.editFilm(title, description, imgUrl, filmId);
        return this.getFilmsPage(model);
    }

    @GetMapping("/{id}")
    public String getFilm(@PathVariable int id, Model model, HttpSession session) {
        Film film = filmDao.getFilm(id);
        model.addAttribute("film", film);
        final User user = (User)session.getAttribute("user");
        this.getReviews(id, user.getId(), model);
        return "filmDetails";
    }

    public void getReviews(int filmId, int userId, Model model) {
        List<Review> reviews = filmDao.getReviews(filmId, userId);
        model.addAttribute("reviews", reviews);
    }

    @PostMapping("/review/add")
    public String addReview(
            @RequestParam int filmId,
            @RequestParam double rating,
            @RequestParam String text,
            Model model,
            HttpSession session
    ) {
        final User user = (User)session.getAttribute("user");
        filmDao.addReview(filmId, rating, text, user.getId());
        this.getReviews(filmId, user.getId(), model);
        return String.format("redirect:/films/%d", filmId);
    }

    @PostMapping("/review/delete")
    public String deleteReview(
            @RequestParam int filmId,
            @RequestParam int reviewId,
            Model model,
            HttpSession session
    ) {
        filmDao.deleteReview(reviewId, filmId);
        final User user = (User)session.getAttribute("user");
        this.getReviews(filmId, user.getId(), model);
        return String.format("redirect:/films/%d", filmId);
    }
}
