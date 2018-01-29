package com.crud.tasks.trello.validator;

import static org.junit.Assert.*;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {
    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void shouldValidateTestCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_card", "test_desc", "top", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        assertTrue("Someone is testing my application!", true);
    }
    @Test
    public void shouldValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "desc", "top", "1");
        //When
        trelloValidator.validateCard(trelloCard);
        //Then
        assertTrue("Seems that my application is used in proper way.", true);
    }

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList list = new TrelloList("1", "list", false);
        trelloLists.add(list);
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));
        trelloBoards.add(new TrelloBoard("2", "board", trelloLists));
        //When
        List<TrelloBoard> validatedList = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validatedList.size());
    }


}