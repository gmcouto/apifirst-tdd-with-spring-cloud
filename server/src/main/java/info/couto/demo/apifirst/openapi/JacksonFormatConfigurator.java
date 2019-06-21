package info.couto.demo.apifirst.openapi;

import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import java.time.format.DateTimeFormatterBuilder;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
public class JacksonFormatConfigurator implements Jackson2ObjectMapperBuilderCustomizer {
  private static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

  @Override
  public void customize(final Jackson2ObjectMapperBuilder builder) {
    final OffsetDateTimeSerializer mySerial =
        new OffsetDateTimeSerializer() {
          OffsetDateTimeSerializer myFormat() {
            return (OffsetDateTimeSerializer)
                withFormat(
                    false,
                    new DateTimeFormatterBuilder().appendPattern(DATETIME_FORMAT).toFormatter(),
                    _shape);
          }
        }.myFormat();
    builder.serializers(mySerial);
  }
}
