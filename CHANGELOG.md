# ChangeLog

### Release_1.0.7_20230430_build_A

#### 功能构建

- Dubbo 微服务增加分组配置。

- 启停脚本优化。
  - 优化 Windows 系统的启动脚本。
  - 优化 Linux 系统的启停脚本。

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `31.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `2.0`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。
  - 增加依赖 `javax.servlet-api` 以规避漏洞，版本为 `4.0.1`。
  - 增加依赖 `jboss-logging` 以规避漏洞，版本为 `3.4.3.Final`。

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.21` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.86.Final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.11.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.3.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.11.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.6.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `el` 依赖。
  - 删除 `zkclient` 依赖。
  - 删除 `commons-net` 依赖。
  - 删除 `dozer` 依赖。
  - 删除 `aopalliance` 依赖。

---

### Release_1.0.6_20220706_build_A

#### 功能构建

- 依赖升级。
  - 升级 `junit` 依赖版本为 `4.13.2` 以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.20` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.28` 以规避漏洞。
  - 升级 `fastjson` 依赖版本为 `1.2.83` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.15` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.77.Final` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.3.20.Final` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.0.21.Final` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.17.2` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.0.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.7.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.8.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.8.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.3.a` 以规避漏洞。

#### Bug修复

- 修复 ItemFileInfo 删除时不能级联删除相关文件的 bug。
- 修复 ItemCoverInfo 删除时不能级联删除相关文件的 bug。

#### 功能移除

- 删除不需要的依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `commons-io` 依赖。
  - 删除 `httpcomponents` 依赖。

---

### Release_1.0.5_20220323_build_A

#### 功能构建

- 优化操作服务验证环节的代码结构。

#### Bug修复

- 修复部分操作服务验证时权限要求过高的 bug。

#### 功能移除

- (无)

---

### Release_1.0.4_20220302_build_A

#### 功能构建

- 优化 Item 相关的 DTO。
- 优化 Item 操作服务的部分方法的流程。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.3_20220209_build_A

#### 功能构建

- (无)

#### Bug修复

- 修复了资产目录实体删除时有问题的级联逻辑。

#### 功能移除

- (无)

---

### Release_1.0.2_20220206_build_A

#### 功能构建

- (无)

#### Bug修复

- 修复 ItemTypeIndicatorMaintainService 实现类的错误路径。

#### 功能移除

- (无)

---

### Release_1.0.1_20220205_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正预设中不合理的配置项。

- 修正预设中不合理的配置格式。

#### 功能移除

- (无)

---

### Release_1.0.0_20220130_build_A

#### 功能构建

- 项目结构建立，程序清理测试通过。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.Item。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemTypeIndicator。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.User。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfoDao。
  - com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfoDao。

- 完成 node 模块，打包测试及启动测试通过。

- 完成实体的操作服务。
  - com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogOperateService。
  - com.dwarfeng.familyhelper.assets.stack.service.ItemOperateService。
  - com.dwarfeng.familyhelper.assets.stack.service.ItemCoverOperateService。
  - com.dwarfeng.familyhelper.assets.stack.service.ItemFileOperateService。

- 为 `dubbo` 增加超时时间的配置选项。

#### Bug修复

- (无)

#### 功能移除

- (无)
