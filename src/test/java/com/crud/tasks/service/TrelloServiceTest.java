package com.crud.tasks.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertEquals("1", trelloBoardDtos.get(0).getId());
    }

    @Test
    public void shouldCreateTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("1", "test_card", "http://test.com/", null);
        TrelloCardDto trelloCard = new TrelloCardDto("test_card", "test_content", "top", "1");

        when(trelloClient.createNewCard(trelloCard)).thenReturn(createdTrelloCard);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCard);
        //Then
        assertEquals("test_card", createdTrelloCardDto.getName());
    }
}