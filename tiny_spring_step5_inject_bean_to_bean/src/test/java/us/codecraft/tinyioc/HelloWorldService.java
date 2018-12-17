package us.codecraft.tinyioc;

public class HelloWorldService {
    private String text;

    private OutputService outputService;

    public void helloWorld() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutoutService(OutputService outputService) {
        this.outputService = outputService;
    }
}
