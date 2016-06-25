package Helper;


public interface JsonHelper {

    String toJson(Object response);

    <T> T fromJson(String request, Class<T> classOfT);
}
