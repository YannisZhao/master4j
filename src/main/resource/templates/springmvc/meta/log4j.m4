#定义3个输出端
log4j.rootCategory=INFO,A1,A2
#Spring MVC 全局异常
log4j.logger.org.springframework.web.servlet.handler.SimpleMappingExceptionResolver=WARN
#定义A1输出到控制器
log4j.appender.A1=org.apache.log4j.ConsoleAppender
#定义A1的布局模式为PaternLayout
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
#定义A1的输出格式
log4j.appender.A1.layout.ConversionPattern=%4p[%t](%F:%L) - %m%n

#定义A2输出到文件
log4j.appender.A2=org.apache.log4j.RollingFileAppender
#定义A2输出到哪个文件
log4j.appender.A2.File=zkorder.log
#定义A2输出文件的最大长度
log4j.appender.A2.MaxFileSize=1KB
#定义A2的备份文件数
log4j.appender.A2.MaxBackupIndex=3
#定义A2的布局模式为PatternLayout
log4j.appender.A2.layout=org.apache.log4j.PatternLayout
#定义A2的输出模式
log4j.appender.A2.layout.ConversionPattern=%d{yyyy-MM--dd hh:mm:ss}:%p %t %c - %m%n

#定义A3输出到数据库
#log4j.appender.A3=org.apache.log4j.jdbc.JDBCAppender
#log4j.appender.A3.URL=jdbc:mysql://localhost:3306/study
#log4j.appender.A3.driver=com.mysql.jdbc.Driver
#log4j.appender.A3.user=root
#log4j.appender.A3.password=root
#log4j.appender.A3.layout=org.apache.log4j.PatternLayout
#log4j.appender.A3.layout.ConversionPattern=INSERT INTO log4j(createDate,thread,level,class,message) values(\'%d\',\'%t\',\'%-5p\',\'%c\',\'%m\')

#JdbcTemplate打印SQL语句
log4j.logger.org.springframework.jdbc.core.JdbcTemplate=debug