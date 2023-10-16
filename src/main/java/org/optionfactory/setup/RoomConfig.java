package org.optionfactory.setup;

import org.optionfactory.room.RoomFacade;
import org.optionfactory.room.RoomRepository;
import org.optionfactory.room.TransactionalRoomFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomConfig {

    @Bean
    public RoomFacade roomFacade(RoomRepository roomRepository) {
        return new TransactionalRoomFacade(roomRepository);
    }
}
