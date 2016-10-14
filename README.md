# master4j
Auto code generator for java

# What can we do with master4j v0.9

1. First of all it will build the basic project architecture.
2. The major of this version is try to generate commonly java web stuff(Controller,Service,Dao,etc.)

# How to use

The project is easy to interaction, all you need to do is configure some basic information, and this is show in AppTest.java as a demo.
You can code like this:
```java
Motor motor = new Motor();
motor.setClassGeneratorFactory(new SpringMVCGeneratorFactory());
motor.setConfigGeneratorFactory(new ConfigGeneratorFactory());
motor.setViewGeneratorFactory(new ViewGeneratorFactory());

ProjectConfig projectConfig = new ProjectConfig();

/* configure database information */
DBConfig dbConfig = new DBConfig();
dbConfig.setDatabase("uaas");
dbConfig.setIp("127.0.0.1");
dbConfig.setDriverPackage("com.mysql.jdbc.Driver");
dbConfig.setUsername("root");
dbConfig.setPassword("111111");
dbConfig.setExcludeTables(Arrays.asList("sys_resource","sys_role","sys_user","user_group"));
projectConfig.setDbConfig(dbConfig);

/* configure project & output path information */
projectConfig.setOutputPath("/home/yannis/Development");
projectConfig.setProjectName("uaas");
projectConfig.setBasePackageName("org.yannis.uaas");
projectConfig.setDirConfig(new DirConfig(projectConfig.getOutputPath(),projectConfig.getProjectName(),projectConfig.getBasePackageName()));

/* run the app */
motor.fire(projectConfig);
```

# What should be improved in next version

1. Continue to add new function such as generate the TEST file, validation and so on.
2. Improve the LAF with whole generated file.
