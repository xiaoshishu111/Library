package cn.base.java;

import com.dao.ILendDao;
import com.dao.impl.LendDao;
import com.domain.Lend;
import com.service.ILendService;
import com.service.impl.LendService;

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
