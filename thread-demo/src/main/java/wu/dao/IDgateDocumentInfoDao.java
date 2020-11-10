package wu.dao;

import wu.entity.DgateDocumentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author wdhua
 */
@Repository
public interface IDgateDocumentInfoDao {


    /**
     * 查询初始状态的数据发送到队列
     *
     */
    List<DgateDocumentInfo> queryForSend(@Param("exportStatus") String exportStatus,
                                         @Param("start") int start,
                                         @Param("end") int end) throws Exception;




    /**
     * 更新报告处理的状态
     *
     * @param listDoc
     * @throws Exception
     */
    void updateForSend(List<DgateDocumentInfo> listDoc) throws Exception;




}
