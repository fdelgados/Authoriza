package com.authoriza.shared.infrastructure.hibernate;

import com.authoriza.shared.domain.service.Service;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HibernateConfigurationFactory {
    private static final String MAPPING_FILES_SUBDIRS = "/infrastructure/persistence/hibernate/mapping/";

    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());

        return transactionManager;
    }

    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, String contextName, String packageName) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        List<Resource> mappingFiles = searchMappingFiles(contextName, packageName);

        sessionFactory.setMappingLocations(mappingFiles.toArray(new Resource[mappingFiles.size()]));

        return sessionFactory;
    }

    public DataSource dataSource(String driver, String url, String username, String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private List<Resource> searchMappingFiles(String contextName, String packageName) {
        List<String> modules = findModulesPaths(contextName, packageName);
        List<String> goodPaths = new ArrayList<>();

        for (String module : modules) {
            String[] files = mappingFilesIn(String.format("%s%s", module, MAPPING_FILES_SUBDIRS));

            for (String file : files) {
                goodPaths.add(String.format("%s%s%s", module, MAPPING_FILES_SUBDIRS, file));
            }
        }

        return goodPaths.stream().map(FileSystemResource::new).collect(Collectors.toList());
    }

    private List<String> findModulesPaths(String contextName, String packageName) {
        String path = String.format("./src/%s/main/java/com/%s/%s/", contextName, packageName, contextName);
        String[] files = new File(path).list((current, name) -> new File(current, name).isDirectory());

        if (null == files) {
            path  = String.format("./main/java/com/%s/%s/", packageName, contextName);
            files = new File(path).list((current, name) -> new File(current, name).isDirectory());
        }

        if (null == files) {
            return Collections.emptyList();
        }

        String finalPath = path;

        return Arrays.stream(files).map(file -> finalPath + file).collect(Collectors.toList());
    }

    private String[] mappingFilesIn(String path) {
        String[] files = new File(path).list((current, name) -> new File(current, name).getName().contains(".hbm.xml"));

        if (null == files) {
            return new String[0];
        }

        return files;
    }
}
