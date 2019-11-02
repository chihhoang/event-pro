package com.eventpro.app.service.impl;

import com.eventpro.app.exception.SystemException;
import com.eventpro.app.model.Asset;
import com.eventpro.app.model.Role;
import com.eventpro.app.model.User;
import com.eventpro.app.repository.AssetRepository;
import com.eventpro.app.service.AmazonS3Service;
import com.eventpro.app.service.AssetService;
import com.eventpro.app.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** @author choang on 10/24/19 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
  private final AssetRepository assetRepository;
  private final UserService userService;
  private final AmazonS3Service amazonS3Service;

  @Override
  public Asset createAsset(
      String fileName, String description, MultipartFile multipartFile, String username) {
    Asset asset = amazonS3Service.uploadFile(fileName, multipartFile, username);
    asset.setDescription(description);

    return assetRepository.save(asset);
  }

  @Override
  public Asset getAsset(long id) {
    return assetRepository
        .findById(id)
        .orElseThrow(
            () -> new SystemException("Unable to find asset " + id, HttpStatus.BAD_REQUEST));
  }

  @Override
  public Asset deleteAsset(long id) {
    Asset asset = getAsset(id);

    amazonS3Service.deleteFile(asset.getFileName());

    assetRepository.delete(asset);

    return asset;
  }

  @Override
  public Asset deleteAsset(long id, String username) {
    User user = userService.getUserByUsername(username);

    Asset asset = getAsset(id);

    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return deleteAsset(id);
    }

    // TODO verify user before delete

    return asset;
  }

  @Override
  public List<Asset> listAssets(String username) {
    User user = userService.getUserByUsername(username);

    if (user.getRoles().contains(Role.ROLE_ADMIN)) {
      return assetRepository.findAll();
    } else {
      return assetRepository.findByUser(user);
    }
  }
}
