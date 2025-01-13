package ru.clevertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.clevertec.client.dto.UserRequest;
import ru.clevertec.client.dto.UserResponse;

import java.util.UUID;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {

    @GetMapping("api/v1/users/{keycloakId}")
    UserResponse readUserByKeycloakId(@PathVariable("keycloakId") UUID keycloakId);

    @PostMapping("api/v1/users")
    UserResponse createOrUpdateUser(@RequestBody UserRequest userRequest);
}



