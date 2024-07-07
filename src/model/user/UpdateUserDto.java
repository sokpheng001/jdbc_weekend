package model.user;

import lombok.Builder;

@Builder
public record UpdateUserDto(String userName, String email, String password) { }
