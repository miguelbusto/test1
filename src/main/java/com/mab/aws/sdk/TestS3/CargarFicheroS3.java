package com.mab.aws.sdk.TestS3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import jakarta.annotation.PostConstruct;

@Configuration
public class CargarFicheroS3 {

    @PostConstruct
    public void test() {
        
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
        .withCredentials(new ProfileCredentialsProvider("mabusto"))
        .withRegion(Regions.EU_NORTH_1).build();
        try {
            System.out.println(new File(".").getAbsolutePath());
            String key_name="prueba1.rtf";
            S3Object o = s3.getObject("xsdert", key_name);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(key_name));
           
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
            System.out.println("FIN OK");
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

}
