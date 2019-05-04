package us.codecraft.tinyioc;

/**
 * @author yihua.huang@dianping.com
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private us.codecraft.tinyioc.OutputService outputService;

    @Override
    public void helloWorld(){
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(us.codecraft.tinyioc.OutputService outputService) {
        this.outputService = outputService;
    }
}
