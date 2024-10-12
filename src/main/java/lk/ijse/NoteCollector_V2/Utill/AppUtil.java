package lk.ijse.NoteCollector_V2.Utill;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String genereteNoteId(){
        return "NOTE-"+UUID.randomUUID();
    }

    public static String genereteUserId() {
        return "USER-"+UUID.randomUUID();
    }
    public static String profilePickToBase64(byte[] profilePic){
        return Base64.getEncoder().encodeToString(profilePic);
    }
}
