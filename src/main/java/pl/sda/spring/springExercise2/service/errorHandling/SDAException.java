package pl.sda.spring.springExercise2.service.errorHandling;

public class SDAException extends RuntimeException {
    public SDAException(){
        super();
    }
    public SDAException(String message){
        super(message);
    }
}
