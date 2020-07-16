package com.service.impl;

import com.dao.impl.ReaderDao;
import com.domain.Reader;
import com.service.IReaderService;
import com.util.PageBean;
import vo.PageReaderSearch;

import java.sql.SQLException;
import java.util.List;

public class ReaderService implements IReaderService {

    private ReaderDao readerDao=new ReaderDao();


    @Override
    public void saveReader(Reader reader) throws Exception {
        readerDao.save(reader);
    }

    @Override
    public void deleteReader(String readerId) throws Exception {
        readerDao.delete(readerId);
    }

    @Override
    public void updateReader(Reader reader) throws Exception {
        readerDao.update(reader);
    }

    @Override
    public List<Reader> findAllReaders() throws Exception {
        return readerDao.findAll();
    }

    @Override
    public Reader findReader(String readerId) throws Exception {
        return readerDao.findById(readerId);
    }

    @Override
    public Reader findReaderByAccount(String readerAccount) throws Exception {
        return readerDao.findByAccount(readerAccount);
    }

    @Override
    public PageBean<Reader> searchPageReader(PageReaderSearch pageReaderSearch,int currentCount,int currentPage) throws SQLException {
        //获取查询后的总条数
        int totalCount=readerDao.countSearchReaders(pageReaderSearch);
        //获取总页数
        int result=totalCount%currentCount;
        int totalPage;
        if (result==0){
            totalPage=totalCount/currentCount;
        }else {
            totalPage=(totalCount/currentCount)+1;
        }
        //获取返回的当页的数据集合
        List<Reader> readers=readerDao.findReaders(pageReaderSearch,currentCount,currentPage);
        //封装到分页对象中
        PageBean<Reader> readerPageBean=new PageBean<Reader>(currentPage,currentCount,totalPage,totalCount,readers);
        //返回分页对象
        return readerPageBean;
    }


}

