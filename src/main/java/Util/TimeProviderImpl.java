package Util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class TimeProviderImpl implements ITimeProvider {

    public Long getCurrentTime() {

        long result = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return result;
    }
}
