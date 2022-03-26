package io.peaches.academy.service.edu.feign.fallback;

import io.peaches.academy.common.base.result.R;
import io.peaches.academy.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R test() {
        return null;
    }

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error();
    }
}
