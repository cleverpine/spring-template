package com.cleverpine.template.config;

import com.cleverpine.specification.parser.SingleFilterParser;
import com.cleverpine.specification.parser.SingleSortParser;
import com.cleverpine.specification.parser.SpecificationParserManager;
import com.cleverpine.specification.parser.json.FilterJsonArrayParser;
import com.cleverpine.specification.parser.json.SortJsonArrayParser;
import com.cleverpine.specification.parser.separator.FilterSeparatorBasedParser;
import com.cleverpine.specification.parser.separator.SortSeparatorBasedParser;
import com.cleverpine.specification.producer.SimpleSpecificationProducer;
import com.cleverpine.specification.util.ValueConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaSpecificationConfig {

    private static final String SEPARATOR = ":";
    private static final String VALUES_SEPARATOR = ";";

    private final ObjectMapper objectMapper;

    @Bean
    public ValueConverter valueConverter() {
        return new ValueConverter();
    }

    @Bean
    public SingleFilterParser filterJsonArrayParser() {
        return new FilterJsonArrayParser(objectMapper);
    }

    @Bean
    public SingleSortParser sortJsonArrayParser() {
        return new SortJsonArrayParser(objectMapper);
    }

    @Bean
    public SimpleSpecificationProducer simpleSpecificationProducer() {
        return new SimpleSpecificationProducer();
    }

    @Bean
    public SpecificationParserManager specificationParserManager() {
        FilterSeparatorBasedParser filterParser = new FilterSeparatorBasedParser(SEPARATOR, VALUES_SEPARATOR);
        SortSeparatorBasedParser sortParser = new SortSeparatorBasedParser(SEPARATOR);

        return SpecificationParserManager.builder()
                .withMultipleFilterParser(filterParser)
                .withMultipleSortParser(sortParser)
                .build();
    }

}
