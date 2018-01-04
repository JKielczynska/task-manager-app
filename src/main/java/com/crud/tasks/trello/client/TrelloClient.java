package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.username}")
    private String trelloUsername;
    @Autowired
    private RestTemplate restTemplate;

    private URI getTrelloBoardsUrl() {
        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/"  + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name, id").build().encode().toUri();
    }
    /**Implementation of sending a request to the Trello API.*/
    public List<TrelloBoardDto> getTrelloBoards() {
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(getTrelloBoardsUrl(), TrelloBoardDto[].class);

        return Arrays.asList(Optional.ofNullable(boardsResponse)
                .orElse(new TrelloBoardDto[]{}));
    }
}
