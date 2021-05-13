package com.dreamwolf.video.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.imageresource.webinterface.UploadFileResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "image-resource-service")
public interface ImageResourceService {
    @PostMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseData<UploadFileResult> upload(@RequestPart MultipartFile image, @RequestParam String type);
}
