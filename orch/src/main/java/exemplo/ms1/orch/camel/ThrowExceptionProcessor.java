package exemplo.ms1.orch.camel;


import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

@Slf4j
public class ThrowExceptionProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        var exception = new Exception("Alguma coisa caiu aê");
        exchange.setException(exception);
        log.info("!---- deu ruim");
    }
}
