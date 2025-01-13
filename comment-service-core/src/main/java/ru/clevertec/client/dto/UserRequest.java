package ru.clevertec.client.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserRequest(
        UUID keycloakUserId,
        String username
) {}



