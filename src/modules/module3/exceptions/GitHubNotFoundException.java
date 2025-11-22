package modules.module3.exceptions;

public class GitHubNotFoundException extends RuntimeException {
    public GitHubNotFoundException(String message) {
        super(message);
    }
}
