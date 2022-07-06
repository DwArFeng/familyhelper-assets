# ChangeLog

### Release_1.0.6_20220706_build_A

#### 功能构建

- (无)

#### Bug修复

- (无)

#### 功能移除

- (无)

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
