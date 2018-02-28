package ru.shipcollision.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Модель пользователя (игрока).
 */
@SuppressWarnings({"PublicField", "unused"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    /**
     * Потокобезопасный генератор ID.
     */
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public @NotNull Long id;

    @JsonProperty("nickname")
    public @NotNull String nickName;

    @JsonProperty("email")
    public @NotNull String email;

    @JsonProperty(value = "rank", access = JsonProperty.Access.READ_ONLY)
    public int rank = 0;

    @JsonProperty(value = "avatarLink", access = JsonProperty.Access.READ_ONLY)
    @Nullable
    public String avatarLink;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    public @NotEmpty String passwordHash;

    public User() {
        this.id = ID_GENERATOR.getAndIncrement();
    }

    public User(@NotNull String nickName, @NotNull String email, @NotNull String passwordHash) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.nickName = nickName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !Objects.equals(getClass(), object.getClass())) {
            return false;
        }
        final User other = (User) object;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
