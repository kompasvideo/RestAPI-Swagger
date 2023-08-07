package ru.yandex.practicum.filmorate.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Пользователь")
public class User {
    private int id;
    @NotEmpty
    @Email
    @Schema(description = "Электронная почта", example = "example@mail.ru")
    private String email;
    @NotEmpty
    @Schema(description = "Логин", example = "user123")
    private String login;

    @Schema(description = "ФИО", example = "Иванов Иван Иванович")
    private String name;
    @Schema(description = "Дата рождения", example = "01.05.1990")
    private LocalDate birthday;

    public User(int id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }
}