package sample.events;

import javafx.event.Event;
import javafx.event.EventHandler;

public class EventoLoteria implements EventHandler {
    int c=0;
    @Override
    public void handle(Event event) {
        System.out.println("Mi primer evento Fovifest desde otra clase :)");
        if(c<5){

        }

    }
}
