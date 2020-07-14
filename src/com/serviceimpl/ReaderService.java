package com.serviceimpl;

import com.daoimpl.ReaderDao;
import com.daoimpl.ReaderDao;
import com.domain.Book;
import com.domain.Reader;
import com.domain.Reader;
import com.service.IReaderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

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


}

