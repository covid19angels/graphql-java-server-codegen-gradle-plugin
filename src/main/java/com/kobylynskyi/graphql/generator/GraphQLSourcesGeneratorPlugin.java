package com.kobylynskyi.graphql.generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Gradle plugin for GraphQL code generation
 *
 * @author kobylynskyi
 */
public class GraphQLSourcesGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getTasks().create("generateGraphQLClassesTask", GraphQLSourcesGeneratorTask.class);
    }

}
