//package wu.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//import wu.dao.IDgateDocumentInfoDao;
//import wu.entity.DgateDocumentInfo;
//
//import java.util.List;
//import java.util.concurrent.Callable;
//
///**
// * @author dihua.wu
// * @description
// * @create 2020/11/9
// */
//
//public class DocThread implements Callable<Boolean> {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(DocThread.class);
//
//    @Autowired
//    private IDgateDocumentInfoDao dgateDocumentInfoDao;
//
//    private String exportStatus;
//    private int start;
//    private int end;
//
//
//    public DocThread(String exportStatus, int start, int end) {
//        this.exportStatus = exportStatus;
//        this.start = start;
//        this.end = end;
//    }
//
//    @Override
//    public Boolean call() throws Exception {
//        LOGGER.info("分页查询表dgateDocumentInfo start = {}, end = {}", start, end);
//        List<DgateDocumentInfo> documentInfos = dgateDocumentInfoDao.queryForSend(exportStatus, start, end);
//        Thread.sleep(5000);
//        if (CollectionUtils.isEmpty(documentInfos)) {
//            LOGGER.info("网关库查询不到需要处理的数据");
//            return false;
//        }
//        for (DgateDocumentInfo doc : documentInfos) {
//            LOGGER.info("正在处理 pk = {}", doc.getPk());
//        }
//        return true;
//    }
//}
