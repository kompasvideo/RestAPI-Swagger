package ru.yandex.practicum.filmorate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.FilmService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
@Tag(name = "Фильмы", description = "Управляет работой с фильмами")

public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @GetMapping("/films")
    @Operation(summary = "Получить все фильмы", description = "Позволяет получить все фильмы")
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @Operation(summary = "Добавить фильм", description = "Позволяет добавить один фильм")
    @PostMapping("/films")
    public Film create(@Valid @RequestBody Film film) {
        return filmService.create(film);
    }

    @Operation(summary = "Обновить фильм", description = "Позволяет изменить данные о фильме")
    @PutMapping("/films")
    public Film put(@Valid @RequestBody Film film) {
        return filmService.put(film);
    }

    @Operation(summary = "Получить фильм по id", description = "Позволяет получить информацию о фильме")
    @GetMapping("/films/{id}")
    public Film film(@PathVariable int id) {
        return filmService.film(id);
    }

    /**
     * пользователь ставит лайк фильму
     * @param id
     * @param userId
     */
    @Operation(summary = "Добавить лайк фильму от пользователя",
        description = "Позволяет добавить лайк фильму от пользователя")
    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        filmService.addLike(id, userId);
    }

    /**
     * пользователь удаляет лайк
     * @param id
     * @param userId
     */
    @Operation(summary = "Удалиить лайк фильму от пользователя",
        description = "Позволяет удалить лайк фильму от пользователя")
    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        filmService.deleteLike(id, userId);
    }

    @Operation(summary = "Вывести список популярных фильмов",
        description = "Позволяет узнать список популярных фильмов")
    @GetMapping("/films/popular")
    public List<Film> listLikes(@RequestParam(required = false) Integer count) {
        if (count == null) {
            return filmService.listLikes();
        }
        return filmService.getPopularList(count);
    }

    @Operation(summary = "Вывести список возрастных рейтингов фильмов",
        description = "Позволяет узнать список возрастных рейтингов фильмов")
    @GetMapping("/mpa")
    public List<Mpa> getAllMpa() {
        return filmService.getAllMpa();
    }

    @Operation(summary = "Вывести возрастной рейтинг",
        description = "Позволяет вывести возрастной рейтинг по id")
    @GetMapping("/mpa/{id}")
    public Mpa getMpaById(@PathVariable int id) {
        return filmService.getMpa(id);
    }

    @Operation(summary = "Вывести список категорий фильмов",
        description = "Позволяет вывести список категорий фильмов")
    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return filmService.getAllGenres();
    }

    @Operation(summary = "Вывести категорию фильма",
        description = "Позволяет вывести категорию фильма по id")
    @GetMapping("/genres/{id}")
    public Genre getGenresById(@PathVariable int id) {
        return filmService.getGenre(id);
    }
}