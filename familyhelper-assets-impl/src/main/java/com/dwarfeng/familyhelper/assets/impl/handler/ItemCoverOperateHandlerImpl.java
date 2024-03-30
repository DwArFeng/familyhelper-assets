package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCover;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverOrderUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverUploadInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemCoverOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemCoverInfoMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ItemCoverOperateHandlerImpl implements ItemCoverOperateHandler {

    private final ItemCoverInfoMaintainService itemCoverInfoMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final OperateHandlerValidator operateHandlerValidator;

    public ItemCoverOperateHandlerImpl(
            ItemCoverInfoMaintainService itemCoverInfoMaintainService,
            FtpHandler ftpHandler,
            KeyGenerator<LongIdKey> keyGenerator,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.itemCoverInfoMaintainService = itemCoverInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyGenerator = keyGenerator;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public ItemCover downloadItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目封面存在。
            operateHandlerValidator.makeSureItemCoverExists(itemCoverKey);

            // 3. 获取项目封面对应的项目，并确认用户有权限操作项目。
            ItemCoverInfo itemCoverInfo = itemCoverInfoMaintainService.get(itemCoverKey);
            operateHandlerValidator.makeSureUserInspectPermittedForItem(userKey, itemCoverInfo.getItemKey());

            // 4. 下载项目封面。
            byte[] content = ftpHandler.retrieveFile(FtpConstants.PATH_ITEM_COVER, getFileName(itemCoverKey));

            // 5. 拼接 ItemCover 并返回。
            return new ItemCover(itemCoverInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void uploadItemCover(StringIdKey userKey, ItemCoverUploadInfo itemCoverUploadInfo) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目封面所属的项目存在。
            LongIdKey itemKey = itemCoverUploadInfo.getItemKey();
            operateHandlerValidator.makeSureItemExists(itemKey);

            // 3. 确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemKey);

            // 4. 分配主键。
            LongIdKey itemCoverKey = keyGenerator.generate();

            // 5. 项目封面内容并存储（覆盖）。
            byte[] content = itemCoverUploadInfo.getContent();
            ftpHandler.storeFile(FtpConstants.PATH_ITEM_COVER, getFileName(itemCoverKey), content);

            // 6. 根据 itemCoverUploadInfo 构造 ItemCoverInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            ItemCoverInfo itemCoverInfo = new ItemCoverInfo();
            itemCoverInfo.setKey(itemCoverKey);
            itemCoverInfo.setItemKey(itemKey);
            itemCoverInfo.setOriginName(itemCoverUploadInfo.getOriginName());
            itemCoverInfo.setLength(itemCoverUploadInfo.getContent().length);
            itemCoverInfo.setCreatedDate(currentDate);
            itemCoverInfo.setModifiedDate(currentDate);
            itemCoverInfo.setRemark("通过 familyhelper-assets 服务上传/更新项目封面");
            // 查找当前 index 最大的 ItemCoverInfo 并且设置 index 为 最大的 index + 1。
            int index = itemCoverInfoMaintainService.lookup(
                    ItemCoverInfoMaintainService.CHILD_FOR_ITEM_INDEX_DESC, new Object[]{itemKey}, new PagingInfo(0, 1)
            ).getData().stream().findFirst().map(i -> i.getIndex() + 1).orElse(0);
            itemCoverInfo.setIndex(index);
            itemCoverInfoMaintainService.insertOrUpdate(itemCoverInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目封面存在。
            operateHandlerValidator.makeSureItemCoverExists(itemCoverKey);

            // 3. 获取项目封面对应的项目，并确认用户有权限操作项目。
            ItemCoverInfo itemCoverInfo = itemCoverInfoMaintainService.get(itemCoverKey);
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemCoverInfo.getItemKey());

            // 4. 如果存在 ItemCover 文件，则删除。
            if (ftpHandler.existsFile(FtpConstants.PATH_ITEM_COVER, getFileName(itemCoverKey))) {
                ftpHandler.deleteFile(FtpConstants.PATH_ITEM_COVER, getFileName(itemCoverKey));
            }

            // 5. 如果存在 ItemCoverInfo 实体，则删除。
            itemCoverInfoMaintainService.deleteIfExists(itemCoverKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private String getFileName(LongIdKey itemCoverKey) {
        return Long.toString(itemCoverKey.getLongId());
    }

    @Override
    public void updateItemCoverOrder(StringIdKey userKey, ItemCoverOrderUpdateInfo itemCoverOrderUpdateInfo)
            throws HandlerException {
        try {
            List<LongIdKey> itemCoverKeys = itemCoverOrderUpdateInfo.getItemCoverKeys();

            // 1. 特殊情况：itemCoverKeys 为空数组，则不执行任何逻辑。
            if (itemCoverKeys.isEmpty()) {
                return;
            }

            // 2. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 3. 确认项目封面存在。
            for (LongIdKey itemCoverKey : itemCoverKeys) {
                operateHandlerValidator.makeSureItemCoverExists(itemCoverKey);
            }

            // 4. 确认项目封面属于同一个项目，且项目不为空。
            operateHandlerValidator.makeSureItemCoverHasSameItem(itemCoverKeys);

            // 5. 获取项目封面所属的项目。
            LongIdKey itemKey = itemCoverInfoMaintainService.get(itemCoverKeys.get(0)).getItemKey();

            // 6. 获取按照旧顺序排列的项目封面。
            List<ItemCoverInfo> orderedItemCoverInfoList = itemCoverInfoMaintainService.lookup(
                    ItemCoverInfoMaintainService.CHILD_FOR_ITEM_INDEX_ASC, new Object[]{itemKey}
            ).getData();

            // 7. 按照 itemCoverKeys 重新组织顺序。
            orderedItemCoverInfoList.removeIf(i -> itemCoverKeys.contains(i.getKey()));
            for (int i = itemCoverKeys.size() - 1; i >= 0; i--) {
                orderedItemCoverInfoList.add(0, itemCoverInfoMaintainService.get(itemCoverKeys.get(i)));
            }
            for (int i = 0; i < orderedItemCoverInfoList.size(); i++) {
                orderedItemCoverInfoList.get(i).setIndex(i);
            }

            // 8. 批量更新。
            itemCoverInfoMaintainService.batchUpdate(orderedItemCoverInfoList);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
