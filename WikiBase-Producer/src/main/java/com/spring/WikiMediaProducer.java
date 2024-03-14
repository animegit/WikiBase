    package com.spring;

    import com.launchdarkly.eventsource.EventSource;
    import com.launchdarkly.eventsource.StreamException;
    import com.launchdarkly.eventsource.background.BackgroundEventHandler;
    import com.launchdarkly.eventsource.background.BackgroundEventSource;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.kafka.core.KafkaTemplate;
    import org.springframework.stereotype.Service;

    import java.net.URI;
    import java.util.concurrent.TimeUnit;

    @Service
    public class WikiMediaProducer {

        private static final Logger log= LoggerFactory.getLogger(WikiMediaProducer.class);
        public KafkaTemplate<String,String> kafkaTemplate;

        public void send() throws InterruptedException, StreamException {
            String topic="wikimedia";
            BackgroundEventHandler eventHandler =  new WikiMediaChangesHandler(kafkaTemplate,topic);
    String url="https://stream.wikimedia.org/v2/stream/recentchange";
            URI uriUrl = URI.create(url);

            EventSource.Builder esBuilder = new EventSource.Builder(uriUrl);

            BackgroundEventSource.Builder eventSource = new BackgroundEventSource.Builder(eventHandler,esBuilder);

            BackgroundEventSource source = eventSource.build();

            source.start();

            TimeUnit.MINUTES.sleep(10);
        }
    }
