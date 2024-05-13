package edu.bu.tests;

import static org.mockito.Mockito.*;

import edu.bu.controller.GameController;
import edu.bu.model.Room;
import edu.bu.model.entitities.Player;
import edu.bu.model.persistence.PlayerSaveService;
import edu.bu.view.TextView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameControllerTest {
    private GameController gameController;
    private TextView viewMock;
    private Player playerMock;
    private Room roomMock;
    private PlayerSaveService pssMock;

    @BeforeEach
    void setUp() {
        viewMock = mock(TextView.class);
        playerMock = mock(Player.class);
        roomMock = mock(Room.class);
        pssMock = mock(PlayerSaveService.class);
        gameController = new GameController(viewMock, playerMock, roomMock, pssMock);
    }

    @Test
    void testDisplayMessage() {
        String testMessage = "Test message";
        gameController.displayMessage(testMessage);
        verify(viewMock).displayMessage(testMessage);
    }

}
