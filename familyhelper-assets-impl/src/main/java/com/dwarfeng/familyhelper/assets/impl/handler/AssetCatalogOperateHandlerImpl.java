package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.exception.AssetCatalogNotExistsException;
import com.dwarfeng.familyhelper.assets.stack.exception.InvalidPermissionLevelException;
import com.dwarfeng.familyhelper.assets.stack.exception.UserNotExistsException;
import com.dwarfeng.familyhelper.assets.stack.exception.UserNotPermittedException;
import com.dwarfeng.familyhelper.assets.stack.handler.AssetCatalogOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.PoacMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class AssetCatalogOperateHandlerImpl implements AssetCatalogOperateHandler {

    private final UserMaintainService userMaintainService;
    private final AssetCatalogMaintainService assetCatalogMaintainService;
    private final PoacMaintainService poacMaintainService;

    public AssetCatalogOperateHandlerImpl(
            UserMaintainService userMaintainService,
            AssetCatalogMaintainService assetCatalogMaintainService,
            PoacMaintainService poacMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.assetCatalogMaintainService = assetCatalogMaintainService;
        this.poacMaintainService = poacMaintainService;
    }

    @Override
    public LongIdKey createAssetCatalog(StringIdKey userKey, AssetCatalogCreateInfo assetCatalogCreateInfo)
            throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 根据 assetCatalogCreateInfo 以及创建的规则组合 资产目录 实体。
            AssetCatalog assetCatalog = new AssetCatalog(
                    null, assetCatalogCreateInfo.getName(), assetCatalogCreateInfo.getRemark(), 0, new Date()
            );

            // 3. 插入资产目录实体，并获取生成的主键。
            LongIdKey assetCatalogKey = assetCatalogMaintainService.insert(assetCatalog);

            // 4. 由资产目录实体生成的主键和用户主键组合权限信息，并插入。
            Poac poac = new Poac(
                    new PoacKey(assetCatalogKey.getLongId(), userKey.getStringId()),
                    Constants.PERMISSION_LEVEL_OWNER,
                    "创建资产目录时自动插入，赋予创建人所有者权限"
            );
            poacMaintainService.insert(poac);

            // 5. 返回生成的主键。
            return assetCatalogKey;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateAssetCatalog(StringIdKey userKey, AssetCatalogUpdateInfo assetCatalogUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey assetCatalogKey = assetCatalogUpdateInfo.getAssetCatalogKey();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            makeSureAssetCatalogExists(assetCatalogKey);

            // 3. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForAssetCatalog(userKey, assetCatalogKey);

            // 4. 根据 assetCatalogUpdateInfo 以及更新的规则设置 资产目录 实体。
            AssetCatalog assetCatalog = assetCatalogMaintainService.get(assetCatalogKey);
            assetCatalog.setName(assetCatalogUpdateInfo.getName());
            assetCatalog.setRemark(assetCatalogUpdateInfo.getRemark());

            // 5. 更新资产目录实体。
            assetCatalogMaintainService.update(assetCatalog);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            makeSureAssetCatalogExists(assetCatalogKey);

            // 3. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForAssetCatalog(userKey, assetCatalogKey);

            // 4. 删除指定主键的资产目录。
            assetCatalogMaintainService.delete(assetCatalogKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo)
            throws HandlerException {
        try {
            LongIdKey assetCatalogKey = permissionUpsertInfo.getAssetCatalogKey();
            StringIdKey targetUserKey = permissionUpsertInfo.getUserKey();
            int permissionLevel = permissionUpsertInfo.getPermissionLevel();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认 permissionLevel 有效。
            makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            makeSureUserExists(ownerUserKey);
            makeSureUserExists(targetUserKey);

            // 4. 确认资产目录存在。
            makeSureAssetCatalogExists(assetCatalogKey);

            // 5. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForAssetCatalog(ownerUserKey, assetCatalogKey);

            // 6. 通过入口信息组合权限实体，并进行插入或更新操作。
            String permissionLabel;
            switch (permissionLevel) {
                case Constants.PERMISSION_LEVEL_GUEST:
                    permissionLabel = "目标";
                    break;
                case Constants.PERMISSION_LEVEL_OWNER:
                    permissionLabel = "所有者";
                    break;
                default:
                    permissionLabel = "（未知）";
            }
            Poac poac = new Poac(
                    new PoacKey(assetCatalogKey.getLongId(), targetUserKey.getStringId()),
                    permissionLevel,
                    "赋予用户 " + targetUserKey.getStringId() + " " + permissionLabel + "权限"
            );
            poacMaintainService.insertOrUpdate(poac);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo)
            throws HandlerException {
        try {
            LongIdKey assetCatalogKey = permissionRemoveInfo.getAssetCatalogKey();
            StringIdKey targetUserKey = permissionRemoveInfo.getUserKey();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认用户存在。
            makeSureUserExists(ownerUserKey);
            makeSureUserExists(targetUserKey);

            // 3. 确认资产目录存在。
            makeSureAssetCatalogExists(assetCatalogKey);

            // 4. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForAssetCatalog(ownerUserKey, assetCatalogKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PoacKey poacKey = new PoacKey(assetCatalogKey.getLongId(), targetUserKey.getStringId());
            poacMaintainService.deleteIfExists(poacKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureAssetCatalogExists(LongIdKey assetCatalogKey) throws HandlerException {
        try {
            if (Objects.isNull(assetCatalogKey) || !assetCatalogMaintainService.exists(assetCatalogKey)) {
                throw new AssetCatalogNotExistsException(assetCatalogKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey)
            throws HandlerException {
        try {
            // 1. 构造 Poac 主键。
            PoacKey poacKey = new PoacKey(assetCatalogKey.getLongId(), userKey.getStringId());

            // 2. 查看 Poac 实体是否存在，如果不存在，则没有权限。
            if (!poacMaintainService.exists(poacKey)) {
                throw new UserNotPermittedException(userKey, assetCatalogKey);
            }

            // 3. 查看 Poac.permissionLevel 是否为 Poac.PERMISSION_LEVEL_OWNER，如果不是，则没有权限。
            Poac poac = poacMaintainService.get(poacKey);
            if (!Objects.equals(poac.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                throw new UserNotPermittedException(userKey, assetCatalogKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        if (permissionLevel == Constants.PERMISSION_LEVEL_MODIFIER) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }
}
