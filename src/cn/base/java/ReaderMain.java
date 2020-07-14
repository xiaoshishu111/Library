package cn.base.java;

import com.daoimpl.BookDao;
import com.domain.Book;
import com.domain.Reader;
import com.enums.ReaderAuthortyEnum;
import com.service.IReaderService;
import com.serviceimpl.BookService;
import com.serviceimpl.ReaderService;

import java.util.List;

public class ReaderMain {
    public static void main(String[] args) throws Exception {
        IReaderService readerService=new ReaderService();
//        Reader reader=new Reader("003","mmm","33","333", ReaderAuthortyEnum.AVERAGE_USER.getCode());
//        readerService.saveReader(reader);
//        readerService.deleteReader("001");
        Reader reader1=new Reader("002","2222","222","222",ReaderAuthortyEnum.ADMIN_USER.getCode());
        readerService.updateReader(reader1);
        List<Reader> readers= readerService.findAllReaders();
        for (Reader reader:readers){
            System.out.println(reader);
        }
        Reader reader=readerService.findReader("002");
        System.out.println(reader);
    }
}
