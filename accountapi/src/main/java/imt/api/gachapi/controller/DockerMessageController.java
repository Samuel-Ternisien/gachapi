@RestController
public class DockerMessageController {
    @GetMapping("/messages")
    public String getMessage() {
        return "Hello from Docker!";
    }
}