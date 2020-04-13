package com.pluralsight.conferencedemo.configuration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.PathResource;
import org.springframework.batch.item.ItemWriter;
import com.pluralsight.conferencedemo.models.batch.PatientEntity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import com.pluralsight.conferencedemo.models.batch.PatientRecord;
import com.pluralsight.conferencedemo.repositories.PatientRepository;
import com.pluralsight.conferencedemo.utilities.batch.Constants;

import org.springframework.batch.item.database.JpaItemWriter;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
@EnableBatchProcessing
public class BatchJobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public ApplicationProperties applicationProperties;
    
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public EntityManagerFactory entityManagerFactory;
    
    /*
     * The goal of this processor is to register jobs at runtime when they are created
     */
    @Bean
    JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
        JobRegistryBeanPostProcessor postProcessor = new JobRegistryBeanPostProcessor();
        postProcessor.setJobRegistry(jobRegistry);
        return postProcessor;
    }
    
    @Bean
    public Job job(Step step) throws Exception{
        return this.jobBuilderFactory
                .get(Constants.JOB_NAME)
                .validator(validator())
                .start(step)
                .build();
    	
    }
    
    
    /*
     * Here we are validating just the input parameters of our job
     * This validator must not contain BL that conerns that data we are importing 
     */
    @Bean
    public JobParametersValidator validator() {
        return new JobParametersValidator() {
            @Override
            public void validate(JobParameters parameters) throws JobParametersInvalidException {
                String fileName = parameters.getString(Constants.JOB_PARAM_FILE_NAME);
                if (StringUtils.isBlank(fileName)) {
                    throw new JobParametersInvalidException(
                		"The patient-batch-loader.fileName parameter is required.");
                }
                try {
                    Path file = Paths.get(applicationProperties.getBatch().getInputPath() + 
                		File.separator + fileName);
                    if (Files.notExists(file) || !Files.isReadable(file)) {
                        throw new Exception("File did not exist or was not readable");
                    }
                } catch (Exception e) {
                    throw new JobParametersInvalidException(
                        "The input path + patient-batch-loader.fileName parameter needs to " + 
                    		"be a valid file location.");
                }
            }
        };
    }
    
    /*
     * This will output hello world to test that our validator is working fine
    @Bean
	public Step step() throws Exception {
		return this.stepBuilderFactory
			.get(Constants.STEP_NAME)
			.tasklet(new Tasklet() {
				@Override
				public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) 
				    throws Exception {
					System.err.println("Hello World!");
					return RepeatStatus.FINISHED; 
				}
			})
			.build();
	}
	*/
    
    /* is used to test the reader only by passing a default processor
    @Bean
    public Step step(ItemReader<PatientRecord> itemReader) throws Exception {
        return this.stepBuilderFactory
            .get(Constants.STEP_NAME)
            .<PatientRecord, PatientRecord>chunk(2)
            .reader(itemReader)
            .processor(processor())
            .writer(writer())
            .build();
    }
    */
    
    
    /* we are using a reader and a processor with a stub out write 
    @Bean
    public Step step(ItemReader<PatientRecord> itemReader,
        Function<PatientRecord, PatientEntity> processor) throws Exception {
        return this.stepBuilderFactory
            .get(Constants.STEP_NAME)
            .<PatientRecord, PatientEntity>chunk(2)
            .reader(itemReader)
            .processor(processor)
            .writer(writer())
            .build();
    }
    */
    
    /*  */
    @Bean
    public Step step(ItemReader<PatientRecord> itemReader,
        Function<PatientRecord, PatientEntity> processor,
        JpaItemWriter<PatientEntity> writer) throws Exception {
        return this.stepBuilderFactory
            .get(Constants.STEP_NAME)
            .<PatientRecord, PatientEntity>chunk(2)
            .reader(itemReader)
            .processor(processor)
            .writer(writer)
            .build();
    }
   
    @Bean
    @StepScope //to be able to inject values at runtime into this step, like the fileName
    public FlatFileItemReader<PatientRecord> reader(
        @Value("#{jobParameters['" + Constants.JOB_PARAM_FILE_NAME + "']}")String fileName) {
        return new FlatFileItemReaderBuilder<PatientRecord>()
            .name(Constants.ITEM_READER_NAME)
            .resource(
                new PathResource(
                    Paths.get(applicationProperties.getBatch().getInputPath() +
                        File.separator + fileName)))
            .linesToSkip(1)
            .lineMapper(lineMapper())
            .build();
    }
    
    /*
    @Bean
    public LineMapper<PatientRecord> lineMapper() {
        DefaultLineMapper<PatientRecord> mapper = new DefaultLineMapper<>();
        
        mapper.setFieldSetMapper((fieldSet) -> new PatientRecord(
            fieldSet.readString(0), fieldSet.readString(1),
            fieldSet.readString(2), fieldSet.readString(3),
            fieldSet.readString(4), fieldSet.readString(5),
            fieldSet.readString(6), fieldSet.readString(7),
            fieldSet.readString(8), fieldSet.readString(9),
            fieldSet.readString(10), fieldSet.readString(11),
            fieldSet.readString(12)));
        mapper.setLineTokenizer(new DelimitedLineTokenizer());
        return mapper;
    }
    */
    
    @Bean
    public LineMapper<PatientRecord> lineMapper() {
    	System.out.println("Patient Record Line Mapper");
    	DefaultLineMapper<PatientRecord> mapper = new DefaultLineMapper<>();
    	mapper.setFieldSetMapper(new PatientFieldSetMapper());
    	mapper.setLineTokenizer(new DelimitedLineTokenizer());
    	return mapper;
    }
    
    public class PatientFieldSetMapper implements FieldSetMapper<PatientRecord> {
    	
        public PatientRecord mapFieldSet(FieldSet fieldSet)  {
        	System.out.println("Maapping Fields from excel to Patient Record");
            PatientRecord patientRecord = new PatientRecord(
                    fieldSet.readString(0), fieldSet.readString(1),
                    fieldSet.readString(2), fieldSet.readString(3),
                    fieldSet.readString(4), fieldSet.readString(5),
                    fieldSet.readString(6), fieldSet.readString(7),
                    fieldSet.readString(8), fieldSet.readString(9),
                    fieldSet.readString(10), fieldSet.readString(11),
                    fieldSet.readString(12));
            return patientRecord;
        }
    }
    
    //Creation a default processor to pass the input as is to the writer
    //to test the Reader standalone
    /*
    @Bean
    @StepScope
    public PassThroughItemProcessor<PatientRecord> processor() {
        return new PassThroughItemProcessor<>();
    }
	*/
    
    @Bean
    @StepScope
    public Function<PatientRecord, PatientEntity> processor() {
        return (patientRecord) ->  {
            return new PatientEntity(
                patientRecord.getSourceId(),
                patientRecord.getFirstName(),
                patientRecord.getMiddleInitial(),
                patientRecord.getLastName(),
                patientRecord.getEmailAddress(),
                patientRecord.getPhoneNumber(),
                patientRecord.getStreet(),
                patientRecord.getCity(),
                patientRecord.getState(),
                patientRecord.getZip(),
                LocalDate.parse(patientRecord.getBirthDate(), DateTimeFormatter.ofPattern("M/dd/yyyy")),
                patientRecord.getSsn());
        };
    }
    
    
    //using a default write to output a string to the console
    //we used it to test the reader in a standalone way
    /* 
    @Bean
    @StepScope
    public ItemWriter<PatientRecord> writer() {
        return new ItemWriter<PatientRecord>() {
            @Override
            public void write(List<? extends PatientRecord> items) throws Exception {
                for (PatientRecord patientRecord : items) {
                    System.err.println("Writing item: " + patientRecord.toString());
                }
            }
        };
    }
    */
    //using a default writer to output the patientEntity to the console
    /*  
    @Bean
    @StepScope
    public ItemWriter<PatientEntity> writer() {
        return new ItemWriter<PatientEntity>() {
            @Override
            public void write(List<? extends PatientEntity> items) throws Exception {
                for (PatientEntity patientEntity : items) {
                    System.err.println("Writing item: " + patientEntity.toString());
                }
            }
        };
    }
   */
    
    /* */
    @Bean
    @StepScope
    public JpaItemWriter<PatientEntity> writer() {
        JpaItemWriter<PatientEntity> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(this.entityManagerFactory);
        return writer;
    }
    
}
	