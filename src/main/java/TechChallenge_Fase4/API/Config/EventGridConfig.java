package TechChallenge_Fase4.API.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.messaging.eventgrid.EventGridEvent;
import com.azure.messaging.eventgrid.EventGridPublisherClient;
import com.azure.messaging.eventgrid.EventGridPublisherClientBuilder;

@Configuration
public class EventGridConfig {

    @Bean
    public EventGridPublisherClient<EventGridEvent> eventGridClient() {

    	String key = System.getenv("EVENTGRID_TOPIC_KEY");

    	if (key == null || key.isBlank()) {
    	    throw new IllegalStateException("EVENTGRID_TOPIC_KEY não definida");
    	}
    	
        return new EventGridPublisherClientBuilder()
                .endpoint("https://function-event.westus2-1.eventgrid.azure.net/api/events")
                .credential(new AzureKeyCredential(key))
                .buildEventGridEventPublisherClient();
    }
}