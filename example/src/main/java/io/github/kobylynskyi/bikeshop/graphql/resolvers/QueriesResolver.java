package io.github.kobylynskyi.bikeshop.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import io.github.kobylynskyi.bikeshop.graphql.api.Query;
import io.github.kobylynskyi.bikeshop.graphql.mappers.BikeMapper;
import io.github.kobylynskyi.bikeshop.graphql.model.BikeTO;
import io.github.kobylynskyi.bikeshop.graphql.model.BikeTypeTO;
import io.github.kobylynskyi.bikeshop.model.BikeType;
import io.github.kobylynskyi.bikeshop.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Component
public class QueriesResolver implements Query, GraphQLQueryResolver {

    @Autowired
    private BikeService service;
    @Autowired
    private BikeMapper mapper;


    @Override
    public Collection<BikeTO> bikes(Integer first, Integer offset, DataFetchingEnvironment env) throws Exception {
        return service.findAll().stream()
                .map(mapper::map)
                .collect(toList());
    }

    @Override
    public Collection<BikeTO> bikesByType(BikeTypeTO type, DataFetchingEnvironment env) throws Exception {
        BikeType bikeType = mapper.mapInputType(type);
        return service.findByType(bikeType).stream()
                .map(mapper::map)
                .collect(toList());
    }
}
