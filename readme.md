## 搭建方法
### git clone到本地
### 配置数据库
在 `src/main/resources` 下添加 `application.yml` 文件，按如下格式配置数据库
```yaml
spring:
  application:
    name: CourseSchedule
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: url
    username: username
    password: passwd
logging:
  level:
    root: info
```
## 已知问题
前端不支持修改课程信息
解决方案：删除相关课程后通过文件导入

## 相关
可搭配 [CourseScheduleQQAdvice](https://github.com/mashirot/CourseScheduleQQAdvice) QQ通知插件使用，基于Mirai