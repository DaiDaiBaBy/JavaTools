package com.zhoufu.springbootoptional.controller;

import com.zhoufu.springbootoptional.domain.ProminentTeacherSumInfo;
import com.zhoufu.springbootoptional.domain.SchoolSumInfo;
import com.zhoufu.springbootoptional.domain.TeacherSumInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName : TestController
 * @Author : ZhouFu
 * @Date: 2023/5/6 11:02
 * @Description : 测试
 */
@Controller
public class TestController {

    private void testIFElse(){
        SchoolSumInfo schoolSumInfo = getNullValueSchoolSumInfo();
        String mostManName = "暂无";
        if (Objects.nonNull(schoolSumInfo)) {
            TeacherSumInfo teacherSumInfo = schoolSumInfo.getTeacherSumInfo();
            if (Objects.nonNull(teacherSumInfo)) {
                ProminentTeacherSumInfo prominentTeacherSumInfo = teacherSumInfo.getProminentTeacherSumInfo();
                if (Objects.nonNull(prominentTeacherSumInfo)) {
                    if (StringUtils.isEmpty(prominentTeacherSumInfo.getMostManName())){
                        mostManName=prominentTeacherSumInfo.getMostManName();
                    }
                }
            }
        }
        System.out.println(" 获取结果: " + mostManName);
    }

    // Optional替代 if-else
    private void testOptional(){
        SchoolSumInfo schoolSumInfo = getNullValueSchoolSumInfo();
        String prominentTeaCount = Optional.of(schoolSumInfo)
                .map(SchoolSumInfo::getTeacherSumInfo)
                .map(TeacherSumInfo::getProminentTeacherSumInfo)
                .map(ProminentTeacherSumInfo::getMostManName)
                .orElse("暂无");
        System.out.println("getNullValueSchoolSumInfo 获取结果: " + prominentTeaCount);


        SchoolSumInfo schoolSumInfo1 = getHasValueSchoolSumInfo();
        String prominentTeaCount1 = Optional.of(schoolSumInfo1)
                .map(SchoolSumInfo::getTeacherSumInfo)
                .map(TeacherSumInfo::getProminentTeacherSumInfo)
                .map(ProminentTeacherSumInfo::getMostManName)
                .orElse("暂无");
        System.out.println("getHasValueSchoolSumInfo 获取结果:" + prominentTeaCount1);
    }

    // 模拟无值  不判空则空指针
    private static SchoolSumInfo getNullValueSchoolSumInfo() {
        return new SchoolSumInfo("xxxxxx");
    }

    // 模拟有值   不判空也报错
    private static SchoolSumInfo getHasValueSchoolSumInfo() {
        TeacherSumInfo teacherSumInfo = new TeacherSumInfo();
        ProminentTeacherSumInfo prominentTeacherSumInfo = new ProminentTeacherSumInfo();
        prominentTeacherSumInfo.setCount(99999);
        prominentTeacherSumInfo.setMostManName("ooooo");
        teacherSumInfo.setProminentTeacherSumInfo(prominentTeacherSumInfo);
        SchoolSumInfo schoolsumInfo = new SchoolSumInfo();
        schoolsumInfo.setTeacherSumInfo(teacherSumInfo);
        return schoolsumInfo;
    }
}
