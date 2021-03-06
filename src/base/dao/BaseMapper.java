package base.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T, Example, ID> {
	
	int countByExample(Example example);

    int deleteByExample(Example example);

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(Example example);

    T selectByPrimaryKey(ID id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

    int updateByExample(@Param("record") T record, @Param("example") Example example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
