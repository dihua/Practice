package wu.entity;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 文档 关键信息
 */
public class DgateDocumentInfo implements Serializable {
    private static final long serialVersionUID = -1638093581207010166L;

    private String pk;

    private String documentUniqueId; // 第三方厂商文档的唯一ID号

    private String documentDomainId; // 第三方厂商文档对应的机构域

    private String patientId; // 病人住院号/门诊号/体检号

    private String patientDomainId; // 病人域UID，分别对应病人住院号域/门诊号域/体检号

    private String fileType; // 文件类型，来自字典表

    private String payLoadType; // 文档类型，来自字典表

    private String subType; // 消息类型，区分上传，更新

    private String content; // 文档内容

    private Timestamp startTime; // 该记录插入的时间

    private Timestamp effectiveTime; // 文档的生成时间，如果文档为CDA格式，要求必填

    private String hiupStatus; // 通知消息状态

    private Timestamp endTime; // 该记录成功上传的时间

    private String filePath; // 生成文件的路径，实际的文件路径为 DgateFileSystem(通过fileSystemFk获取).DIR_PATH+FILE_PATH

    private String entryUuid; // 上传成功后返回的文档ENTRYUUID

    private Long retry; // 通知重试次数

    private Timestamp retryTime; // 通知错误时间

    private String globalId; // 文档对应的人的唯一UID

    private Long fileSystemFk; // 文档对应的文件目录外键

    private String hiupErrorInfo;

    private String cdaUniqueId;

    private String requestNumber;

    private String orderNumber;

    private String repositoryUniqueId; // XDS上传的唯一UID

    private String requestDomain;

    private String orderDomain;

    private String beforeStatus;

    private Timestamp beforeRetryTime;

    private Long beforeRetry;

    private String patientType;

    private String custom1;

    private String custom2;

    private String custom3;

    private String custom4;

    private String custom5;

    private String afterStatus;

    private Timestamp afterRetryTime;

    private Long afterRetry;

    private String exportStatus;

    private Timestamp exportRetryTime;

    private Long exportRetry;

    private String patName;

    private String tposPath;

    private String clobStatus;

    private Timestamp clobRetryTime;

    private Long clobRetry;

    private String patCategory;

    private String patCategorySystem;

    //北京宣武使用的字段，start
    private String assignedPerson;        //审核医生

    private String docName;                    //文档标题名称

    private Timestamp admitTime;            //入院/登记时间

    private String hisType;                    //患者类别，区分门诊住院病人

    private String bedNo;                    //床号

    private String assignedCode;            //审核医师工号

    private String authorCode;                //报告医师工号

    private String authorName;                //报告医师姓名

    private String modality;                //检查类型

    private String accessionnum;            //检查申请单

    private String studyUid;                //检查UID

    private String bodyPart;                //检查部位

    private String diagnosisMethodCode;        //检查方法

    private String dicomNum;                //影像号

    private Timestamp dicomStudyTime;        //检查时间

    private Long repeatNumber;        //曝光次数

    private String machineRoomName;            //机房名称

    private String deviceName;                //设备名称

    private String retrieveAets;
    //北京宣武使用的字段，end

    private String esStatus;//推送ES状态，默认0，成功1，失败4
    private Timestamp esTime;
    private String esErrorInfo;

    private String sendStatus;

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getEsStatus() {
        return esStatus;
    }

    public void setEsStatus(String esStatus) {
        this.esStatus = esStatus;
    }

    public Timestamp getEsTime() {
        return esTime;
    }

    public void setEsTime(Timestamp esTime) {
        this.esTime = esTime;
    }

    public String getEsErrorInfo() {
        return esErrorInfo;
    }

    public void setEsErrorInfo(String esErrorInfo) {
        this.esErrorInfo = esErrorInfo;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getDocumentUniqueId() {
        return documentUniqueId;
    }

    public void setDocumentUniqueId(String documentUniqueId) {
        this.documentUniqueId = documentUniqueId;
    }

    public String getDocumentDomainId() {
        return documentDomainId;
    }

    public void setDocumentDomainId(String documentDomainId) {
        this.documentDomainId = documentDomainId;
    }

    public String getPatientIdTrim() {
        if(patientId!=null){
            return patientId.replace("null", "").replace(" ", "");
        }else{
            return patientId;
        }
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientDomainId() {
        return patientDomainId;
    }

    public void setPatientDomainId(String patientDomainId) {
        this.patientDomainId = patientDomainId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPayLoadType() {
        return payLoadType;
    }

    public void setPayLoadType(String payLoadType) {
        this.payLoadType = payLoadType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public Timestamp getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Timestamp effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getHiupStatus() {
        return hiupStatus;
    }

    public void setHiupStatus(String hiupStatus) {
        this.hiupStatus = hiupStatus;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getEntryUuid() {
        return entryUuid;
    }

    public void setEntryUuid(String entryUuid) {
        this.entryUuid = entryUuid;
    }

    public Long getRetry() {
        return retry;
    }

    public void setRetry(Long retry) {
        this.retry = retry;
    }

    public Timestamp getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(Timestamp retryTime) {
        this.retryTime = retryTime;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public Long getFileSystemFk() {
        return fileSystemFk;
    }

    public void setFileSystemFk(Long fileSystemFk) {
        this.fileSystemFk = fileSystemFk;
    }

    public String getHiupErrorInfo() {
        return hiupErrorInfo;
    }

    public void setHiupErrorInfo(String hiupErrorInfo) {
        this.hiupErrorInfo = hiupErrorInfo;
    }

    public String getCdaUniqueId() {
        return cdaUniqueId;
    }

    public void setCdaUniqueId(String cdaUniqueId) {
        this.cdaUniqueId = cdaUniqueId;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRepositoryUniqueId() {
        return repositoryUniqueId;
    }

    public void setRepositoryUniqueId(String repositoryUniqueId) {
        this.repositoryUniqueId = repositoryUniqueId;
    }

    public String getRequestDomain() {
        return requestDomain;
    }

    public void setRequestDomain(String requestDomain) {
        this.requestDomain = requestDomain;
    }

    public String getOrderDomain() {
        return orderDomain;
    }

    public void setOrderDomain(String orderDomain) {
        this.orderDomain = orderDomain;
    }

    public String getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(String beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public Timestamp getBeforeRetryTime() {
        return beforeRetryTime;
    }

    public void setBeforeRetryTime(Timestamp beforeRetryTime) {
        this.beforeRetryTime = beforeRetryTime;
    }

    public Long getBeforeRetry() {
        return beforeRetry;
    }

    public void setBeforeRetry(Long beforeRetry) {
        this.beforeRetry = beforeRetry;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getCustom1() {
        return custom1;
    }

    public void setCustom1(String custom1) {
        this.custom1 = custom1;
    }

    public String getCustom2() {
        return custom2;
    }

    public void setCustom2(String custom2) {
        this.custom2 = custom2;
    }

    public String getCustom3() {
        return custom3;
    }

    public void setCustom3(String custom3) {
        this.custom3 = custom3;
    }

    public String getCustom4() {
        return custom4;
    }

    public void setCustom4(String custom4) {
        this.custom4 = custom4;
    }

    public String getCustom5() {
        return custom5;
    }

    public void setCustom5(String custom5) {
        this.custom5 = custom5;
    }

    public String getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(String afterStatus) {
        this.afterStatus = afterStatus;
    }

    public Timestamp getAfterRetryTime() {
        return afterRetryTime;
    }

    public void setAfterRetryTime(Timestamp afterRetryTime) {
        this.afterRetryTime = afterRetryTime;
    }

    public Long getAfterRetry() {
        return afterRetry;
    }

    public void setAfterRetry(Long afterRetry) {
        this.afterRetry = afterRetry;
    }

    public String getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }

    public Timestamp getExportRetryTime() {
        return exportRetryTime;
    }

    public void setExportRetryTime(Timestamp exportRetryTime) {
        this.exportRetryTime = exportRetryTime;
    }

    public Long getExportRetry() {
        return exportRetry;
    }

    public void setExportRetry(Long exportRetry) {
        this.exportRetry = exportRetry;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getTposPath() {
        return tposPath;
    }

    public void setTposPath(String tposPath) {
        this.tposPath = tposPath;
    }

    public String getClobStatus() {
        return clobStatus;
    }

    public void setClobStatus(String clobStatus) {
        this.clobStatus = clobStatus;
    }

    public Timestamp getClobRetryTime() {
        return clobRetryTime;
    }

    public void setClobRetryTime(Timestamp clobRetryTime) {
        this.clobRetryTime = clobRetryTime;
    }

    public Long getClobRetry() {
        return clobRetry;
    }

    public void setClobRetry(Long clobRetry) {
        this.clobRetry = clobRetry;
    }

    public String getPatCategory() {
        return patCategory;
    }

    public void setPatCategory(String patCategory) {
        this.patCategory = patCategory;
    }

    public String getPatCategorySystem() {
        return patCategorySystem;
    }

    public void setPatCategorySystem(String patCategorySystem) {
        this.patCategorySystem = patCategorySystem;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Timestamp getAdmitTime() {
        return admitTime;
    }

    public void setAdmitTime(Timestamp admitTime) {
        this.admitTime = admitTime;
    }

    public String getHisType() {
        return hisType;
    }

    public void setHisType(String hisType) {
        this.hisType = hisType;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getAssignedCode() {
        return assignedCode;
    }

    public void setAssignedCode(String assignedCode) {
        this.assignedCode = assignedCode;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getAccessionnum() {
        return accessionnum;
    }

    public void setAccessionnum(String accessionnum) {
        this.accessionnum = accessionnum;
    }

    public String getStudyUid() {
        return studyUid;
    }

    public void setStudyUid(String studyUid) {
        this.studyUid = studyUid;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String getDiagnosisMethodCode() {
        return diagnosisMethodCode;
    }

    public void setDiagnosisMethodCode(String diagnosisMethodCode) {
        this.diagnosisMethodCode = diagnosisMethodCode;
    }

    public String getDicomNum() {
        return dicomNum;
    }

    public void setDicomNum(String dicomNum) {
        this.dicomNum = dicomNum;
    }

    public Timestamp getDicomStudyTime() {
        return dicomStudyTime;
    }

    public void setDicomStudyTime(Timestamp dicomStudyTime) {
        this.dicomStudyTime = dicomStudyTime;
    }

    public Long getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(Long repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    public String getMachineRoomName() {
        return machineRoomName;
    }

    public void setMachineRoomName(String machineRoomName) {
        this.machineRoomName = machineRoomName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRetrieveAets() {
        return retrieveAets;
    }

    public void setRetrieveAets(String retrieveAets) {
        this.retrieveAets = retrieveAets;
    }


    @Override
    public String toString() {
        return "DgateDocumentInfo{" +
                "pk='" + pk + '\'' +
                ", documentUniqueId='" + documentUniqueId + '\'' +
                ", documentDomainId='" + documentDomainId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", patientDomainId='" + patientDomainId + '\'' +
                ", fileType='" + fileType + '\'' +
                ", payLoadType='" + payLoadType + '\'' +
                ", subType='" + subType + '\'' +
                ", startTime=" + startTime +
                ", globalId='" + globalId + '\'' +
                ", beforeStatus='" + beforeStatus + '\'' +
                ", exportStatus='" + exportStatus + '\'' +
                ", patName='" + patName + '\'' +
                ", tposPath='" + tposPath + '\'' +
                ", sendStatus='" + sendStatus + '\'' +
                '}';
    }
}
