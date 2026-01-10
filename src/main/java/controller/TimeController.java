package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


//@RestController: Класс обозначается как контроллер,
// отвечающий за обработку запросов и выдачу результатов в формате JSON.
@RestController
public class TimeController {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");


    //@GetMapping("/api/v1/time/london"):
    // По адресу /api/v1/time/london отправляется строка с временем.
    @GetMapping("/api/v1/time/london")
    public String londonTime() {
        return LocalDateTime.now(ZoneId.of("Europe/London")).format(formatter);
    }

    @GetMapping("/api/v1/time/moscow")
    public String moscowTime() {
        return LocalDateTime.now(ZoneId.of("Europe/Moscow")).format(formatter);
    }

    @GetMapping("/api/v1/time/utc")
    public String utcTime() {
        return LocalDateTime.now(ZoneId.of("UTC")).format(formatter);
    }

    @GetMapping("/api/v1/time/uptime")
    public String upTime() {
        long SecondsAfterStart = System.currentTimeMillis() / 1000;
        long MinsAfterStart = System.currentTimeMillis() / 60;

        return "Приложение работает: " + MinsAfterStart + " мин " + SecondsAfterStart + " сек";
    }

}