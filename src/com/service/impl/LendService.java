package com.service.impl;

import com.dao.impl.BookDao;
import com.dao.impl.LendDao;
import com.dao.impl.ReaderDao;
import com.domain.Lend;
import com.service.ILendService;

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
