package cn.base.java;

import com.dao.ILendDao;
import com.daoimpl.LendDao;
import com.domain.Book;
import com.domain.Lend;
import com.service.IBookService;
import com.service.ILendService;
import com.serviceimpl.BookService;
import com.serviceimpl.LendService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class LendMain {
//   ILendService lendService=new LendService();

    public static void main(String[] args) throws Exception {
        ILendService lendService=new LendService();
        ILendDao lendDao=new LendDao();
        List<Lend> lends=lendService.findAllLends();
        List<Lend> lends1=lendDao.findAll();
        System.out.println(lends1);
    }

}
