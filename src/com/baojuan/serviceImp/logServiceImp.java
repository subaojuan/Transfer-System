package com.baojuan.serviceImp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.baojuan.pojo.Log;
import com.baojuan.pojo.PageInfo;
import com.baojuan.service.logService;

public class logServiceImp implements logService{

	@Override
	public PageInfo showPage(int pageSize, int pageNumber) throws IOException {
		InputStream is=Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession session=factory.openSession();
		long count=session.selectOne("com.baojuan.mapper.LogMapper.selCount");
		Map<String,Object> map=new HashMap<>();
		map.put("pageStart",pageSize*(pageNumber-1));
		map.put("pageSize", pageSize);
		List<Log> list=session.selectList("com.baojuan.mapper.LogMapper.selBypage",map);
		PageInfo pi=new PageInfo();
		pi.setList(list);
		pi.setPageNumber(pageNumber);
		pi.setPageSize(pageSize);
		pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
		return pi;
	}

}
