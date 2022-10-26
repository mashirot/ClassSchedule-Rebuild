package ski.mashiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.mashiro.dao.TableDao;
import ski.mashiro.service.TableService;

/**
 * @author MashiroT
 */
@Service
public class TableServiceImpl implements TableService {
    private final TableDao tableDao;

    @Autowired
    public TableServiceImpl(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public void createUserTable() {
        tableDao.createUserTable();
    }

    @Override
    public void createCourseTable(String tableName) {
        tableDao.createCourseTable(tableName);
    }

    @Override
    public void deleteCourseTable(String tableName) {
        tableDao.deleteCourseTable(tableName);
    }
}
