package com.example.visioncare;


import java.time.LocalDateTime;

class OPDetailsDTO {
    private int OP_ID;
    private String OP_TYPE;
    private int OP_TTYPE;
    private String TRANS_TYPE;
    private int OP_NO;
    private LocalDateTime OP_DATE;
    private LocalDateTime OP_TIME;
    private String OP_NAME;
    private String OP_ADICE;
    private String OP_COLOR;
    private String OP_SPC_REMARKS;
    private String OP_CHIEFCOMPLAINTS;
    private String OP_MEDICALHISTORY;
    private String OP_DIAGONSIS;
    private String OP_FINDINGS;
    private String OP_TREATMENTDETAILS;
    private String OP_REMARKS;
    private int DEPT_ID;
    private int REFERAL_ID;
    private int SCHEME_ID;
    private int COUNTER_ID;
    ContactDetailsDTO contactDetails;
    AppointmentDTO appointment;
    DoctorMasterDTO doctorMaster;
    UserMasterDTO userMaster;
    private int LOGIN_ID;

    public void setOP_ID(int OP_ID) {
        this.OP_ID = OP_ID;
    }

    public void setOP_TYPE(String OP_TYPE) {
        this.OP_TYPE = OP_TYPE;
    }

    public void setOP_TTYPE(int OP_TTYPE) {
        this.OP_TTYPE = OP_TTYPE;
    }

    public void setTRANS_TYPE(String TRANS_TYPE) {
        this.TRANS_TYPE = TRANS_TYPE;
    }

    public void setOP_NO(int OP_NO) {
        this.OP_NO = OP_NO;
    }

    public void setOP_DATE(LocalDateTime OP_DATE) {
        this.OP_DATE = OP_DATE;
    }

    public void setOP_TIME(LocalDateTime OP_TIME) {
        this.OP_TIME = OP_TIME;
    }

    public void setOP_NAME(String OP_NAME) {
        this.OP_NAME = OP_NAME;
    }

    public void setOP_ADICE(String OP_ADICE) {
        this.OP_ADICE = OP_ADICE;
    }

    public void setOP_COLOR(String OP_COLOR) {
        this.OP_COLOR = OP_COLOR;
    }

    public void setOP_SPC_REMARKS(String OP_SPC_REMARKS) {
        this.OP_SPC_REMARKS = OP_SPC_REMARKS;
    }

    public void setOP_CHIEFCOMPLAINTS(String OP_CHIEFCOMPLAINTS) {
        this.OP_CHIEFCOMPLAINTS = OP_CHIEFCOMPLAINTS;
    }

    public void setOP_MEDICALHISTORY(String OP_MEDICALHISTORY) {
        this.OP_MEDICALHISTORY = OP_MEDICALHISTORY;
    }

    public void setOP_DIAGONSIS(String OP_DIAGONSIS) {
        this.OP_DIAGONSIS = OP_DIAGONSIS;
    }

    public void setOP_FINDINGS(String OP_FINDINGS) {
        this.OP_FINDINGS = OP_FINDINGS;
    }

    public void setOP_TREATMENTDETAILS(String OP_TREATMENTDETAILS) {
        this.OP_TREATMENTDETAILS = OP_TREATMENTDETAILS;
    }

    public void setOP_REMARKS(String OP_REMARKS) {
        this.OP_REMARKS = OP_REMARKS;
    }

    public void setDEPT_ID(int DEPT_ID) {
        this.DEPT_ID = DEPT_ID;
    }

    public void setREFERAL_ID(int REFERAL_ID) {
        this.REFERAL_ID = REFERAL_ID;
    }

    public void setSCHEME_ID(int SCHEME_ID) {
        this.SCHEME_ID = SCHEME_ID;
    }

    public void setCOUNTER_ID(int COUNTER_ID) {
        this.COUNTER_ID = COUNTER_ID;
    }

    public void setContactDetails(ContactDetailsDTO contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void setAppointment(AppointmentDTO appointment) {
        this.appointment = appointment;
    }

    public void setDoctorMaster(DoctorMasterDTO doctorMaster) {
        this.doctorMaster = doctorMaster;
    }

    public void setUserMaster(UserMasterDTO userMaster) {
        this.userMaster = userMaster;
    }

    public void setLOGIN_ID(int LOGIN_ID) {
        this.LOGIN_ID = LOGIN_ID;
    }
}