package ru.yandex.practicum.filmorate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
@Tag(name = "Пользователи", description = "Управление пользователями")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Operation(summary = "Получить всех пользователей", description = "Позволяет получить всех пользователей")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    @Operation(summary = "Создание пользователя", description = "Позволяет создать пользователя")
    public User create(@Valid @RequestBody @Parameter(description = "Пользователь") User user) {
        return userService.postUser(user);
    }

    @PutMapping("/users")
    @Operation(summary = "Обновление пользователя", description = "Позволяет изменить данные пользователя")
    public User put(@Valid @RequestBody @Parameter(description = "Пользователь") User user) {
        return userService.putUser(user);
    }

    /**
     * Получение user по id
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    @Operation(summary = "Получение пользователя по id", description = "Позволяет получить пользователя по id")
    public User userById(@PathVariable @Parameter(description = "Id пользователя") int id) {
        return userService.getUser(id);
    }

    /**
     * добавление в друзья
     */
    @PutMapping("/users/{id}/friends/{friendId}")
    @Operation(summary = "Добавить в друзья", description = "Позволяет добавить друга по id")
    public void addFriends(@PathVariable @Parameter(description = "Id пользователя") int id, @PathVariable
    @Parameter(description = "Id друга") int friendId) {
        userService.addFriend(id, friendId);
    }

    /**
     * удаление из друзей
     * @param id
     * @param friendId
     */
    @DeleteMapping("/users/{id}/friends/{friendId}")
    @Operation(summary = "Удалить из друзей", description = "Позволяет удалить друга по id")
    public void deleteFriends(@PathVariable @Parameter(description = "Id пользователя") int id,
           @PathVariable @Parameter(description = "Id друга") int friendId) {
        userService.deleteFriend(id, friendId);
    }

    /**
     * Список друзей
     *
     * @return
     */
    @GetMapping("/users/{id}/friends")
    @Operation(summary = "Показать список друзей",
        description = "Возвращаем список пользователей, являющихся его друзьями")
    public List<User> listFriends(@PathVariable @Parameter(description = "Id пользователя") int id) {
        return userService.listFriends(id);
    }

    /**
     * список друзей, общих с другим пользователем
     * @param id
     * @param otherId
     * @return
     */
    @GetMapping("/users/{id}/friends/common/{otherId}")
    @Operation(summary = "Показать список друзей общих с другим пользователем",
        description = "Позволяет показать список друзей общих с другим пользователем")
    public List<User> listOtherFriends(@PathVariable @Parameter(description = "Id пользователя") int id,
            @PathVariable  @Parameter(description = "Id другого пользователя")int otherId) {
        return userService.listOtherFriends(id, otherId);
    }
}
