package pl.sda.spring.springExercise2.errorHandling;

public class SDAException extends RuntimeException {
    public SDAException(){
        super();
    }
    public SDAException(String message){
        super(message);
    }
}
