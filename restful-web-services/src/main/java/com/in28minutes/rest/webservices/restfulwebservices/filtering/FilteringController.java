package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean retireveSomeBean() {
        return new SomeBean("val1", "val2", "val3");
    }

    //filter out val3 - DO NOT send in response
    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue retireveSomeBeanDymanic() {
        SomeBean someBean = new SomeBean("val1", "val2", "val3");
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("val1", "val2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter1", filter);
        mapping.setFilters(filters);
        return mapping;
    }

    //filter out val1 - DO NOT send in response
    @GetMapping("/filtering-dynamic-list")
    public MappingJacksonValue retireveSomeBeanListDymanic() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("val1", "val2", "val3"), new SomeBean("val11", "val22", "val33"));

        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("val2", "val3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter1", filter);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> retireveSomeBeanList() {
        return Arrays.asList(new SomeBean("val1", "val2", "val3"), new SomeBean("val11", "val22", "val33"));
    }
}
