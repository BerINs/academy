package io.peaches.academy.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.peaches.academy.service.edu.entity.Subject;
import io.peaches.academy.service.edu.entity.excel.ExcelSubjectData;
import io.peaches.academy.service.edu.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    private SubjectMapper subjectMapper;

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        log.info("解析到一条记录: {}", excelSubjectData);

        // 读取分类
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        log.info(levelOneTitle);
        log.info(levelTwoTitle);
        String parentId = null;

        // 一级类别存储
        Subject byTitle = this.getByTitle(levelOneTitle);
        if (byTitle == null) {
            // 组装
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            subjectMapper.insert(subject);
            parentId = subject.getId();
        } else {
            parentId = byTitle.getId();
        }

        // 二级类别存储
        Subject bySubTitle = this.getSubByTitle(levelTwoTitle, parentId);
        if (bySubTitle == null) {
            // 组装
            Subject subject = new Subject();
            subject.setParentId(parentId);
            subject.setTitle(levelTwoTitle);
            subjectMapper.insert(subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部解析完成");
    }

    // 一级类别查重
    private Subject getByTitle(String title) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");
        return subjectMapper.selectOne(queryWrapper);
    }

    // 二级类别查重
    private Subject getSubByTitle(String title, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return subjectMapper.selectOne(queryWrapper);
    }
}
