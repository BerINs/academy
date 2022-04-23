package io.peaches.academy.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import io.peaches.academy.service.edu.entity.Subject;
import io.peaches.academy.service.edu.entity.excel.ExcelSubjectData;
import io.peaches.academy.service.edu.entity.vo.SubjectVO;
import io.peaches.academy.service.edu.listener.ExcelSubjectDataListener;
import io.peaches.academy.service.edu.mapper.SubjectMapper;
import io.peaches.academy.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-02-09
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Override
    public void batchImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVO> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}
