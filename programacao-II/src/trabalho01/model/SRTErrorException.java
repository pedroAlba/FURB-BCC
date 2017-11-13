package trabalho01.model;

import java.text.ParseException;

public class SRTErrorException extends ParseException {

    public SRTErrorException(String s) {
        super(s, 0);
    }

}
