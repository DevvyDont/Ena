package sh.niall.ena.utils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HumanUtils {

    /**
     * Converts seconds into a more human readable format.
     * 90 seconds becomes 1 minute, 30 seconds.
     * @param duration The duration to convert.
     * @return The human readable conversion.
     */
    public static String secondsToHumanReadable(long duration) {
        int day = (int) TimeUnit.SECONDS.toDays(duration);
        long hours = TimeUnit.SECONDS.toHours(duration) - (day *24);
        long minute = TimeUnit.SECONDS.toMinutes(duration) - (TimeUnit.SECONDS.toHours(duration) * 60);
        long second = TimeUnit.SECONDS.toSeconds(duration) - (TimeUnit.SECONDS.toMinutes(duration) * 60);
        return Arrays.stream(new String[] {
                day == 0 ? null : day + plural(day, " day", " days"),
                hours == 0 ? null : hours + plural(hours, " hour", " hours"),
                minute == 0 ? null : minute + plural(minute, " minute", " minutes"),
                second == 0 ? null : second + plural(second, " second", " seconds"),
        }).filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining(", "));
    }

    public static String plural(long amount, String singular, String multiple) {
        return amount == 1 ? singular : multiple;
    }
}
