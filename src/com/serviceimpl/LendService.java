package com.serviceimpl;

import com.dao.IReaderDao;
import com.daoimpl.BookDao;
import com.daoimpl.LendDao;
import com.daoimpl.ReaderDao;
import com.domain.Book;
import com.domain.Lend;
import com.domain.Reader;
import com.enums.BookStatusEnum;
import com.service.ILendService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LendService implements ILendService {
    UUID uuid=UUID.randomUUID();
    Scanner scanner = new Scanner(System.in);
    private ReaderDao readerDao=new ReaderDao();
    private BookDao bookDao=new BookDao();
    private LendDao lendDao=new LendDao();

    @Override
    public List<Lend> findAllLends() throws Exception {
        return lendDao.findAll();
    }
}
