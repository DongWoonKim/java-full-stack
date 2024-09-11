package notice;

public class SignInResponseDTO {

    private boolean status;
    private String userName;


    public SignInResponseDTO(boolean status, String userName) {
        this.status = status;
        this.userName = userName;
    }

    public boolean isStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}
