package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

    //добавляем новые методы в контроллер
    //тут аннотация @PathVariable связывает значение из URL с переменной в методе.
    //в нашем случае с {timezone}

    @GetMapping("/api/v1/time/zone/{timezone}")
    public String timeByZone(@PathVariable String timezone) {
        ZoneId zone = ZoneId.of(timezone);
        return LocalDateTime.now(zone).format(formatter);
    }

    @GetMapping("/api/v1/time/zone/{hours}")
    public String offsetTime(@PathVariable Integer hours) {
        ZonedDateTime zdt = ZonedDateTime.now().plusHours(hours);
        return zdt.format(formatter);
    }

    //Метод возвращает время в указанной временной зоне в заданном формате
    @GetMapping("/api/v1/time/format/")
    public String formatTime(

            //  Если вам нужно извлечь данные из строки запроса
            //  , используйте аннотацию @RequestParam.
            //  Параметры передаются в URL после знака ? в формате ключ=значение.
            //  Например, /products?category=books.

            @RequestParam(name = "zone") String zone,
            //Если параметр не указан в URL, будет использоваться значение по умолчанию.(минуты часы и тд)
            @RequestParam(required = false, defaultValue = "HH:mm:ss dd.MM.yyyy") String pattern
    ) {
        ZoneId z = ZoneId.of(zone);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.now(z).format(fmt);
    }

    @GetMapping("/api/v1/time/difference")
    public String differenceBetweenZones(
            @RequestParam(name = "from") String fromZone,
            @RequestParam(name = "to") String toZone
    ) {
        ZoneId from = ZoneId.of(fromZone);
        ZoneId to = ZoneId.of(toZone);
        Duration duration = Duration.between(LocalDateTime.now(from), LocalDateTime.now(to));
        return "Разница между " + fromZone + " и " + toZone + Math.abs(duration.toHours()) + " часов ";
    }

    @GetMapping("/api/v1/time/convert")
    public String convertTime(
            @RequestParam(name = "time") String inputTime,
            @RequestParam(name = "fromZone") String fromZone,
            @RequestParam(name = "toZone") String toZone
    ) {
        return "";
    }
}