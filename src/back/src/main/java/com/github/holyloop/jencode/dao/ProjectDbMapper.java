package com.github.holyloop.jencode.dao;

import com.github.holyloop.jencode.model.ProjectDb;
import com.github.holyloop.jencode.model.ProjectDbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectDbMapper extends BaseMapper<ProjectDb> {
    long countByExample(ProjectDbExample example);

    int deleteByExample(ProjectDbExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectDb record);

    int insertSelective(ProjectDb record);

    List<ProjectDb> selectByExample(ProjectDbExample example);

    ProjectDb selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectDb record, @Param("example") ProjectDbExample example);

    int updateByExample(@Param("record") ProjectDb record, @Param("example") ProjectDbExample example);

    int updateByPrimaryKeySelective(ProjectDb record);

    int updateByPrimaryKey(ProjectDb record);
}