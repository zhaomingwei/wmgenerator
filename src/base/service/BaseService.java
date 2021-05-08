package base.service;

import base.model.BaseExample;
import base.model.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseService<T, Example extends BaseExample, ID> {
//	void setMapper();
	
	int countByExample(Example example);

	int deleteByExample(Example example);

	int deleteByPrimaryKey(ID id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(Example example);
	/**
	 * if pageInfo == null<p/>
	 * then return result of selectByExample(example)
	 * @author Marvis
	 * @date Jul 13, 2017 5:24:35 PM
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	List<T> selectByPageExample(Example example, PageInfo pageInfo);

	T selectByPrimaryKey(ID id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") Example example);

	int updateByExample(@Param("record") T record, @Param("example") Example example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

//	int updateByPrimaryKeyWithBLOBs(T record);
}
