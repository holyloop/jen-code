package com.github.holyloop.jencode.dao;

import com.github.holyloop.jencode.model.ProjectDbTable;
import com.github.holyloop.jencode.model.ProjectDbTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectDbTableMapper extends BaseMapper<ProjectDbTable> {
    long countByExample(ProjectDbTableExample example);

    int deleteByExample(ProjectDbTableExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectDbTable record);

    int insertSelective(ProjectDbTable record);

    List<ProjectDbTable> selectByExample(ProjectDbTableExample example);

    ProjectDbTable selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectDbTable record, @Param("example") ProjectDbTableExample example);

    int updateByExample(@Param("record") ProjectDbTable record, @Param("example") ProjectDbTableExample example);

    int updateByPrimaryKeySelective(ProjectDbTable record);

    int updateByPrimaryKey(ProjectDbTable record);
}