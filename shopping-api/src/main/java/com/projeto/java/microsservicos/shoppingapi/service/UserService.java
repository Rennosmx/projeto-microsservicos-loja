package com.projeto.java.microsservicos.shoppingapi.service;

import com.projeto.java.microsservicos.shoppingclient.dto.UserDTO;
import com.projeto.java.microsservicos.shoppingclient.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080}")
    private String userApiURL;

    public UserDTO getUserByCpf(String cpf, String key) {

        try {
            WebClient webClient = WebClient.builder()
                                           .baseUrl(userApiURL)
                                           .build();

            Mono<UserDTO> user = webClient.get()
                                          .uri("/user/" + cpf + "/cpf?key=" + key)
                                          .retrieve()
                                          .bodyToMono(UserDTO.class);

            return user.block();

        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

}
