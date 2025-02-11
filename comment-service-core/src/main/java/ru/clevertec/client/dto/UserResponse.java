package ru.clevertec.client.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        UUID keycloakUserId,
        String username
) {}


