package ru.yandex.practicum.filmorate.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Schema(description = "Фильм")
public class Film {
    private int id;
    @NotEmpty
    @Schema(description = "Название", example = "Бэтмен")
    private String name;
    @Size(max = 200)
    @Schema(description = "Описание", example = "Продолжение культовой супергеройской франшизы")
    private String description;
    @Schema(description = "Дата релиза", example = "01.01.2022")
    private LocalDate releaseDate;
    @Schema(description = "Длительность", example = "150")
    private int duration;
    @Schema(description = "Рейтнинг", example = "8")
    private int rate;
    @NotNull
    @Schema(description = "Возрастной рейтинг", example = "NC-17")
    private Mpa mpa;
    @Schema(description = "Жанр", example = "Боевик")
    private List<Genre> genres;

    @Autowired
    public Film(int id, String name, String description, LocalDate releaseDate, int duration,
            int rate,  Mpa mpa, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.rate = rate;
        this.mpa = mpa;
        this.genres = genres;
    }
}
