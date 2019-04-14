package com.baojuan.service;

import java.io.IOException;

import com.baojuan.pojo.PageInfo;

public interface logService {
PageInfo showPage(int pageSize,int pageNumber) throws IOException;
}
