package com.crud.tasks.trello.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.badges.AttachmentsByType;
import com.crud.tasks.domain.badges.Badges;
import com.crud.tasks.domain.badges.Trello;
import com.crud.tasks.trello.config.TrelloConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {
    @InjectMocks
    private TrelloClient trelloClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloUsername()).thenReturn("test_user");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        final TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        final URI uri = new URI("http://test.com/members/test_user/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //When
        final List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }
    @Test
    public void shouldCreateCard() throws URISyntaxException {
        //Given
        final TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        final URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        final CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com",
                new Badges(1, new AttachmentsByType(new Trello(1, 1)))
        );
        when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class)).thenReturn(createdTrelloCardDto);
        //When
        final CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
        assertEquals(1, newCard.getBadges().getVotes());
        assertEquals(1, newCard.getBadges().getAttachmentsByType().getTrello().getBoard());
        assertEquals(1, newCard.getBadges().getAttachmentsByType().getTrello().getCard());
    }
    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI uri = new URI("http://test.com/members/test_user/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
        //When
        final List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
        //Then
        assertEquals(0, fetchedTrelloBoards.size());
    }
}