package ca.pitt.camel.ce.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import ca.pitt.camel.ce.dto.CloudEventDTO;


public class TimerToCloudEventsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Consume book CSV files
        from("timer://cetimer?fixedRate=true&period=5000")
            .process(new Processor(){
                public void process(Exchange exchange) throws Exception {
                    CloudEventDTO ce = new CloudEventDTO();
                    exchange.getIn().setBody(ce);
                }
            })
            .log("File contents: ${body}")
            .to("direct:postcloudevents");


        from("direct:postcloudevents")
            .setHeader("CamelHttpMethod", constant("POST"))
            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
            .setHeader("Ce-Id", constant("camel-timer"))
            .setHeader("Ce-Specversion", constant("1.0"))
            .setHeader("Ce-Type", constant("counter"))
            .setHeader("Ce-source", constant("Fuse"))
            .marshal().json(JsonLibrary.Jackson)
            .to("{{K_SINK}}");
    }
    
}
