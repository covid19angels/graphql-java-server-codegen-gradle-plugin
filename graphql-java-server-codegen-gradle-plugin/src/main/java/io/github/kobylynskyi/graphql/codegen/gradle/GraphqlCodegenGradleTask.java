package io.github.kobylynskyi.graphql.codegen.gradle;

import com.kobylynskyi.graphql.codegen.GraphqlCodegen;
import com.kobylynskyi.graphql.codegen.model.MappingConfig;
import com.kobylynskyi.graphql.codegen.supplier.JsonMappingConfigSupplier;
import com.kobylynskyi.graphql.codegen.supplier.MappingConfigSupplier;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Optional;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gradle task for GraphQL code generation
 *
 * @author kobylynskyi
 */
public class GraphqlCodegenGradleTask extends DefaultTask {

    private List<String> graphqlSchemaPaths;
    private File outputDir;
    private Map<String, String> customTypesMapping = new HashMap<>();
    private Map<String, String> customAnnotationsMapping = new HashMap<>();
    private String packageName;
    private String apiPackageName;
    private List<String> apiPackageImports;
    private String modelPackageName;
    private String resolverPackageName;
    private List<String> modelPackageImports;
    private String modelNamePrefix;
    private String modelNameSuffix;
    private String subscriptionReturnType;
    private Boolean generateApis = true;
    private String modelValidationAnnotation;
    private Boolean generateEqualsAndHashCode = false;
    private Boolean generateToString = false;
    private String jsonConfigurationFile;

    @TaskAction
    public void generate() throws Exception {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.setPackageName(packageName);
        mappingConfig.setCustomTypesMapping(customTypesMapping);
        mappingConfig.setModelNamePrefix(modelNamePrefix);
        mappingConfig.setModelNameSuffix(modelNameSuffix);
        mappingConfig.setApiPackageName(apiPackageName);
        mappingConfig.setModelPackageName(modelPackageName);
        mappingConfig.setResolverPackageName(resolverPackageName);
        mappingConfig.setGenerateApis(generateApis);
        mappingConfig.setModelValidationAnnotation(modelValidationAnnotation);
        mappingConfig.setSubscriptionReturnType(subscriptionReturnType);
        mappingConfig.setCustomAnnotationsMapping(customAnnotationsMapping);
        mappingConfig.setGenerateEqualsAndHashCode(generateEqualsAndHashCode);
        mappingConfig.setGenerateToString(generateToString);
        mappingConfig.setApiPackageImports(apiPackageImports);
        mappingConfig.setModelPackageImports(modelPackageImports);

        new GraphqlCodegen(graphqlSchemaPaths, outputDir, mappingConfig, buildJsonSupplier()).generate();
    }

    private MappingConfigSupplier buildJsonSupplier() {
        if (jsonConfigurationFile != null && !jsonConfigurationFile.isEmpty()) {
            return new JsonMappingConfigSupplier(jsonConfigurationFile);
        }
        return null;
    }

    @Input
    public List<String> getGraphqlSchemaPaths() {
        return graphqlSchemaPaths;
    }

    public void setGraphqlSchemaPaths(List<String> graphqlSchemaPaths) {
        this.graphqlSchemaPaths = graphqlSchemaPaths;
    }

    @OutputDirectory
    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    @Input
    @Optional
    public Map<String, String> getCustomTypesMapping() {
        return customTypesMapping;
    }

    public void setCustomTypesMapping(Map<String, String> customTypesMapping) {
        this.customTypesMapping = customTypesMapping;
    }

    @Input
    @Optional
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Input
    @Optional
    public String getModelNamePrefix() {
        return modelNamePrefix;
    }

    public void setModelNameSuffix(String modelNameSuffix) {
        this.modelNameSuffix = modelNameSuffix;
    }

    @Input
    @Optional
    public String getModelNameSuffix() {
        return modelNameSuffix;
    }

    public void setModelNamePrefix(String modelNamePrefix) {
        this.modelNamePrefix = modelNamePrefix;
    }

    @Input
    @Optional
    public String getApiPackageName() {
        return apiPackageName;
    }

    public void setApiPackageName(String apiPackageName) {
        this.apiPackageName = apiPackageName;
    }

    @Input
    @Optional
    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    @Input
    @Optional
    public String getResolverPackageName() {
        return resolverPackageName;
    }

    public void setResolverPackageName(String resolverPackageName) {
        this.resolverPackageName = resolverPackageName;
    }

    @Input
    @Optional
    public Boolean getGenerateApis() {
        return generateApis;
    }

    public void setGenerateApis(Boolean generateApis) {
        this.generateApis = generateApis;
    }

    @Input
    @Optional
    public String getModelValidationAnnotation() {
        return modelValidationAnnotation;
    }

    public void setModelValidationAnnotation(String modelValidationAnnotation) {
        this.modelValidationAnnotation = modelValidationAnnotation;
    }

    @Input
    @Optional
    public Map<String, String> getCustomAnnotationsMapping() {
        return customAnnotationsMapping;
    }

    public void setCustomAnnotationsMapping(Map<String, String> customAnnotationsMapping) {
        this.customAnnotationsMapping = customAnnotationsMapping;
    }

    @Input
    public boolean getGenerateEqualsAndHashCode() {
        return generateEqualsAndHashCode;
    }

    public void setGenerateEqualsAndHashCode(Boolean generateEqualsAndHashCode) {
        this.generateEqualsAndHashCode = generateEqualsAndHashCode;
    }

    @Input
    @Optional
    public Boolean getGenerateToString() {
        return generateToString;
    }

    public void setGenerateToString(Boolean generateToString) {
        this.generateToString = generateToString;
    }

    @Input
    @Optional
    public String getSubscriptionReturnType() {
        return subscriptionReturnType;
    }

    public void setSubscriptionReturnType(String subscriptionReturnType) {
        this.subscriptionReturnType = subscriptionReturnType;
    }

    @Input
    @Optional
    public String getJsonConfigurationFile() {
        return jsonConfigurationFile;
    }

    public void setJsonConfigurationFile(String jsonConfigurationFile) {
        this.jsonConfigurationFile = jsonConfigurationFile;
    }

    @Input
    @Optional
    public List<String> getApiPackageImports() {
        return apiPackageImports;
    }

    public void setApiPackageImports(List<String> apiPackageImports) {
        this.apiPackageImports = apiPackageImports;
    }

    @Input
    @Optional
    public List<String> getModelPackageImports() {
        return modelPackageImports;
    }

    public void setModelPackageImports(List<String> modelPackageImports) {
        this.modelPackageImports = modelPackageImports;
    }
}
