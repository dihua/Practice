package wu.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import wu.dao.IDgateDocumentInfoDao;
import wu.entity.DgateDocumentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;


/**
 * 处理报告（上传、更新、废弃）
 *
 * @author -
 * @date 2017/7/12
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ExportReportServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(ExportReportServiceImpl.class);


    @Autowired
    private IDgateDocumentInfoDao dgateDocumentInfoDao;

//    public static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    public static final ThreadPoolExecutor executorService =
            new ThreadPoolExecutor(5, 5,
                    30L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(2),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public void run() {
        for (int i = 1; i < 1000; i++) {
            DocThread docThread = new DocThread("X0", i, i*10);
            Future<Boolean> future = executorService.submit(docThread);

            try {
//                Thread.sleep(5000);
                if (future.get()) {
                    executorService.shutdown();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


     class DocThread implements Callable<Boolean> {

        private final Logger LOGGER = LoggerFactory.getLogger(DocThread.class);


        private String exportStatus;
        private int start;
        private int end;

        public DocThread(String exportStatus, int start, int end) {
            this.exportStatus = exportStatus;
            this.start = start;
            this.end = end;
        }

        @Override
        public Boolean call() throws Exception {
            LOGGER.info("分页查询表dgateDocumentInfo start = {}, end = {}", start, end);
            List<DgateDocumentInfo> documentInfos = dgateDocumentInfoDao.queryForSend(exportStatus, start, end);
            Thread.sleep(5000);
            if (CollectionUtils.isEmpty(documentInfos)) {
                LOGGER.info("网关库查询不到需要处理的数据");
                return false;
            }
            for (DgateDocumentInfo doc : documentInfos) {
                LOGGER.info("正在处理 pk = {}", doc.getPk());
            }
            return true;
        }
    }
}
