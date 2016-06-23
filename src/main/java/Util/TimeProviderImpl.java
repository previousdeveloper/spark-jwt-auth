package Util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.LocalDateTime.now;


public class TimeProviderImpl implements ITimeProvider {

    public Long getCurrentTime() {

        long result = now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return result;
    }
}
